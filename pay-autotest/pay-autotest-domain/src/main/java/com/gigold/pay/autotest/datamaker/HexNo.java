package com.gigold.pay.autotest.datamaker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenkuan
 * on 16/1/29.
 */
public class HexNo {

    public static void main(String args[]) throws Exception {
        String no = getLastHexNo();
        System.out.println(no);
    }


    public static List<String> getUnUsedNo() throws Exception {
        List<String> list = new ArrayList<>();
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * from T_UI_AVL_OPER_ACCT where stat='Y' limit 100");
            while (rs.next()) {
                list.add(rs.getString("operacct"));
            }
            return list;
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }

        return list;
    }

    public static String getLastHexNo() throws Exception {
        String hexNo = "";
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * from T_UI_LAST_HEX_NO limit 1");
            while (rs.next()) {
                hexNo = rs.getString("no");
            }
            return hexNo;
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }

        return hexNo;
    }


    public static void renewNo() throws Exception {
        String hexNo = "";
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * from T_UI_LAST_HEX_NO limit 1");
            while (rs.next()) {
                hexNo = rs.getString("no");
            }
            dBconnector.updata("UPDATE T_UI_LAST_HEX_NO SET no ='"+StrIncrease(hexNo)+"'");
            dBconnector.updata("insert into T_UI_AVL_OPER_ACCT(operacct) values('"+hexNo+"')");
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }
    }



    /**
     * 设置号码用途
     * @param no 号码
     * @param useage 用途
     * @throws Exception
     */
    public static void disableNo(String no , String useage) throws Exception {
        DBconnector dBconnector = new DBconnector();
        try {
            dBconnector.insert("UPDATE T_UI_AVL_OPER_ACCT SET stat='N',useage='"+useage+"' WHERE operacct='" + no + "'");
        } finally {
            dBconnector.close();
        }

    }

    public static void disableNo(String no) throws Exception {
        disableNo(no,"unset");
    }

    // 字符串增长工具
    public static String StrIncrease(String str) {
        str = str.toUpperCase();
        char[] chars = str.toCharArray();
        chars[chars.length-1]++;
        for(int i=chars.length-1;i>=0; i--){
            byte nowchar = (byte) chars[i];
            // 跳过符号段
            if(nowchar>57&&nowchar<65)chars[i]='A';
            // 进位
            if(nowchar>90){
                chars[i]='0';// 当前位置零
                chars[i-1]++; // 进位加1
            }
        }
        str = String.valueOf(chars);
        return str;
    }
}
