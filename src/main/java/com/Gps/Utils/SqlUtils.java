package com.Gps.Utils;

import java.sql.*;

public class SqlUtils {
    public SqlUtils() {
    }

    private static final String URL = "jdbc:mysql://localhost:3306/wordcount?" +
            "userUnicode=true&characterEncoding=UTF-8";
    private static final String USRE_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static Statement statement;


    public static Connection getConnection() {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(URL,USRE_NAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet executeQuery(String sql){
        if (connection==null)
            getConnection();

        try {
             statement = connection.createStatement();
             return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static ResultSet executeQuery(String sql,Object...objects){
        if (connection==null)
            getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i=0;i<objects.length;i++){
                preparedStatement.setObject(i+1,objects[i]);
            }
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void  close(){
        try {
            if (connection!=null&&connection.isClosed()){
                connection.close();
            if (statement!=null)
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}