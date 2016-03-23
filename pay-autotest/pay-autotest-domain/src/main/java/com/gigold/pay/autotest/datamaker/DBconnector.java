package com.gigold.pay.autotest.datamaker;

import java.sql.*;

public class DBconnector {
//    public static final String url = GlobalVal.get("mysql.url");
//    public static final String name = "com.mysql.jdbc.Driver";
//    public static final String user = GlobalVal.get("mysql.username");
//    public static final String password = GlobalVal.get("mysql.password");
//
    public static final String url = "jdbc:mysql://rds80ubr9x806azaml24.mysql.rds.aliyuncs.com:3306/motion2?characterEncoding=utf8";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "motion2";
    public static final String password = "motion2cs";

    public Connection conn = null;
    public Statement stmt = null;

    public DBconnector() throws Exception {
        try {
            Class.forName(name);// 动态加载mysql驱动
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
            stmt.close();
        }

    }

    public ResultSet query(String sql) throws SQLException {
        try {
            return stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String sql) throws SQLException {
        try {
            return stmt.execute(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updata(String sql) throws SQLException {
        try {
            return stmt.executeUpdate(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insert(String sql) throws SQLException {
        try {
            return stmt.executeUpdate(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            System.out.println(sql);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(sql);
        }
        return 0;
    }

    public void close(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args){
//        try {
//            DBconnector dBconnector = new DBconnector();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}