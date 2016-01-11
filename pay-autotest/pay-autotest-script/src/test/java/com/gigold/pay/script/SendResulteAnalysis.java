/**
 * Title: Test.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.script;

import java.io.*;
import java.util.*;

import com.gigold.pay.autotest.bo.InterFaceSysTem;
import com.gigold.pay.autotest.dao.InterFaceDao;
import com.gigold.pay.autotest.dao.InterFaceSystemDao;
import com.gigold.pay.autotest.service.*;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.VerticalAlignment;
import org.apache.commons.collections.list.TreeList;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.IfSysMockHistory;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.email.MailSenderService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import org.springframework.core.task.SyncTaskExecutor;
/**
 * Title: Test<br/>
 * Description: <br/>
 * Company: gigold<br/>
 *
 * @author xiebin
 * @date 2015年12月5日下午3:37:06
 *
 */

public class SendResulteAnalysis {
	private static final long serialVersionUID = 1L;
	private IfsysCheckThreadPool ifsysCheckThreadPool;
	private MailSenderService mailSenderService;
	private IfSysMockService ifSysMockService;
	private IfSysStuffService ifSysStuffService;
	private IfSysMockHistoryService ifSysMockHistoryService;
	private InterFaceService interFaceService;
	private InterFaceDao interFaceDao;
    private IfSysAutoTestService ifSysAutoTestService;
    private InterFaceSysService interFaceSysService;

