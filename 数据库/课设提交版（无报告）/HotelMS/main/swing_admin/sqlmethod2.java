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
			System.out.println("�������سɹ���");
//      �������ݿ�
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
				"root","zzcfgdfcvb123,");
			System.out.println("���ݿ����ӳɹ���");
//      ����ִ�л����Ķ���
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
			System.out.println("�������سɹ���");
//      �������ݿ�
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
				"root","zzcfgdfcvb123,");
			System.out.println("���ݿ����ӳɹ���");
//      ����ִ�л����Ķ���
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
		JFrame inframe = new JFrame("���ķ�����Ϣ");
		JPanel centerPanel = new JPanel();
		inframe.add(centerPanel);
		
		JLabel l = new JLabel("������"+string);
		centerPanel.add(l);
		
		
		JLabel l2 = new JLabel("����״̬");
		JTextField T2 = new JTextField(s2);
		T2.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l2);
		centerPanel.add(T2);
		
		JLabel l3 = new JLabel("��ע");
		JTextField T3;
		if("null".equals(s3)) {
			T3 = new JTextField();
		}else {
			T3 = new JTextField(s3);
		}
		
		T3.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l3);
		centerPanel.add(T3);
		
		
		JButton jb = new JButton("����");
		centerPanel.add(jb);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
						System.out.println("�������سɹ���");
//	      �������ݿ�
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
								"root","zzcfgdfcvb123,");
					System.out.println("���ݿ����ӳɹ���");
//	      ����ִ�л����Ķ���
					Statement statement = conn.createStatement();
					statement.execute("Update room set roomStatus= '"+ T2.getText()+"' where roomNumber = '"+string+"'");
					statement.execute("Update room set remarks= '"+ T3.getText()+"' where roomNumber = '"+string+"'");
					JOptionPane.showMessageDialog(inframe, "���ĳɹ�");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				
			}
		});
		
		inframe.setSize(500, 300);
		//��رյ�ʱ���˳�����

		//���ڴ�С���ɱ�
		inframe.setResizable(false);
		//���ھ���Ĭ�Ͼ���
		inframe.setLocationRelativeTo(null);
		inframe.setVisible(true);
		
		
	
		
		

	}
		
}
