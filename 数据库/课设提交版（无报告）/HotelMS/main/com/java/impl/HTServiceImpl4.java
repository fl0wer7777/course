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


public class HTServiceImpl4 implements HTService3{

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

			ps = conn.prepareStatement("select * from roominfo where roomNumber like '%" +s1 +"%'");
			rs = ps.executeQuery();
			Vector<Vector<Object>> data = new Vector<>();
			while(rs.next()) {
				//处理每一条记录
				Vector<Object> record = new Vector<Object>();
				String roomNumber = rs.getString("roomNumber");
				String roomType = rs.getString("roomType");
				String roomStatus = rs.getString("roomStatus");
				String remarks = rs.getString("remarks");
				String price = rs.getString("price");
				String desc = rs.getString("desc");	
			
				record.addElement(roomNumber);
				record.addElement(roomType);
				record.addElement(roomStatus);
				record.addElement(remarks);
				record.addElement(price);
				record.addElement(desc);

				data.addElement(record);
				returnDTO.setData(data);
			}
			return returnDTO;
		}catch (Exception e) {
			
		}
		
		
		return null;
		
	}

}
