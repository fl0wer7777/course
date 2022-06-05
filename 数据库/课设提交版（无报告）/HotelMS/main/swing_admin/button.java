package swing_admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.java.gui.MainView;

public class button {
	JButton insert = new JButton();
	JButton desert = new JButton();
	JButton search = new JButton();
	MainView mainView = new MainView();
	
	public button() {
		// TODO Auto-generated constructor stub
		insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.insertUI();
			}
		});
		
		desert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.alterUI();
			}
		});
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.searchUI();
			}
		});
	
	}
}
