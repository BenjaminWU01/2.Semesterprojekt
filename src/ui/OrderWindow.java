package ui;

import controller.*;
import model.Order;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import java.time.LocalDate;
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
	private static BorderFactory bf;

	// Constructor for the OrderWindow class
	public OrderWindow() {
		
		// Initiate the jframe components
		f = new JFrame();
		t_0 = new JTable(new DefaultTableModel(), null);
		t_1 = new JTable(new DefaultTableModel(), null);
		sp_0 = new JScrollPane(t_0);
		sp_1 = new JScrollPane(t_1);
		p_0 = new JPanel();
		p_1 = new JPanel();
		l_0 = new JLabel("Waiting orders:");
		l_1 = new JLabel("Current orders: ");
		b_0 = new JButton("Begin order");
		b_1 = new JButton("Finish order");
		
		// Action Listeners for buttons
		b_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				updateOrderRunning();
			}
		});
		b_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (t_1.getSelectedRow() >= 0 && t_1.getSelectedColumn() >= 0) {

					updateOrderFinished(t_1.getValueAt(t_1.getSelectedRow(), 0).toString());
				}
			}
		});

		// Add swing components to content panes
		f.getContentPane().add(p_0);
		f.getContentPane().add(p_1);
		p_0.add(l_0);
		p_0.add(sp_0);
		p_0.add(b_0);
		p_1.add(l_1);
		p_1.add(sp_1);
		p_1.add(b_1);

		// Misc. jframe setup
		f.setResizable(false);
		f.setTitle("Current Order");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocation(960, 0);
		f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		l_0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		l_1.setFont(new Font("Tahoma", Font.PLAIN, 30));

		// Set swing component sizes
		f.setPreferredSize(new Dimension(580, 1080));
		p_0.setPreferredSize(new Dimension(580, 380));
		p_1.setPreferredSize(new Dimension(580, 380));
		sp_0.setPreferredSize(new Dimension(452, 300));
		sp_1.setPreferredSize(new Dimension(452, 300));
		b_0.setPreferredSize(new Dimension(200, 25));
		b_1.setPreferredSize(new Dimension(200, 25));
		f.setSize(580, 1080);

		// Update lists on startup
		updateLists();

		// List-updater-thread, updates from database every 5000ms
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				updateLists();
			}
		}).start();

	}

	// Fetches all orders from the database
	private static List<Order> getOrders() {
		OrderCtrl oc = new OrderCtrl();
		return oc.getOrders();
	}

	// Fetches data from getOrders() and updates the lists
	private static void updateLists() {
		List<Order> list = getOrders();
		List<Order> waitingList = new ArrayList<>();
		List<Order> currentList = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Order o : list) {
				if (o.getStatus().contains("pending")) {
					waitingList.add(o);
				} else if (o.getStatus().contains("running")) {
					currentList.add(o);
				}
			}
		}
		updateWaitingTable(waitingList);
		updateCurrentTable(currentList);
	}

	// Updates the top table with the correct values from the DB
	private static void updateWaitingTable(List<Order> list) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("OrderNo");
		dtm.addColumn("InvoiceNo");
		dtm.addColumn("TrackingNo");
		dtm.addColumn("OrderDate");
		dtm.addColumn("Status");
		for (Order o : list) {
			int i = 0;
			dtm.insertRow(i, new Object[] { o.getOrderNo(), o.getInvoiceNo(), o.getTrackingNo(), o.getOrderDate(),
					o.getStatus() });
			i++;
		}
		t_0.setModel(dtm);
	}

	// Updates the bottom table with the correct values from the DB
	private static void updateCurrentTable(List<Order> list) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("OrderNo");
		dtm.addColumn("InvoiceNo");
		dtm.addColumn("TrackingNo");
		dtm.addColumn("OrderDate");
		dtm.addColumn("Status");
		for (Order o : list) {
			int i = 0;
			dtm.insertRow(i, new Object[] { o.getOrderNo(), o.getInvoiceNo(), o.getTrackingNo(), o.getOrderDate(),
					o.getStatus() });
			i++;
		}
		t_1.setModel(dtm);
	}

	// Finds the oldest order based on Date, from the TABLE, not from DB
	private static String findOldestOrder() {
		TableModel checkModel = t_0.getModel();
		LocalDate oldestOrderDate = LocalDate.now().plusDays(1);
		String orderNo = null;
		for (int i = 0; i < checkModel.getRowCount(); i++) {
			String date = checkModel.getValueAt(i, 3).toString();
			LocalDate newDate = LocalDate.of(Integer.valueOf(date.substring(0, 4)),
					Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
			if (newDate.isBefore(oldestOrderDate)) {
				oldestOrderDate = newDate;
				orderNo = checkModel.getValueAt(i, 0).toString();
			}
		}
		System.out.println(orderNo);
		return orderNo;
	}

	// Updates the oldest orders status to running
	private static void updateOrderRunning() {
		OrderCtrl oc = new OrderCtrl();
		oc.updateOrderRunning(findOldestOrder());
		updateLists();
	}

	// Updates the selected orders status to finished
	private static void updateOrderFinished(String orderNo) {
		OrderCtrl oc = new OrderCtrl();
		oc.updateOrderFinished(orderNo);
		updateLists();
	}

}
