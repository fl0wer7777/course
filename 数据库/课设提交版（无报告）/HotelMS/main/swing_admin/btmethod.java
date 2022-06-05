package swing_admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.java.gui.MainView;
import com.java.impl.HTServiceImpl2;
import com.java.impl.HTServiceImpl3;
import com.java.req.HTRequest;
import com.java.res.TableDTO;
import com.java.service.HTService;
import com.java.service.HTService3;



public class btmethod {
	public btmethod() {
		// TODO Auto-generated constructor stub
	}

	public static void insertUI() {
		// TODO Auto-generated method stub
		JFrame inframe = new JFrame("��������");
		
		JPanel centerPanel = new JPanel();
		inframe.add(centerPanel);
		
		
		JLabel l2 = new JLabel("����״̬");
		JTextField T2 = new JTextField("����ס");
		T2.setPreferredSize(new Dimension(200,30));
		centerPanel.add(l2);
		centerPanel.add(T2);
		
		JLabel l3 = new JLabel("�˿����֤��");
		JTextField T3 = new JTextField("116593199409193331");
		T3.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l3);
		centerPanel.add(T3);
		
		JLabel l4 = new JLabel("�����");
		JTextField T4 = new JTextField("000010");
		T4.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l4);
		centerPanel.add(T4);
		
		JLabel l5 = new JLabel("��סʱ��");
		JTextField T5 = new JTextField("2021-07-07");
		T5.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l5);
		centerPanel.add(T5);
		
		JLabel l6 = new JLabel("���ʱ��");
		JTextField T6 = new JTextField("2021-07-07");
		T6.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l6);
		centerPanel.add(T6);
		
		JLabel l7 = new JLabel("�ܽ��");
		JTextField T7 = new JTextField("11");
		T7.setPreferredSize(new Dimension(230,30));
		centerPanel.add(l7);
		centerPanel.add(T7);
		
		JLabel l8 = new JLabel("������Ա");
		JTextField T8 = new JTextField("lx");
		T8.setPreferredSize(new Dimension(230,30));
		centerPanel.add(l8);
		centerPanel.add(T8);
		
		JLabel l9 = new JLabel("��ע");
		JTextField T9 = new JTextField("��");
		T9.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l9);
		centerPanel.add(T9);
		
		JLabel l10 = new JLabel("�µ�ʱ��");
		JTextField T10 = new JTextField("2021-07-07");
		T10.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l10);
		centerPanel.add(T10);
		
		JButton jb = new JButton("�ύ");
		centerPanel.add(jb);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s =T7.getText();
				System.out.println(s);
				Integer num1 =  Integer.valueOf(s);
				if(sqlmethod.in_submit(T2.getText(),T3.getText(),T4.getText(),T5.getText(),T6.getText(),
						num1,T8.getText(),T9.getText(),T10.getText())) {
					JOptionPane.showMessageDialog(inframe, "����ɹ�");
					//reLoad();
				}else {
					JOptionPane.showMessageDialog(inframe, "����ʧ��");
				}
			}
		});
		
		
		inframe.setSize(600, 300);
		//��رյ�ʱ���˳�����
		
		//���ڴ�С���ɱ�
		inframe.setResizable(false);
		//���ھ���Ĭ�Ͼ���
		inframe.setLocationRelativeTo(null);
		inframe.setVisible(true);
	}


	public static void alterUI() {
		// TODO Auto-generated method stub
		JFrame inframe = new JFrame("�޸Ķ���");
		
		JPanel centerPanel = new JPanel();
		inframe.add(centerPanel);
		
		JLabel l1 = new JLabel("�޸Ķ������");
		JTextField T1 = new JTextField("800");
		T1.setPreferredSize(new Dimension(220,30));
		centerPanel.add(l1);
		centerPanel.add(T1);
		
		JButton jb = new JButton("�ύ");
		centerPanel.add(jb);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sqlmethod.al_submit(T1.getText());				
				inframe.dispose();
				
			}
		});
		
		inframe.setSize(500, 100);
		//��رյ�ʱ���˳�����

		//���ڴ�С���ɱ�
		inframe.setResizable(false);
		//���ھ���Ĭ�Ͼ���
		inframe.setLocationRelativeTo(null);
		inframe.setVisible(true);
	}
	
	public static void searchUI() {
		// TODO Auto-generated method stub
		JFrame inframe = new JFrame("���Ҷ���");
		
		JPanel centerPanel = new JPanel();
		inframe.add(centerPanel);
		
		JLabel l = new JLabel("�������");
		JTextField T = new JTextField("800");
		T.setPreferredSize(new Dimension(220,30));
		
		centerPanel.add(l);
		centerPanel.add(T);	
		
		JButton jb = new JButton("��ѯ");
		centerPanel.add(jb);

		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					JFrame inframe2 = new JFrame("���Ҷ���");
					Container contentPane = inframe2.getContentPane();
					
					HTService3 hTService3 = new HTServiceImpl3();
					HTRequest request3 = new HTRequest();
					TableDTO tableDTO3 = hTService3.getHT(request3,T.getText());
					Vector<Vector<Object>> data3 = tableDTO3.getData();
					System.out.println(data3.get(0).get(0));
					//jTable�Ͷ����Table����
					HTModel3 hTModel3 = HTModel3.assmbleModel3(data3);
					JTable jTable3 = new JTable(hTModel3);
					//����ÿһ����Ⱦ��ʽ
					Vector<String> cols3 = hTModel3.getColumns3();
					HTCellRender1 render3 = new HTCellRender1();
					for(int i=0;i < cols3.size();i++) {
						TableColumn column3 = jTable3.getColumn(cols3.get(i));
						column3.setCellRenderer(render3);
					}
					//jTable����jPanel�У�Ĭ�ϲ�չʾ��ͷ������JScrollPane�У�Ĭ��չʾ��
					JScrollPane jScrollPane3 = new JScrollPane(jTable3);
					
					contentPane.add(jScrollPane3);
					inframe2.setSize(800, 600);
					inframe.setLocationRelativeTo(null);
					inframe2.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		

		inframe.setSize(400, 200);
		//��رյ�ʱ���˳�����
	
		//���ڴ�С���ɱ�
		inframe.setResizable(false);
		//���ھ���Ĭ�Ͼ���
		inframe.setLocationRelativeTo(null);
		inframe.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		insertUI();
		//alterUI();
		//searchUI();
	}
	
	static class HTCellRender1 extends DefaultTableCellRenderer {
		
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
	
	static class HTModel3 extends DefaultTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		static Vector<String> columns = new Vector<>();
		static {
			columns.addElement("orderNumber");
			columns.addElement("orderStatus");
			columns.addElement("customerName");
			columns.addElement("roomNumber");
			columns.addElement("roomType");
			columns.addElement("orderTime");
			columns.addElement("checkInTime");
			columns.addElement("checkOutTime");
			columns.addElement("customerPhoneNumber");
			columns.addElement("totalMoney");
		}
		
		private HTModel3() {
			super(null,columns);
		}
		
		private static HTModel3 hTModel3 = new HTModel3();
		
		public static HTModel3 assmbleModel3(Vector<Vector<Object>> data) {
			hTModel3.setDataVector(data,columns);
			
			return hTModel3;
		}
		
		public static void updateModel3(Vector<Vector<Object>> data) {
			hTModel3.setDataVector(data,columns);
			
		}
		
		public static Vector<String> getColumns3(){
			return columns;
		}
		
		public boolean isCellEditable3(int row, int column) {
			return false;
			}
		}

	
}
