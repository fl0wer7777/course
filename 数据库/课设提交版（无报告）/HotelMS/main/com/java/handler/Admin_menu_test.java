package com.java.handler;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Admin_menu_test extends JFrame{
	
	public Admin_menu_test(String title){
		super(title);
		
		JPanel menu = new JPanel();
		this.setContentPane(menu);
		menu.setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("客房信息 ");
		menuBar.add(fileMenu);
		
		JMenu fileMenu1 = new JMenu("订单信息 ");
		menuBar.add(fileMenu1);
		
		JMenu fileMenu2 = new JMenu("仓库信息 ");
		menuBar.add(fileMenu2);
		
		//width:600, height:400
		setSize(1000, 800);
		//点关闭的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小不可变
		setResizable(true);
		//窗口居中默认居中
		setLocationRelativeTo(null);
		//窗口可见
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Admin_menu_test("管理员XX登录");
	}

}
