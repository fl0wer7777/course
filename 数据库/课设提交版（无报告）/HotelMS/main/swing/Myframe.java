package swing;

import com.tjl.jdbc.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Myframe extends JFrame{
	
	JButton button = new JButton("test");
	JLabel label = new JLabel("hello,world");
	JTextField textField1 = new JTextField(16);
	JTextField textField2 = new JTextField(16);
	public Myframe(String title)
	{
		super(title);
		
//		��岼��
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		
//		��ӿؼ�
		contentPane.add(label);
		contentPane.add(button);
		
		MyButtonListener mylistener = new MyButtonListener();
		button.addActionListener(mylistener);
		JLabel label1 = new JLabel("�û�");
		
		JLabel label2 = new JLabel("����");
		
		contentPane.add(label1);
		contentPane.add(textField1);
		contentPane.add(label2);
		contentPane.add(textField2);
		
//		��������	
	}
		public void showData(){
			System.out.println("ִ�гɹ�");
			String S1 = textField1.getText();
			System.out.println("ִ�гɹ�");
			String S2 = textField2.getText();
			System.out.println("ִ�гɹ�");
			//JDBC.Login(S1,S2);
		}
		
	
		private class MyButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("��ť�����");
				System.out.println(textField1.getText());
				
				//showData(username, password);
				showData();
			}
			
		}
	
}
