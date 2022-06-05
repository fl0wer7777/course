package com.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.java.impl.HTServiceImpl;
import com.java.req.HTRequest;
import com.java.res.TableDTO;
import com.java.service.HTService;

import swing_admin.btmethod;

public class MainView extends JFrame{
	JPanel northPanel = new JPanel();
	JButton addBtn = new JButton("增加");
	JButton delBtn = new JButton("删除");
	JButton updateBtn = new JButton("更新");
	JButton searchBtn = new JButton("查找");
	
	JPanel southPanel = new JPanel();
	HTModel hTModel;
	JTable jTable;
	Container contentPane = getContentPane();
	JScrollPane jScrollPane;
	
	private void layoutNorth(Container contentPane) {
		northPanel.add(addBtn);
		northPanel.add(delBtn);
		northPanel.add(updateBtn);
		northPanel.add(searchBtn);
		contentPane.add(northPanel,BorderLayout.NORTH);
	}
	
	public void reLoad() {
		HTService hTService = new HTServiceImpl();
		HTRequest request = new HTRequest();

		TableDTO tableDTO = hTService.getHT(request);
		Vector<Vector<Object>> data = tableDTO.getData();
		HTModel.updateModel(data);
		
	}
	
	public MainView() {
		super("图表");
		
		MainView mainView = this;
		layoutNorth(contentPane);
		layoutSouth(contentPane);
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btmethod.insertUI(mainView);
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
				reLoad();
			}
		});
		
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
		contentPane.add(jScrollPane);
		jScrollPane.updateUI();
		
		
		//width:600, height:400
		setSize(1200, 600);
		//点关闭的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小不可变
		setResizable(false);
		//窗口居中默认居中
		setLocationRelativeTo(null);
		//窗口可见
		setVisible(true);
	}

	private void layoutSouth(Container contentPane) {
		contentPane.add(southPanel,BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new MainView();
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

}
