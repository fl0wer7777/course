package com.tjl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


//���ļ����ܼ�¼���ݿ����ӵ�
public class JDBC {

	public static void main(String[] args) {
		try {
//          ��������
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�������سɹ���");
//          �������ݿ�
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC","root","zzcfgdfcvb123,");
			System.out.println("���ݿ����ӳɹ���");
//          ����ִ�л����Ķ���
			Statement statement = conn.createStatement();
//          ִ��sql�����֤
			ResultSet result = statement.executeQuery("select * from roomtypeandprice");
			while(result.next()) {
				System.out.print(result.getString("roomType"));
				System.out.print(result.getInt("price"));
				System.out.print(result.getString("desc"));
				System.out.println(result.getString("url"));
			}
//          
			
			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
			System.out.println("���ݿ�����ʧ�ܣ�");
		}
	}

}
