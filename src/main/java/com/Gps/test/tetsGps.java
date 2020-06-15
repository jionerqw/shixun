package com.Gps.test;

import com.Gps.Bean.GpsBean;
import com.Gps.dao.GpsDao;
import com.alibaba.fastjson.JSON;

import java.util.List;

public class tetsGps {
    public static void main(String[] args) {
        GpsDao gpsDao = new GpsDao();
        String sql="SELECT * FROM t_gps limit ?,?";
        List<GpsBean> pageGps = gpsDao.getPageGps(sql,0, 3);
        String json= JSON.toJSONString(pageGps);
        System.out.println(json);
    }
}
