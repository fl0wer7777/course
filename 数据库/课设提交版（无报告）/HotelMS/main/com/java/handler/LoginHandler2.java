package com.java.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.java.gui.*;
import com.java.handler.*;

public class LoginHandler2 {
		
	public static void main(String[] args) {
		
	}
	
	public static boolean Login(String username,String password,String type) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功！");
	//      连接数据库
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
					"root","zzcfgdfcvb123,");
			System.out.println("数据库连接成功！");
	//      创建执行环境的对象
			Statement statement = conn.createStatement();
			if("系统管理员".equals(type)) {
				ResultSet result = statement.executeQuery("select userPassword from systemadministrator where userID ="+"'"
						+username+"'");
				while(result.next()) {
					System.out.println(result.getString("userPassword"));
					if( password.equals(result.getString("userPassword") ) ) {
						flag = true;
						break;
						}
					}	
			}else if("员工".equals(type)) {
				ResultSet result = statement.executeQuery("select waiterPassword from waiter where waiterID ="+"'"
						+username+"'");
				while(result.next()) {
					System.out.println(result.getString("waiterPassword"));
					if( password.equals(result.getString("waiterPassword") ) ) {
						flag = true;
						break;
						}
					}	
			}	
		} catch (Exception e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return flag;
	}
	

}
