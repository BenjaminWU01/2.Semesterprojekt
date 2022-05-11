package ui;

import controller.*;
import model.Order;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class OrderWindow {

	private static JFrame f;
	private static JScrollPane p;
	private static JScrollPane p2;
	private static JLabel l2;
	private static JTable t;
	private static JTable t2;
	private static String[] columnNames = { "OrderNo", "Name", "Size", "Quantity", "Price" };
	private static Object[][] data = { { "1234", "Højhæle", "L", Integer.valueOf(5), Float.valueOf(300) },
			{ "1234", "Højhæle", "L", Integer.valueOf(5), Float.valueOf(300) },
			{ "1234", "Højhæle", "L", Integer.valueOf(5), Float.valueOf(300) } };
	private static JPanel panel;
	private static JPanel panel_1;
	private static JLabel l;
	private static JButton b;
	private static JButton b2;

	public static void main(String[] args) {

		f = new JFrame();
		t = new JTable(data, columnNames);
		t2 = new JTable(data, columnNames);
		p = new JScrollPane(t);
		p2 = new JScrollPane(t2);
		panel = new JPanel();
		panel_1 = new JPanel();
		l = new JLabel("Waiting orders:");
		l2 = new JLabel("Current orders: ");
		b = new JButton("Begin order");
		b2 = new JButton("Finish order");

		f.getContentPane().add(panel);
		f.getContentPane().add(panel_1);
		panel.add(l);
		panel.add(p);
		panel.add(b);
		panel_1.add(l2);
		panel_1.add(p2);
		panel_1.add(b2);

		f.setResizable(false);
		b.setVisible(true);

		f.setTitle("Current Order");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocation(960, 0);
		f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		t.setVisible(true);

		p.setVisible(true);
		p.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		l2.setFont(new Font("Tahoma", Font.PLAIN, 30));

		t2.setVisible(true);

		p2.setVisible(true);

		l.setVerticalAlignment(SwingConstants.TOP);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setFont(new Font("Tahoma", Font.PLAIN, 30));

		f.setPreferredSize(new Dimension(580, 1080));
		panel.setPreferredSize(new Dimension(580, 380));
		panel_1.setPreferredSize(new Dimension(580, 380));
		p.setPreferredSize(new Dimension(452, 300));
		p2.setPreferredSize(new Dimension(452, 300));
		b.setPreferredSize(new Dimension(200, 25));
		b2.setPreferredSize(new Dimension(200, 25));
		f.setSize(580, 1080);

		try {
			updateLists();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void updateLists() throws SQLException {
		ResultSet rs = OrderCtrl.getAllOrders();
		List<Order> list = buildObjects(rs);
		List<Order> waitingList = new ArrayList<>();
		List<Order> currentList = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Order o : list) {
				if (o.getStatus().equals("placed")) {
					waitingList.add(o);
				} else if (o.getStatus().equals("current")) {
					currentList.add(o);
				}
			}
		}
		editWaitingTable(waitingList);
		editCurrentTable(currentList);
	}

	private static Order buildObject(ResultSet rs) throws SQLException {
		Order o = new Order();
		return o;
	}

	private static List<Order> buildObjects(ResultSet rs) throws SQLException {
		List<Order> result = new ArrayList<>();
		if (rs != null) {
			while (rs.next()) {
				result.add(buildObject(rs));
			}
		}
		return result;
	}

	private static void editWaitingTable(List<Order> list) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("OrderNo");
		dtm.addColumn("");
		dtm.addColumn("");
		dtm.addColumn("Date");
		dtm.addColumn("Status");
		if (!list.isEmpty()) {
			for (Order o : list) {
				int i = 0;
				t.getModel().setValueAt(o, 0, i);
				t.getModel().setValueAt(o, 1, i);
				t.getModel().setValueAt(o, 2, i);
				t.getModel().setValueAt(o, 3, i);
				t.getModel().setValueAt(o, 4, i);
				i++;

			}
		}
		t.setModel(dtm);
	}

	private static void editCurrentTable(List<Order> list) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("OrderNo");
		dtm.addColumn("");
		dtm.addColumn("");
		dtm.addColumn("Date");
		dtm.addColumn("Status");
		if (!list.isEmpty()) {
			for (Order o : list) {
				int i = 0;
				t.getModel().setValueAt(o, 0, i);
				t.getModel().setValueAt(o, 1, i);
				t.getModel().setValueAt(o, 2, i);
				t.getModel().setValueAt(o, 3, i);
				t.getModel().setValueAt(o, 4, i);
				i++;

			}
		}
		t2.setModel(dtm);
	}

}
