package com.gigold.pay.autotest.datamaker;

import com.gigold.pay.autotest.datamaker.DBconnector;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenkuan
 * on 16/1/29.
 */
public class IdCardNo {

//    public static void main(String args[]) throws Exception {
////        System.out.println(getUnusedPhoneNo());
////        System.out.println(getAvalidPhoneNo());
//        System.out.println(getUnusedNo());
////        disableNo("530500198304214639");
//    }

    public static String getUnusedNo() throws Exception {
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * from T_UI_AVL_IDCARD_NO a where a.status='Y' limit 1");
            rs.next();
            return rs.getString("cardNo");
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }

        return null;
    }

    public static List<String> getUsedNo() throws Exception {
        List<String> list = new ArrayList<>();
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * from T_UI_AVL_IDCARD_NO a where a.status!='Y' limit 100");
            while (rs.next()) {
                list.add(rs.getString("cardNo"));
            }
            return list;
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }

        return list;
    }

    public static void renewNo() throws Exception {
        String phonenum = "";
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * from T_UI_AVL_IDCARD_NO a limit 1");
            while (rs.next()) {
                phonenum = rs.getString("NUMBER");
            }
            String phone_head = phonenum.substring(0, 3);
            String phone_nill = phonenum.substring(3, 11);
            long _phone_nill = Long.parseLong(phone_nill);
            _phone_nill++;
            // 向左补足
            String filledString = "00000000" + String.valueOf(_phone_nill);
            filledString = phone_head + filledString.substring(filledString.length() - 8);
            dBconnector.updata("update T_UI_LAST_PHONE_NO a set a.NUMBER='" + filledString + "' where a.id=1");
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }
    }

    public static void disableNo(String CardNo) throws Exception {
        DBconnector dBconnector = new DBconnector();
        try {
            dBconnector.insert("UPDATE T_UI_AVL_IDCARD_NO SET status='N' WHERE cardNo='" + CardNo + "'");
        } finally {
            dBconnector.close();
        }

    }

    public static void disableNo(String CardNo , String useage) throws Exception {
        DBconnector dBconnector = new DBconnector();
        try {
            dBconnector.insert("UPDATE T_UI_AVL_IDCARD_NO SET status='N' , useage='"+useage+"' WHERE cardNo='" + CardNo + "'");
        } finally {
            dBconnector.close();
        }

    }
}
