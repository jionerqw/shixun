package com.Gps.dao;

import com.Gps.Bean.GpsBean;
import com.Gps.Utils.SqlUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GpsDao {
    public List<GpsBean> getAllGps(String sql){
        List<GpsBean> list = new ArrayList<GpsBean>();
        ResultSet resultSet = SqlUtils.executeQuery(sql);
        try {
            while (resultSet.next()){
                String hours = resultSet.getString("hours");
                int cars = resultSet.getInt("cars");
                int i = (int) (resultSet.getDouble("rates")*1000);
                double rates=i/1000.0;
                list.add(new GpsBean(hours,cars,rates));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }
    public List<GpsBean> getPageGps(String sql,int starts,int end){
        List<GpsBean> list = new ArrayList<GpsBean>();
        ResultSet resultSet = SqlUtils.executeQuery(sql,starts,end);
        try {
            while (resultSet.next()){
                String hours = resultSet.getString("hours");
                int cars = resultSet.getInt("cars");
                int i = (int) (resultSet.getDouble("rates")*1000);
                double rates=i/1000.0;
                list.add(new GpsBean(hours,cars,rates));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
}
