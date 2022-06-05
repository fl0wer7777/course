package com.java.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.java.req.HTRequest;
import com.java.res.TableDTO;
import com.java.service.HTService;
import com.java.service.HTService3;


public class HTServiceImpl3 implements HTService3{

	public TableDTO getHT(HTRequest request,String s1) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TableDTO returnDTO = new TableDTO();
		

//      连接数据库
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功！");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
					"root","zzcfgdfcvb123,");
			System.out.println("数据库连接成功！");

			ps = conn.prepareStatement("select * from orderViews where orderNumber like '%" +s1 +"%'");
			rs = ps.executeQuery();
			Vector<Vector<Object>> data = new Vector<>();
			while(rs.next()) {
				//处理每一条记录
				Vector<Object> record = new Vector<Object>();
				String orderNumber = rs.getString("orderNumber");
				String orderStatus = rs.getString("orderStatus");
				String customerName = rs.getString("customerName");
				String roomNumber = rs.getString("roomNumber");
				String roomType = rs.getString("roomType");
				String orderTime = rs.getString("orderTime");
				String checkInTime = rs.getString("checkInTime");
				String checkOutTime = rs.getString("checkOutTime");
				String customerPhoneNumber = rs.getString("customerPhoneNumber");
				String totalMoney = rs.getString("totalMoney");


				
				
				record.addElement(orderNumber);
				record.addElement(orderStatus);
				record.addElement(customerName);
				record.addElement(roomNumber);
				record.addElement(roomType);
				record.addElement(orderTime);
				record.addElement(checkInTime);
				record.addElement(checkOutTime);
				record.addElement(customerPhoneNumber);
				record.addElement(totalMoney);
				
				

				data.addElement(record);
				returnDTO.setData(data);
			}
			return returnDTO;
		}catch (Exception e) {
			
		}
		
		
		return null;
		
	}

}
