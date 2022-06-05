package swing_admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class button2 {
	JButton insert2 = new JButton();
	JButton desert2 = new JButton();
	JButton search2 = new JButton();
	
	public button2() {
		// TODO Auto-generated constructor stub
		insert2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod2.insertUI();
			}
		});
		
		desert2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod2.alterUI();
			}
		});
		
		search2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod2.searchUI();
			}
		});
	
	}
}
