package swing;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class demo 
{
	public static void createGUI()
	{
//		��������
		Myframe frame = new Myframe("Swing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
//		��������
		frame.setSize(400, 300);
		
		frame.setVisible(true);
	
	}
	
	
	
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				createGUI();
			}
		});
	
	
	}
}

