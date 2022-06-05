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
	//界面组件
	JLabel nameLabel = new JLabel("宾馆管理系统",JLabel.CENTER);
	
	SpringLayout springLayout = new SpringLayout();//流式布局
	JPanel centerPanel = new JPanel(springLayout);
	
	JLabel userNameLabel = new JLabel("用户名：");
	JTextField userTxt = new JTextField();
	JLabel pwdLabel = new JLabel("密码：");
	JPasswordField pwdField = new JPasswordField();
	JComboBox<String> usertype = new JComboBox<String>();
	String user_type = "系统管理员";
	JLabel combobox = new JLabel("选择登录方式");
	JButton loginBtn = new JButton("登录");
	
	//系统托盘
	SystemTray systemTray;
	TrayIcon trayIcon;
	
	public LoginViewVer2() {
		super("宾馆管理系统");
		Container contentPane = myComponent();
		// 弹簧布局	
		layoutLogin(contentPane);
		layoutTuoPan();

		//width:600, height:400
		setSize(600, 400);
		//点关闭的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小不可变
		setResizable(false);
		//窗口居中默认居中
		setLocationRelativeTo(null);
		//窗口可见
		setVisible(true);
		
	}

	private void layoutTuoPan() {
		//判断系统是否支持系统托盘
		if(SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();
			//自定义图标
			URL imgUrl = LoginViewVer2.class.getResource("tool.jpg");
			trayIcon = new TrayIcon(new ImageIcon(imgUrl).getImage());
			//设置托盘图片大小自动缩放
			trayIcon.setImageAutoSize(true);
			try {
				systemTray.add(trayIcon);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//增加最小化时销毁资源
			this.addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					LoginViewVer2.this.dispose();
				}
			});
			//托盘事件监听
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
		//userNameLabel布局
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
	
		nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
		nameLabel.setPreferredSize(new Dimension(0, 80));
		
		Font centerFont = new Font("楷体",Font.PLAIN, 20);
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
		
		usertype.addItem("系统管理员");
		usertype.addItem("员工");
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
		System.out.println("按钮被点击");
		System.out.println(userTxt.getText());
		System.out.println(pwdField.getText());
		boolean flag = LoginHandler2.Login(userTxt.getText(),pwdField.getText(),user_type);
		if(flag) {
			JOptionPane.showMessageDialog(this, "登录成功");
			this.dispose();
			new Admin_menu("宾馆管理系统");
		}else {
			JOptionPane.showMessageDialog(this, "用户名或密码错误");
		}
	}

}
