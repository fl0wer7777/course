package com.tjl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


//该文件功能记录数据库连接的
public class JDBC {

	public static void main(String[] args) {
		try {
//          加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功！");
//          连接数据库
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC","root","zzcfgdfcvb123,");
			System.out.println("数据库连接成功！");
//          创建执行环境的对象
			Statement statement = conn.createStatement();
//          执行sql语句验证
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
			System.out.println("驱动加载失败！");
			System.out.println("数据库连接失败！");
		}
	}

}
