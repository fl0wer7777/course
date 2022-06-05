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


public class HTServiceImpl implements HTService{

	public TableDTO getHT(HTRequest request) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TableDTO returnDTO = new TableDTO();
		

//      �������ݿ�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�������سɹ���");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
					"manger","1234");
			System.out.println("���ݿ����ӳɹ���");

			ps = conn.prepareStatement("select * from orders;");
			rs = ps.executeQuery();
			Vector<Vector<Object>> data = new Vector<>();
			while(rs.next()) {
				//����ÿһ����¼
				Vector<Object> record = new Vector<Object>();
				String orderNumber = rs.getString("orderNumber");
				String orderStatus = rs.getString("orderStatus");
				String customerIDCard = rs.getString("customerIDCard");
				String roomNumber = rs.getString("roomNumber");
				String checkInTime = rs.getString("checkInTime");
				String checkOutTime = rs.getString("checkOutTime");
				String totalMoney = rs.getString("totalMoney");
				String waiterID = rs.getString("waiterID");
				String remarks = rs.getString("remarks");
				String orderTime = rs.getString("orderTime");
				
				record.addElement(orderNumber);
				record.addElement(orderStatus);
				record.addElement(customerIDCard);
				record.addElement(roomNumber);
				record.addElement(checkInTime);
				record.addElement(checkOutTime);
				record.addElement(totalMoney);
				record.addElement(waiterID);
				record.addElement(remarks);
				record.addElement(orderTime);

				data.addElement(record);
				returnDTO.setData(data);
			}
			return returnDTO;
		}catch (Exception e) {
			
		}
		
		
		return null;
		
	}

}