	@Before
	public void setup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
		ifsysCheckThreadPool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
		mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
		ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
		ifSysStuffService = (IfSysStuffService) SpringContextHolder.getBean(IfSysStuffService.class);
		ifSysMockHistoryService = (IfSysMockHistoryService) SpringContextHolder.getBean(IfSysMockHistoryService.class);
		interFaceService = (InterFaceService) SpringContextHolder.getBean(InterFaceService.class);
        interFaceDao = (InterFaceDao) SpringContextHolder.getBean(InterFaceDao.class);
        ifSysAutoTestService = (IfSysAutoTestService) SpringContextHolder.getBean(IfSysAutoTestService.class);
        interFaceSysService = (InterFaceSysService) SpringContextHolder.getBean(InterFaceSysService.class);


	}

	@Test
	public void work() {
		System.out.println("开始调用接口");
		autoTest();
		System.out.println("调用接口结束");
        sendMail();
        testAutoTest();
        //sendCases();
        System.out.println("work");
	}

   // @Test
	public void autoTest() {
		ifSysMockService.initIfSysMock();
		ifsysCheckThreadPool.execute();
		
	}

   // @Test
	public void sendMail() {

        // 返回所有测试过的结果
        List<IfSysMock> resulteMocks = ifSysMockService.filterMocksByFailed();
        // 根据用例 - 步骤对应表
        if(resulteMocks.isEmpty()){
            System.out.print("没有查询到错误的结果集");
        }else {
            // 1.测试结果按接口分类
            Map<String,Object> StepsMap = new HashMap<>();// 每个用例的步骤表
            Map<String,List<IfSysMock>> rstItfces = new TreeMap<>();
            for(IfSysMock ifSysMock:resulteMocks){
                // 首先,判断结果分类中是否已经初始化过了,若没有则初始化
                String key  = String.valueOf(ifSysMock.getIfId());
                String mockId  = String.valueOf(ifSysMock.getId());
                if(!rstItfces.containsKey(key)){
                    rstItfces.put(key,new ArrayList<IfSysMock>()); // 键值格式为{"12":[1,2,3,4]}
                }
                // 然后,初始化每个用例的步骤表 键值格式为 {"186":[1,2,3,4]}
                List<IfSysMock> Steps = new ArrayList<>();
                ifSysAutoTestService.invokerOrder(Steps,Integer.parseInt(mockId));
                Collections.reverse(Steps);//步骤反序
                StepsMap.put(mockId,Steps);
                // 最后,增加mock
                rstItfces.get(key).add(ifSysMock);
            }


            // 2.分发收件人
            Map<String,List<List<IfSysMock>>> observers = new HashMap<>();
            for(String ifId:rstItfces.keySet()){
                // 每个接口的测试结果集 [1,2,3,4]
                List<IfSysMock> eachMockSet = rstItfces.get(ifId);

                // 获取接口的关注者
                List<IfSysMock> emailObjs = ifSysMockService.getInterfaceFollowShipById(Integer.parseInt(ifId));

                // 将接口结果集添加到收件人observers
                int i = 1;
                for(IfSysMock emailObj:emailObjs){ // 遍历单个接口的测试结果集,拿到测试结果
                    String email = emailObj.getEmail(); // chenkuan@qq.com
                    String Uname = emailObj.getUsername(); // chenkuan@qq.com
                    String key = email+"::"+Uname;
                    if(!observers.containsKey(key)){
                        observers.put(key,new ArrayList<List<IfSysMock>>());//二维数组,每组为一个接口
                    }
                    observers.get(key).add(eachMockSet);// 对测试结果所包含的发件人去重,然后将相同的发件人所对应的mock所对应的接口进行关联
                    if(i++ == emailObjs.size()){// 如果到最后,则进行一次排序
                        Collections.sort(observers.get(key), new Comparator<List<IfSysMock>>() {
                            @Override
                            public int compare(List<IfSysMock> o1, List<IfSysMock> o2) {
                                return o1.get(0).getIfId() - o2.get(0).getIfId();
                            }
                        });
                    }
                }
            }


            // 3.发件
            for(String emailNuname:observers.keySet()){
                // 获取每个用户的结果
                List<List<IfSysMock>> ifOfmockSetList = observers.get(emailNuname);
                //收件人地址和姓名
                String email = emailNuname.split("::")[0].trim();
                String userName = emailNuname.split("::")[1].trim();
                // 设置收件人地址
                List<String> addressTo = new ArrayList<>();
                addressTo.add(email);
                mailSenderService.setTo(addressTo);
                mailSenderService.setSubject("详情 - 来自独孤九剑接口自动化测试的邮件");
                mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
                // 发送结果
                Map<String,Object> model = new HashMap<>();
                model.put("ifOfmockSetList", ifOfmockSetList);
                model.put("userName", userName);
                model.put("StepsMap", StepsMap);//每个用例的步骤表  {332=[], 159=[1,2], 330=[1,2]}

                if(email.equals("chenkuan@gigold.com")||email.equals("chenhl@gigold.com")||email.equals("liuzg@gigold.com")||email.equals("xiebin@gigold.com"))
                mailSenderService.sendWithTemplateForHTML(model);
            }
            System.out.println("邮件发送成功！");
        }
	}


    //@Test
    public  void sendCases() {
        try{
            WritableWorkbook book = Workbook.createWorkbook(new File("casesModel.xls"));//工作簿对象
            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 12);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 14,WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(true); // 文字是否换行

            // 用于表头
            WritableCellFormat wcf_head = new WritableCellFormat(BoldFont);
            wcf_head.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_head.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_head.setWrap(true); // 文字是否换行
            wcf_head.setBackground(Colour.LIGHT_BLUE);

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(true); // 文字是否换行


            /** ************以下设置三种单元格样式，灵活备用************ */


            // 添加附件 - 用例
            List<IfSysMock> resulteCases = ifSysMockService.getCasesMarks();
            List<InterFaceSysTem> ifsysInfos = interFaceSysService.getAllSysInfo();
            Map<String,String> infos = new HashMap<>();
            // 查出系统信息
            for(InterFaceSysTem ifsysInfo : ifsysInfos){
                infos.put(String.valueOf(ifsysInfo.getId()),ifsysInfo.getSysName());
            }


            // 遍历每一条步骤
            int pageID=0;// 页号
            Map<String,Integer> index = new HashMap<>();//所有页的行号
            for(IfSysMock ifSysMock:resulteCases) {
                List<IfSysMock> Steps = new ArrayList<>();
                String ifSysId = String.valueOf(ifSysMock.getIfSysId());
                String ifSysName = infos.get(ifSysId);
                String mockId  = String.valueOf(ifSysMock.getId());
                ifSysAutoTestService.invokerOrder(Steps, Integer.parseInt(mockId));
                Collections.reverse(Steps);//步骤反序,得到排序后的用例 list

                // 查找sheet是否存在
                WritableSheet sheet = book.getSheet(ifSysName);
                // 不存在就建一个
                if(sheet==null){
                    index.put(ifSysName,2);
                    sheet = book.createSheet(ifSysName , pageID++);
                    // 设置表头
                    Label title = new Label(0,0,ifSysName+" - 测试用例",wcf_center);
                    Label head1 = new Label(0,1,"序号",wcf_head);
                    Label head2 = new Label(1,1,"接口名",wcf_head);
                    Label head3 = new Label(2,1,"用例名",wcf_head);
                    Label head4 = new Label(3,1,"步骤",wcf_head);
                    Label head5 = new Label(4,1,"预期输出",wcf_head);
                    Label head6 = new Label(5,1,"备注",wcf_head);
                    // 设置列宽
                    sheet.setColumnView(0,5);
                    sheet.setColumnView(1,30);
                    sheet.setColumnView(2,30);
                    sheet.setColumnView(3,60);
                    sheet.setColumnView(4,20);
                    sheet.setColumnView(5,20);
                    // 设置行高
                    sheet.setRowView(0,1600,false);
                    // 合并页标题
                    sheet.mergeCells(0,0,5,0);
                    // 添加标题
                    sheet.addCell(title);
                    sheet.addCell(head1);
                    sheet.addCell(head2);
                    sheet.addCell(head3);
                    sheet.addCell(head4);
                    sheet.addCell(head5);
                    sheet.addCell(head6);
                }
                int inx = index.get(ifSysName);
                //序号
                jxl.write.Number id = new jxl.write.Number(0,inx,inx,wcf_left);
                //接口名
                Label ifName = new Label(1,inx,"("+String.valueOf(ifSysMock.getIfId())+")"+ifSysMock.getIfName(),wcf_left);
                //用例名
                Label caseName = new Label(2,inx,"在"+ifSysMock.getCaseName()+"的情况下,测试"+ifSysMock.getIfName(),wcf_left);
                String stepsStr = "";
                int inx_stp=1;
                for(IfSysMock stepObj :Steps){
                    stepsStr+=String.valueOf(inx_stp++)+". "+stepObj.getCaseName()+"\n";
                }
                stepsStr+=(String.valueOf(inx_stp)+". "+ifSysMock.getCaseName());
                //步骤
                Label steps = new Label(3,inx,stepsStr,wcf_left);
                //预期输出
                Label preOut = new Label(4,inx,ifSysMock.getRspCode()+ifSysMock.getPreCodeDesc(),wcf_left);
                //备注
                Label remark = new Label(5,inx,ifSysMock.getIfDESC(),wcf_left);

                sheet.addCell(id);
                sheet.addCell(ifName);
                sheet.addCell(caseName);
                sheet.addCell(steps);
                sheet.addCell(preOut);
                sheet.addCell(remark);

                index.put(ifSysName,index.get(ifSysName)+1);

            }

            book.write();
            book.close();
        }catch (Exception e){

            System.out.println(e);
            System.out.println("打开失败");
        }
        //收件人地址和姓名
        String email = "chenkuan@gigold.com";
        String userName = "chenkuan";
        // 设置收件人地址
        List<String> addressTo = new ArrayList<>();
        addressTo.add(email);
        mailSenderService.setTo(addressTo);
        mailSenderService.setSubject("测试用例 - 本次测试所用到的测试用例");
        mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
        // 发送结果
        //mailSenderService.sendHtmlWithAttachment(new File("casesModel.xls"));
    }

	//@Test
	public void testAutoTest() {
        int jnrCount = 15;
        // 发送结果分析
        List<IfSysMockHistory> recentRst = ifSysMockHistoryService.getNewestReslutOf(jnrCount);

        if(recentRst==null){System.out.println("查询最近的mocks查询结果为空");return;}
        Map< String,List<IfSysMockHistory> > mailBuffers = new HashMap<>();
        for(IfSysMockHistory history:recentRst){
            String JNR = history.getJrn();

            // 为每个接收者包装信件
            if(mailBuffers.containsKey(JNR)&&mailBuffers.get(JNR).size()!=0){
                mailBuffers.get(JNR).add(history);
            }else{
                List<IfSysMockHistory> histories = new ArrayList<IfSysMockHistory>();
                histories.add(history);
                mailBuffers.put(JNR,histories);
            }
        }

        // 重新格式化结果数据
        Iterator entries = mailBuffers.entrySet().iterator();
        Comparator comparator = new Comparator<String>(){

            public int compare(String o1, String o2)
            {
                int n1 = Integer.parseInt(o1);
                int n2 = Integer.parseInt(o2);
                if (n1 == n2)
                    return 0;
                else
                    return n1-n2;
            }};
        Map<String,Map> initedDataSet = new TreeMap<>();
        ArrayList<String> HeadIFID = new ArrayList<>();

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            // 每一批的批号
            String JNR = (String)entry.getKey();
            // 每一批的所有数据
            List<IfSysMockHistory> histMocks = (List<IfSysMockHistory>)entry.getValue();//[{if1,test1,info1},{if1,test2,info2}]

            // 遍历所有数据,并分装到eachIfset
            Map<String,Map<String,Object>> eachIfSet = new HashMap<>();// {if1 : {null,null,[] },if2 : { }}
            for(IfSysMockHistory eachHisMock:histMocks){
                //{if1,test1,info1}
                //判断eachIfSet是否已经有该接口的数据,若没有,则新建
                String ifId = String.valueOf(eachHisMock.getIfId());
                if(!eachIfSet.containsKey(ifId)){
                    eachIfSet.put(ifId,new HashMap<String,Object>());
                    eachIfSet.get(ifId).put("ifPassRate",new Float(0)); //后面计算
                    String ifName = eachHisMock.getIfName();
                    eachIfSet.get(ifId).put("ifName",(ifName!=null)?ifName:"0"); //取接口名,取不到则为0
                    eachIfSet.get(ifId).put("ifTestData",new ArrayList<IfSysMockHistory>());
                }

                // 同时初始化表列
                HeadIFID.add(ifId);

                // 当前接口的所有原始结果数据存放点
                List<IfSysMockHistory> ifTestData = ((List<IfSysMockHistory>)eachIfSet.get(ifId).get("ifTestData"));
                ifTestData.add(eachHisMock);

                // 实时计算当前接口通过率
                float rstSiz = ifTestData.size();//当前单接口集合大小
                if(rstSiz!=0){

                    float nowRst = 0;
                    try {
                        nowRst = eachHisMock.getTestResult().isEmpty()?0:(eachHisMock.getTestResult().equals("1")?1:0);
                    }catch (Exception e){
                        System.out.println("************** TestResult 为空 ********* 是否有改了数据库  ***:"+eachHisMock.getTestResult());
                    }
                    float preRst = (float) (eachIfSet.get(ifId).get("ifPassRate"));
                    float _rate = ((rstSiz-1)*preRst+nowRst)/rstSiz;
                    eachIfSet.get(ifId).put("ifPassRate",(float)(Math.round(_rate*100))/100); //实时计算
                }else{
                    eachIfSet.get(ifId).put("ifPassRate","没有测试数据,无法计算");
                }
            }
            initedDataSet.put(JNR,eachIfSet); //拼装
        }
        // 格式化结束


        // 去重 - HeadIFID 去重/排序
        Map<String,String> IfIDNameMap = new TreeMap<>(comparator);// id-名字映射
        Map<String,String> IfIDDsnrMap = new TreeMap<>(comparator); // id-设计者映射
        for (Iterator iter = HeadIFID.iterator(); iter.hasNext();) {
            String _ifId = String.valueOf(iter.next());
            IfIDNameMap.put(_ifId,_ifId);
        }
        // 去重 - 替换接口名
        Iterator<String> iter = IfIDNameMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            InterFaceInfo ifinfo=interFaceService.getInterFaceById(Integer.parseInt(key));
            if(ifinfo!=null){
                IfIDNameMap.put(key,ifinfo.getIfName());
                IfIDDsnrMap.put(key,ifinfo.getDsname());
            }
        }
        // 去重 - 结束

        // JNR集合
        Set<String> OrderedHeadJNRSet = initedDataSet.keySet();

        // 计算通过率 - 格式化数据
        List<IfSysMockHistory> lastRst = ifSysMockHistoryService.getNewestReslutOf(1);//最近一批数据
        float _passRate = 0;

        // 用例总数
        float mockCount = lastRst.size();

        // 计算当次覆盖率
        float CCprob,CCtot,IFtst,IFtot;
        CCprob=0;
        CCtot = lastRst.size();
        for(IfSysMockHistory eachRst:lastRst){
            if(!(eachRst.getTestResult()==null||eachRst.getTestResult().equals("1")||eachRst.getTestResult().equals("0")))
                CCprob++;
            _passRate += ((eachRst.getTestResult()!=null)&&eachRst.getTestResult().equals("1")?1:0);
//            // 获取每个接口的当前状态
//            int eachRstNowStat = newestPassRate.get(strIfId);
//            // 若一直是通过状态,则写最新状态(一票否决状态)
//            if(eachRstNowStat==1){
//                newestPassRate.put(strIfId,nowrst);
//            }
        }
        // 计算通过率 - 按mock算
        float mockPassRate = 100*_passRate/mockCount;
        String lastJNR = lastRst.get(0).getJrn();
        IFtst = initedDataSet.get(lastJNR).size();
        IFtot = interFaceDao.getAllIfSysCount();
        System.out.println(initedDataSet.get(lastJNR).size());

        // 计算当次覆盖率
        float CCcoverage = ( 1 - CCprob/CCtot );
        float IFcoverage = ( IFtst/IFtot );

        // 发送邮件
        String[] copyList = SystemPropertyConfigure.getProperty("mail.default.observer").split(",");
        List<String> copyTo = new ArrayList<String>();
        for(String email:copyList){
            System.out.println(email);
            copyTo.add(email);
        }
        mailSenderService.setTo(copyTo);
       // String userName= ifSysStuffService.getStuffByEmail(email).get(0).getUserName();
        mailSenderService.setSubject("总览 - 来自独孤九剑接口自动化测试的邮件");
        mailSenderService.setTemplateName("copyMail.vm");// 设置的邮件模板
        // 发送结果
        Map<String,Object> model = new HashMap<>();
        model.put("initedDataSet", initedDataSet);// 所有数据
        model.put("IfIDNameMap", IfIDNameMap);// 表列头
        model.put("IfIDDsnrMap", IfIDDsnrMap);// 设计者映射
        model.put("OrderedHeadJNRSet", OrderedHeadJNRSet);//表行头
     //   model.put("userName", userName);
        // 最近一条JNR
        model.put("lastJNR", lastJNR);
        // 指标数据
        model.put("ifCount", IFtst);
        // model.put("caseCount", caseCount); //所有的
        model.put("caseCount", Math.round(mockCount));
        model.put("jnrCount", jnrCount);
        model.put("mockPassRate", (float)(Math.round(mockPassRate*100))/100);//保留两位
        model.put("CCcoverage", (float)(Math.round(CCcoverage*10000))/100);//保留两位
        model.put("IFcoverage", (float)(Math.round(IFcoverage*10000))/100);//保留两位
        mailSenderService.sendWithTemplateForHTML(model);

        System.out.println("邮件发送成功！");
    }

	@After
	/**
	 *
	 * Title: testSendMail<br/>
	 * Description: 测试完成之后再发邮件的情况<br/>
	 *
	 * @author xiebin
	 * @date 2015年12月7日下午4:27:30
	 *
	 */
	public void testSendMail() {
		// 结束
	}
}
