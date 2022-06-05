package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorFrame extends JFrame{
	
	
	public ErrorFrame(String content)
	{
		JFrame error = new JFrame("±ö¹ÝÏµÍ³--Error!");
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		JLabel label = new JLabel(content);
		contentPane.add(label,BorderLayout.CENTER);
	}
		
}
