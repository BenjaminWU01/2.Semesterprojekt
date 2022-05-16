package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DataAccessException;
import model.Size;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class AppWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchForProduct;
	private JTable table;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private OrderWindow ow;
	private OrderUI oUI;
	private JTextField textQty;
	private JComboBox comboBoxSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow frame = new AppWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws DataAccessException
	 */
	public AppWindow() throws DataAccessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtSearchForProduct = new JTextField();
		txtSearchForProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearchForProduct.setText("");
			}
		});
		txtSearchForProduct.setText("Search for product");
		txtSearchForProduct.setBounds(34, 88, 704, 43);
		contentPane.add(txtSearchForProduct);
		txtSearchForProduct.setColumns(10);

		JButton btnNewButton = new JButton("Finish Order");
		btnNewButton.setBackground(new Color(0, 204, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(776, 631, 171, 75);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(169, 169, 169));
		scrollPane.setBounds(513, 157, 434, 446);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					addItem();
				} catch (SQLException e1) {
					System.out.println("Couldnt add item to order");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(34, 260, 171, 59);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(222, 260, 200, 59);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Update");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(34, 357, 171, 59);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JButton("Clear order");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(222, 357, 200, 59);
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel("Create Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(389, 29, 200, 43);
		contentPane.add(lblNewLabel);

		textQty = new JTextField();
		textQty.setText("1");
		textQty.setBounds(34, 171, 96, 19);
		contentPane.add(textQty);
		textQty.setColumns(10);

		JLabel lblQty = new JLabel("Quantity");
		lblQty.setBounds(34, 157, 47, 15);
		contentPane.add(lblQty);

		JLabel lblSize = new JLabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSize.setBounds(365, 154, 60, 19);
		contentPane.add(lblSize);

		comboBoxSize = new JComboBox();
		comboBoxSize.setModel(new DefaultComboBoxModel(new String[] { "Small", "Medium", "Large" }));
		comboBoxSize.setBounds(314, 170, 108, 21);
		contentPane.add(comboBoxSize);

		oUI = new OrderUI();
		oUI.registerOrder();
		openWindow();
	}

	protected void addItem() throws SQLException {
		// der skal laves en create all funktion for item, og derefter skal der  kunne vælges derfra

	}

	public boolean checkInt() {
		boolean result = false;
		try {
			int x = Integer.parseInt(textQty.getText());
			result = true;
		} catch (NumberFormatException e) {
			System.out.println("The input in Quantity must be a number");
		}
		return result;

	}

	public void openWindow() throws DataAccessException {
		ow.main(null);
	}
}
