package com.Gps.servlet;

import com.Gps.Bean.GpsBean;
import com.Gps.dao.GpsDao;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/gps")
public class GpsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GpsDao gpsDao = new GpsDao();
        String sql="SELECT * FROM t_gps";
        List<GpsBean> list = gpsDao.getAllGps(sql);
        String json= JSON.toJSONString(list);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
