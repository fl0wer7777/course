package com.java.gui;

import javax.swing.*;

import com.java.handler.*;

import swing_admin.Admin_menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoginViewVer2 extends JFrame{
	//�������
	JLabel nameLabel = new JLabel("���ݹ���ϵͳ",JLabel.CENTER);
	
	SpringLayout springLayout = new SpringLayout();//��ʽ����
	JPanel centerPanel = new JPanel(springLayout);
	
	JLabel userNameLabel = new JLabel("�û�����");
	JTextField userTxt = new JTextField();
	JLabel pwdLabel = new JLabel("���룺");
	JPasswordField pwdField = new JPasswordField();
	JComboBox<String> usertype = new JComboBox<String>();
	String user_type = "ϵͳ����Ա";
	JLabel combobox = new JLabel("ѡ���¼��ʽ");
	JButton loginBtn = new JButton("��¼");
	
	//ϵͳ����
	SystemTray systemTray;
	TrayIcon trayIcon;
	
	public LoginViewVer2() {
		super("���ݹ���ϵͳ");
		Container contentPane = myComponent();
		// ���ɲ���	
		layoutLogin(contentPane);
		layoutTuoPan();

		//width:600, height:400
		setSize(600, 400);
		//��رյ�ʱ���˳�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ڴ�С���ɱ�
		setResizable(false);
		//���ھ���Ĭ�Ͼ���
		setLocationRelativeTo(null);
		//���ڿɼ�
		setVisible(true);
		
	}

	private void layoutTuoPan() {
		//�ж�ϵͳ�Ƿ�֧��ϵͳ����
		if(SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();
			//�Զ���ͼ��
			URL imgUrl = LoginViewVer2.class.getResource("tool.jpg");
			trayIcon = new TrayIcon(new ImageIcon(imgUrl).getImage());
			//��������ͼƬ��С�Զ�����
			trayIcon.setImageAutoSize(true);
			try {
				systemTray.add(trayIcon);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//������С��ʱ������Դ
			this.addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					LoginViewVer2.this.dispose();
				}
			});
			//�����¼�����
			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int clickCount = e.getClickCount();
					if (clickCount == 1) {
						LoginViewVer2.this.setExtendedState(JFrame.NORMAL);
					}
					LoginViewVer2.this.setVisible(true);
				}
			});
		}
	}

	private void layoutLogin(Container contentPane) {
		Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userTxt)), Spring.constant(20));
		int offsetX = childWidth.getValue() / 2;
		//combobox
		springLayout.putConstraint(SpringLayout.WEST,combobox,-offsetX-20,SpringLayout.HORIZONTAL_CENTER,centerPanel);
		springLayout.putConstraint(SpringLayout.NORTH,combobox,20,SpringLayout.NORTH,centerPanel);
		//usertype
		springLayout.putConstraint(SpringLayout.WEST,usertype,20,SpringLayout.EAST,combobox);
		springLayout.putConstraint(SpringLayout.NORTH,usertype,0,SpringLayout.NORTH,combobox);
		//userNameLabel����
		springLayout.putConstraint(SpringLayout.EAST,userNameLabel,0,SpringLayout.EAST,combobox);
		springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.SOUTH,combobox);
		//userTxt
		springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
		springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
		//pwdLabel
		springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
		springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
		//pwdField
		springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
		springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
		//loginBtn
		springLayout.putConstraint(SpringLayout.WEST,loginBtn,100,SpringLayout.WEST,pwdLabel);
		springLayout.putConstraint(SpringLayout.NORTH,loginBtn,30,SpringLayout.SOUTH,pwdLabel);
	
		
		contentPane.add(nameLabel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
	}

	private Container myComponent() {
		Container contentPane = getContentPane();
	
		nameLabel.setFont(new Font("�����п�",Font.PLAIN,40));
		nameLabel.setPreferredSize(new Dimension(0, 80));
		
		Font centerFont = new Font("����",Font.PLAIN, 20);
		userNameLabel.setFont(centerFont);
		userTxt.setPreferredSize(new Dimension(200,30));
		pwdLabel.setFont(centerFont);
		pwdField.setPreferredSize(new Dimension(200,30));
		usertype.setPreferredSize(new Dimension(100,30));
		combobox.setFont(centerFont);
		loginBtn.setFont(centerFont);
		LoginButtonListener mylistener = new LoginButtonListener();
		loginBtn.addActionListener(mylistener);
		
		centerPanel.add(userNameLabel);
		centerPanel.add(userTxt);
		centerPanel.add(pwdLabel);
		centerPanel.add(pwdField);
		centerPanel.add(usertype);
		centerPanel.add(combobox);
		centerPanel.add(loginBtn);
		
		usertype.addItem("ϵͳ����Ա");
		usertype.addItem("Ա��");
		usertype.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updatausertype();
			}
		});
		return contentPane;
	}


	
	public static void main(String[] args) {
			new LoginViewVer2();

	}
	
	protected void updatausertype() {
		// TODO Auto-generated method stub
		user_type = (String)usertype.getSelectedItem();
		System.out.println(user_type);
	}
	
	private class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			// TODO Auto-generated method stub
			Login();
		}
		
	}
	
	private void Login() {
		System.out.println("��ť�����");
		System.out.println(userTxt.getText());
		System.out.println(pwdField.getText());
		boolean flag = LoginHandler2.Login(userTxt.getText(),pwdField.getText(),user_type);
		if(flag) {
			JOptionPane.showMessageDialog(this, "��¼�ɹ�");
			this.dispose();
			new Admin_menu("���ݹ���ϵͳ");
		}else {
			JOptionPane.showMessageDialog(this, "�û������������");
		}
	}

}
