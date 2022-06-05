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
	JButton addBtn = new JButton("����");
	JButton delBtn = new JButton("ɾ��");
	JButton updateBtn = new JButton("����");
	JButton searchBtn = new JButton("����");
	
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
		super("ͼ��");
		
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
		
		//jTable�Ͷ����Table����
		hTModel = HTModel.assmbleModel(data);
		jTable = new JTable(hTModel);

		//����ÿһ����Ⱦ��ʽ
		Vector<String> cols = hTModel.getColumns();
		HTCellRender render = new HTCellRender();
		for(int i=0;i < cols.size();i++) {
			TableColumn column = jTable.getColumn(cols.get(i));
			column.setCellRenderer(render);
		}
		//jTable����jPanel�У�Ĭ�ϲ�չʾ��ͷ������JScrollPane�У�Ĭ��չʾ��
		jScrollPane = new JScrollPane(jTable);
		contentPane.add(jScrollPane);
		jScrollPane.updateUI();
		
		
		//width:600, height:400
		setSize(1200, 600);
		//��رյ�ʱ���˳�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ڴ�С���ɱ�
		setResizable(false);
		//���ھ���Ĭ�Ͼ���
		setLocationRelativeTo(null);
		//���ڿɼ�
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

	
//�Զ���TableModel
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
