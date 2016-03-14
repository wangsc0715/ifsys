package com.gigold.pay.autotest.datamaker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenkuan
 * on 16/1/29.
 */
public class ConstField {

//    public static void main(String args[]) {
////        System.out.println(getUnusedPhoneNo());
////        System.out.println(getAvalidPhoneNo());
//        addToAvalidList(renewPhone());
//    }

    public static List getAllConstFields() throws Exception{
        List<Map> list = new ArrayList<>();
        DBconnector dBconnector = new DBconnector();
        try {
            ResultSet rs = dBconnector.query("select * FROM T_IF_REFER_CONST_FEILD a where a.status = 'Y' limit 10;");
            while (rs.next()) {
                Map obj = new HashMap();
                obj.put("id",rs.getString("id"));
                obj.put("holder",rs.getString("holder"));
                obj.put("desc",rs.getString("desc"));
                obj.put("timestamp",rs.getString("timestamp"));
                list.add(obj);
            }
            return list;
        } catch (Exception ignore) {

        } finally {
            dBconnector.close();
        }

        return list;
    };

}
