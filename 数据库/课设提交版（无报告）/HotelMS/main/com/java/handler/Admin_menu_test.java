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
		
		JMenu fileMenu = new JMenu("�ͷ���Ϣ ");
		menuBar.add(fileMenu);
		
		JMenu fileMenu1 = new JMenu("������Ϣ ");
		menuBar.add(fileMenu1);
		
		JMenu fileMenu2 = new JMenu("�ֿ���Ϣ ");
		menuBar.add(fileMenu2);
		
		//width:600, height:400
		setSize(1000, 800);
		//��رյ�ʱ���˳�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ڴ�С���ɱ�
		setResizable(true);
		//���ھ���Ĭ�Ͼ���
		setLocationRelativeTo(null);
		//���ڿɼ�
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Admin_menu_test("����ԱXX��¼");
	}

}
