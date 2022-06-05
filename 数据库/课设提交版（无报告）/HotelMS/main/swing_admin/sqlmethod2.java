package swing_admin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class sqlmethod2 {
	public static void main(String[] args) {
		
	}

	public static boolean in_submit(String string, String string2, String string3, String string4) {
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
			statement.execute("insert into room VALUES ( '"+string+"' , '"+string2+"' , '"+string3+"' , '"+string4+"' )");
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static void al_submit(String string) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功！");
//      连接数据库
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
				"root","zzcfgdfcvb123,");
			System.out.println("数据库连接成功！");
//      创建执行环境的对象
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from room where roomNumber ="+string);
			while(result.next()) {
			String s1 = result.getString("roomNumber");
			System.out.println(s1);
			String s2 = result.getString("roomType");
			System.out.println(s2);
			String s3 = result.getString("roomStatus");
			System.out.println(s3);
			String s4 = result.getString("remarks");
			System.out.println(s4);
			alterframe(string, s2, s3, s4);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void alterframe(String string, String s1, String s2, String s3) {
		// TODO Auto-generated method stub
		JFrame inframe = new JFrame("更改房间信息");
		JPanel centerPanel = new JPanel();
		inframe.add(centerPanel);
		
		JLabel l = new JLabel("房间编号"+string);
		centerPanel.add(l);
		
		
		JLabel l2 = new JLabel("房间状态");
		JTextField T2 = new JTextField(s2);
		T2.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l2);
		centerPanel.add(T2);
		
		JLabel l3 = new JLabel("备注");
		JTextField T3;
		if("null".equals(s3)) {
			T3 = new JTextField();
		}else {
			T3 = new JTextField(s3);
		}
		
		T3.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l3);
		centerPanel.add(T3);
		
		
		JButton jb = new JButton("更改");
		centerPanel.add(jb);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
						System.out.println("驱动加载成功！");
//	      连接数据库
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
								"root","zzcfgdfcvb123,");
					System.out.println("数据库连接成功！");
//	      创建执行环境的对象
					Statement statement = conn.createStatement();
					statement.execute("Update room set roomStatus= '"+ T2.getText()+"' where roomNumber = '"+string+"'");
					statement.execute("Update room set remarks= '"+ T3.getText()+"' where roomNumber = '"+string+"'");
					JOptionPane.showMessageDialog(inframe, "更改成功");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				
			}
		});
		
		inframe.setSize(500, 300);
		//点关闭的时候退出程序

		//窗口大小不可变
		inframe.setResizable(false);
		//窗口居中默认居中
		inframe.setLocationRelativeTo(null);
		inframe.setVisible(true);
		
		
	
		
		

	}
		
}
