package com.java.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class JTableTest extends JFrame{

	public JTableTest() {
		super("table");
		
		Vector<Vector<Object>> data = new Vector<>();
		Vector<Object> row = new Vector<>();
		row.addElement("1");
		row.addElement("1");
		row.addElement("1");
		row.addElement("1");
		row.addElement("1");
		Vector<Object> row2 = new Vector<>();
		row2.addElement("1");
		row2.addElement("1");
		row2.addElement("1");
		row2.addElement("1");
		row2.addElement("1");
		Vector<Object> row3 = new Vector<>();
		row3.addElement("1");
		row3.addElement("1");
		row3.addElement("1");
		row3.addElement("1");
		row3.addElement("1");
		Vector<Object> row4 = new Vector<>();
		row4.addElement("1");
		row4.addElement("1");
		row4.addElement("1");
		row4.addElement("1");
		row4.addElement("1");
		Vector<Object> row5 = new Vector<>();
		row5.addElement("1");
		row5.addElement("1");
		row5.addElement("1");
		row5.addElement("1");
		row5.addElement("1");
		Vector<Object> row6 = new Vector<>();
		row6.addElement("1");
		row6.addElement("1");
		row6.addElement("1");
		row6.addElement("1");
		row6.addElement("1");
		Vector<Object> row7 = new Vector<>();
		row7.addElement("1");
		row7.addElement("1");
		row7.addElement("1");
		row7.addElement("1");
		row7.addElement("1");
		
		for(int i=0;i<20;i++) {
			data.addElement(row3);
		}
		
		
		HTModel hTModel = HTModel.assmbleModel(data);
		//jTable和定义的Table关联
		JTable jTable = new JTable(hTModel);
		//设置表头
		JTableHeader tableHeader =  jTable.getTableHeader();
		tableHeader.setFont(new Font(null,Font.PLAIN,14));
		tableHeader.setForeground(Color.RED);
		//设置表体
		jTable.setFont(new Font(null,Font.PLAIN,14));
		jTable.setForeground(Color.blue);
		jTable.setGridColor(Color.darkGray);
		jTable.setRowHeight(30);
		//设置多行选择
		jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//设置每一列渲染方式
		Vector<String> cols = hTModel.getColumns();
		HTCellRender render = new HTCellRender();
		for(int i=0;i < cols.size();i++) {
			TableColumn column = jTable.getColumn(cols.get(i));
			column.setCellRenderer(render);
		}

		
		//jTable放入jPanel中，默认不展示表头，放入JScrollPane中，默认展示。
		Container contentPane = getContentPane();
		JScrollPane jScrollPane = new JScrollPane(jTable);
		contentPane.add(jScrollPane);
		
		
		
		//width:600, height:400
		setSize(600, 400);
		//点关闭的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小不可变
		setResizable(false);
		//窗口居中默认居中
		setLocationRelativeTo(null);
		//窗口可见
		setVisible(true);
	}
	public static void main(String[] args) {
		new JTableTest();

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
		columns.addElement("列一");
		columns.addElement("列二");
		columns.addElement("列三");
		columns.addElement("列四");
		columns.addElement("列五");
	}
	
	private HTModel() {
		super(null,columns);
	}
	
	private static HTModel hTModel = new HTModel();
	
	public static HTModel assmbleModel(Vector<Vector<Object>> data) {
		hTModel.setDataVector(data,columns);
		
		return hTModel;
	}
	
	public static Vector<String> getColumns(){
		return columns;
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}

}
