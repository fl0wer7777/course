package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Waier_menu extends JFrame {
	public Waier_menu(String title){
		super(title);
		
		JPanel menu = new JPanel();
		this.setContentPane(menu);
		menu.setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("客房信息 ");
		menuBar.add(fileMenu);
		fileMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JMenu fileMenu1 = new JMenu("订单信息 ");
		menuBar.add(fileMenu1);
		
		JMenu fileMenu2 = new JMenu("个人信息 ");
		menuBar.add(fileMenu2);
		
		//width:600, height:400
		setSize(1000, 800);
		//点关闭的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小不可变
		setResizable(true);
		//窗口居中默认居中
		setLocationRelativeTo(null);
		//窗口可见
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Waier_menu("员工XX登录");
	}
}
