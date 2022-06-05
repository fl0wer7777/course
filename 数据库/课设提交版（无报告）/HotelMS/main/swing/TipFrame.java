package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TipFrame extends JFrame{
	public TipFrame(String content)
	{
		JFrame tFrame = new JFrame("±ö¹ÝÏµÍ³");
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		JLabel label = new JLabel(content);
		
		contentPane.add(label,BorderLayout.CENTER);
		
	}
}
