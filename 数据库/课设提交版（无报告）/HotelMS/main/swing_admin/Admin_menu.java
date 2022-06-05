package swing_admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.java.gui.LoginViewVer2;
import com.java.gui.MainView;
import com.java.impl.HTServiceImpl;
import com.java.impl.HTServiceImpl2;
import com.java.req.HTRequest;
import com.java.res.TableDTO;
import com.java.service.HTService;

public class Admin_menu{
	JPanel northPanel = new JPanel();
	JButton addBtn = new JButton("增加");
	JButton updateBtn = new JButton("更新");
	JButton searchBtn = new JButton("查找");
	HTModel hTModel;
	JTable jTable;
	JScrollPane jScrollPane;
	
	JPanel northPanel2 = new JPanel();
	JButton addBtn2 = new JButton("增加");
	JButton updateBtn2 = new JButton("更新");
	JButton searchBtn2 = new JButton("查找");
	HTModel2 hTModel2;
	JTable jTable2;
	JScrollPane jScrollPane2;
	
	private static JFrame jf = new JFrame();
	public Admin_menu(String title) {
		
		CardLayout cards = new CardLayout();	
		jf=new JFrame(title);
		jf.setPreferredSize(new Dimension(1000,600));	
		jf.setLayout(null);
		Container con1 = jf.getContentPane();
		con1.setLayout(cards);
		
		JPanel j1 = new JPanel();	
		j1.setLayout(new BorderLayout());	
		con1.add(j1);	
		northPanel.add(addBtn);
		northPanel.add(updateBtn);
		northPanel.add(searchBtn);
		j1.add(northPanel,BorderLayout.NORTH);
		HTService hTService = new HTServiceImpl();
		HTRequest request = new HTRequest();
		TableDTO tableDTO = hTService.getHT(request);
		Vector<Vector<Object>> data = tableDTO.getData();
		//jTable和定义的Table关联
		hTModel = HTModel.assmbleModel(data);
		jTable = new JTable(hTModel);
		//设置每一列渲染方式
		Vector<String> cols = hTModel.getColumns();
		HTCellRender render = new HTCellRender();
		for(int i=0;i < cols.size();i++) {
			TableColumn column = jTable.getColumn(cols.get(i));
			column.setCellRenderer(render);
		}
		//jTable放入jPanel中，默认不展示表头，放入JScrollPane中，默认展示。
		jScrollPane = new JScrollPane(jTable);
		j1.add(jScrollPane);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.insertUI();
			}

		});
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.alterUI();
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.searchUI();
			}
		});
		
		
		JPanel j2 = new JPanel();	
		j2.setLayout(new BorderLayout());	
		con1.add(j2);	
		northPanel2.add(addBtn2);
		northPanel2.add(updateBtn2);
		northPanel2.add(searchBtn2);
		j2.add(northPanel2,BorderLayout.NORTH);
		HTService hTService2 = new HTServiceImpl2();
		HTRequest request2 = new HTRequest();
		TableDTO tableDTO2 = hTService2.getHT(request2);
		Vector<Vector<Object>> data2 = tableDTO2.getData();
		System.out.println(data2.get(0).get(0));
		//jTable和定义的Table关联
		hTModel2 = HTModel2.assmbleModel2(data2);
		jTable2 = new JTable(hTModel2);
		//设置每一列渲染方式
		Vector<String> cols2 = hTModel2.getColumns2();
		HTCellRender render2 = new HTCellRender();
		for(int i=0;i < cols2.size();i++) {
			TableColumn column2 = jTable2.getColumn(cols2.get(i));
			column2.setCellRenderer(render2);
		}
		//jTable放入jPanel中，默认不展示表头，放入JScrollPane中，默认展示。
		jScrollPane2 = new JScrollPane(jTable2);
		j2.add(jScrollPane2);
		
		
		addBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod2.insertUI();
			}
		});
		
		updateBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod2.alterUI();
			}
		});
		
		searchBtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod2.searchUI();
			}
		});
		
		con1.add(new Panel());
		con1.add(new Panel());
		//
		JMenuBar menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
			
		JMenu fileMenu = new JMenu("信息表 ");
		menuBar.add(fileMenu);
		JMenuItem jm1 = new JMenuItem("订单信息");
		JMenuItem jm2 = new JMenuItem("客房信息");
		JMenuItem jm3 = new JMenuItem("顾客信息");
		JMenuItem jm4 = new JMenuItem("员工信息");
		fileMenu.add(jm1);
		fileMenu.add(jm2);
		fileMenu.add(jm3);
		fileMenu.add(jm4);
		jm1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.first(con1);
				System.out.println("信息表已被打开！");			
			}
		});
		
		jm2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.first(con1);
				cards.next(con1);
				System.out.println("222");
			}
		});
		

		jm3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.last(con1);
				cards.previous(con1);
				System.out.println("333");
			}
		});
		
		jm4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.last(con1);
				System.out.println("333");
			}
		});
		JMenu fileMenu1 = new JMenu("账户 ");
		menuBar.add(fileMenu1);
		JMenuItem jm5 = new JMenuItem("退出登录");
		fileMenu1.add(jm5);
		
		jm5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				new LoginViewVer2();
				System.out.println("333");
			}
		});
		
		
		//width:600, height:400
		jf.setSize(1200, 800);
		//点关闭的时候退出程序

		//窗口大小不可变
		jf.setResizable(true);
		//窗口居中默认居中
		jf.setLocationRelativeTo(null);
		//窗口可见
		jf.setVisible(true);
		
	}
		
	
	public static void main(String[] args) {
		new Admin_menu("管理员XX登录");
	}

	public static JFrame returnJFrame() {
		System.out.println("done");
		return jf;
	}
	
	class HTCellRender extends DefaultTableCellRenderer {
		
		@Override
		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected,boolean hasFocus,int row,int column) {
			if (row % 2 == 0) {
				setBackground(Color.LIGHT_GRAY);
			}else {
				setBackground(Color.WHITE);
			}
			setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}
	
	//自定义TableModel
	static class HTModel extends DefaultTableModel{
		static Vector<String> columns = new Vector<>();
		static {
			columns.addElement("orderNumber");
			columns.addElement("orderStatus");
			columns.addElement("customerIDCard");
			columns.addElement("roomNumber");
			columns.addElement("checkInTime");
			columns.addElement("checkOutTime");
			columns.addElement("totalMoney");
			columns.addElement("waiterID");
			columns.addElement("remarks");
			columns.addElement("orderTime");
		}
		
		private HTModel() {
			super(null,columns);
		}
		
		private static HTModel hTModel = new HTModel();
		
		public static HTModel assmbleModel(Vector<Vector<Object>> data) {
			hTModel.setDataVector(data,columns);
			
			return hTModel;
		}
		
		public static void updateModel(Vector<Vector<Object>> data) {
			hTModel.setDataVector(data,columns);
			
		}
		
		public static Vector<String> getColumns(){
			return columns;
		}
		
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

static class HTModel2 extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Vector<String> columns = new Vector<>();
	static {
		columns.addElement("roomNumber");
		columns.addElement("roomType");
		columns.addElement("roomStatus");
		columns.addElement("remarks");
		columns.addElement("price");
		columns.addElement("desc");
	}
	
	private HTModel2() {
		super(null,columns);
	}
	
	private static HTModel2 hTModel2 = new HTModel2();
	
	public static HTModel2 assmbleModel2(Vector<Vector<Object>> data) {
		hTModel2.setDataVector(data,columns);
		
		return hTModel2;
	}
	
	public static void updateModel2(Vector<Vector<Object>> data) {
		hTModel2.setDataVector(data,columns);
		
	}
	
	public static Vector<String> getColumns2(){
		return columns;
	}
	
	public boolean isCellEditable2(int row, int column) {
		return false;
		}
	}

}
/*public void reLoad() {
	HTService hTService = new HTServiceImpl();
	HTRequest request = new HTRequest();

	TableDTO tableDTO = hTService.getHT(request);
	Vector<Vector<Object>> data = tableDTO.getData();
	HTModel.updateModel(data);
	
}*/
