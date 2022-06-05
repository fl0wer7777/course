package com.java.test;

import java.awt.Container;
import javax.swing.*;

public class jframeTest extends JFrame{
	
	JButton btn;
	
	public jframeTest() {
		super("登录界面");//title

		//按钮,将它先放到容器里，再放入窗口
		btn = new JButton("登录");
		btn.setSize(100,100);
		Container contentPane = getContentPane();
		contentPane.add(btn);
		//width:900, height:600
		setSize(900, 600);
		//点关闭的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小不可变
		setResizable(false);
		//窗口居中默认居中
		setLocationRelativeTo(null);
		//窗口可见
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new jframeTest();
	}

}
