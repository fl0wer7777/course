package com.java.test;

import java.awt.Container;
import javax.swing.*;

public class jframeTest extends JFrame{
	
	JButton btn;
	
	public jframeTest() {
		super("��¼����");//title

		//��ť,�����ȷŵ�������ٷ��봰��
		btn = new JButton("��¼");
		btn.setSize(100,100);
		Container contentPane = getContentPane();
		contentPane.add(btn);
		//width:900, height:600
		setSize(900, 600);
		//��رյ�ʱ���˳�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ڴ�С���ɱ�
		setResizable(false);
		//���ھ���Ĭ�Ͼ���
		setLocationRelativeTo(null);
		//���ڿɼ�
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new jframeTest();
	}

}
