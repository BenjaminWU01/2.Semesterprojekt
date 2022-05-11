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
import javax.swing.table.TableModel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderWindow {

	private static JFrame f;
	private static JScrollPane sp_0;
	private static JScrollPane sp_1;
	private static JLabel l_1;
	private static JTable t_0;
	private static JTable t_1;
	private static JPanel p_0;
	private static JPanel p_1;
	private static JLabel l_0;
	private static JButton b_0;
	private static JButton b_1;

	private static String[] columnNames = { "OrderNo", "Name", "Size", "Quantity", "Price" };
	private static Object[][] data = {
			{ "1234", "Højhæle", LocalDate.of(2001, 3, 15), Integer.valueOf(5), Float.valueOf(300) },
			{ "1234", "Højhæle", LocalDate.of(2003, 3, 26), Integer.valueOf(5), Float.valueOf(300) },
			{ "1234", "Højhæle", LocalDate.of(1975, 9, 15), Integer.valueOf(5), Float.valueOf(300) } };

	public static void main(String[] args) {

		f = new JFrame();
		t_0 = new JTable(data, columnNames);
		t_1 = new JTable(data, columnNames);
		sp_0 = new JScrollPane(t_0);
		sp_1 = new JScrollPane(t_1);
		p_0 = new JPanel();
		p_1 = new JPanel();
		l_0 = new JLabel("Waiting orders:");
		l_1 = new JLabel("Current orders: ");
		b_0 = new JButton("Begin order");
		b_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				processOldestOrder();
			}
		});
		b_1 = new JButton("Finish order");
		b_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				finishOrder();
			}
		});

		f.getContentPane().add(p_0);
		f.getContentPane().add(p_1);
		p_0.add(l_0);
		p_0.add(sp_0);
		p_0.add(b_0);
		p_1.add(l_1);
		p_1.add(sp_1);
		p_1.add(b_1);

		f.setResizable(false);

		f.setTitle("Current Order");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocation(960, 0);
		f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		l_0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		l_1.setFont(new Font("Tahoma", Font.PLAIN, 30));

		f.setPreferredSize(new Dimension(580, 1080));
		p_0.setPreferredSize(new Dimension(580, 380));
		p_1.setPreferredSize(new Dimension(580, 380));
		sp_0.setPreferredSize(new Dimension(452, 300));
		sp_1.setPreferredSize(new Dimension(452, 300));
		b_0.setPreferredSize(new Dimension(200, 25));
		b_1.setPreferredSize(new Dimension(200, 25));
		f.setSize(580, 1080);

	}

	private static ResultSet getAllOrdersDB() {
		return OrderCtrl.getAllOrders();
	}

	private static void updateLists() throws SQLException {
		ResultSet rs = getAllOrdersDB();
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
		updateWaitingTable(waitingList);
		updateCurrentTable(currentList);
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

	// Updates the top table with the correct values from the DB
	private static void updateWaitingTable(List<Order> list) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("OrderNo");
		dtm.addColumn("");
		dtm.addColumn("");
		dtm.addColumn("Date");
		dtm.addColumn("Status");
		if (!list.isEmpty()) {
			for (Order o : list) {
				int i = 0;
				t_0.getModel().setValueAt(o, 0, i);
				t_0.getModel().setValueAt(o, 1, i);
				t_0.getModel().setValueAt(o, 2, i);
				t_0.getModel().setValueAt(o, 3, i);
				t_0.getModel().setValueAt(o, 4, i);
				i++;

			}
		}
		t_0.setModel(dtm);
	}

	// Updates the bottom table with the correct values from the DB
	private static void updateCurrentTable(List<Order> list) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("OrderNo");
		dtm.addColumn("");
		dtm.addColumn("");
		dtm.addColumn("Date");
		dtm.addColumn("Status");
		TableModel tm = t_1.getModel();
		if (!list.isEmpty()) {
			for (Order o : list) {
				int i = 0;
				tm.setValueAt(o, 0, i);
				tm.setValueAt(o, 1, i);
				tm.setValueAt(o, 2, i);
				tm.setValueAt(o, 3, i);
				tm.setValueAt(o, 4, i);
				i++;

			}
		}
		t_1.setModel(dtm);
	}

	private static String findOldestOrder() {
		TableModel checkModel = t_0.getModel();
		LocalDate oldestOrderDate = LocalDate.of(1972, 1, 5);
		String orderNo = null;
		for (int i = 0; i < checkModel.getRowCount(); i++) {
			String date = checkModel.getValueAt(i, 2).toString();
			LocalDate newDate = LocalDate.of(Integer.valueOf(date.substring(0, 4)),
					Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
			if (newDate.isBefore(oldestOrderDate)) {
				oldestOrderDate = newDate;
				orderNo = checkModel.getValueAt(i, 0).toString();
			}
		}
		System.out.println(oldestOrderDate);
		return orderNo;
	}

	private static void processOldestOrder() {
		processOldestOrderDB(findOldestOrder());
	}

	private static void processOldestOrderDB(String orderNo) {
		OrderCtrl.processOldestOrder(orderNo);
	}

	private static void finishOrder() {
		finishOrderDB();
	}

	private static void finishOrderDB() {

	}

}
