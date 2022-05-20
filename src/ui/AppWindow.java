package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProductCtrl;
import db.DataAccessException;
import model.Order;
import model.OrderLine;
import model.Product;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
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
	private ProductCtrl pc;
	private List<Product> prod;
	private Product currentProduct;
	

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
		setBounds(100, 100, 1165, 788);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
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
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(776, 631, 171, 75);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(169, 169, 169));
		scrollPane.setBounds(513, 157, 544, 449);
		contentPane.add(scrollPane);

		
		DefaultTableModel model = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table = new JTable(model);
		model.addColumn("Description");
		model.addColumn("Product No");
		model.addColumn("Quantity ordered");
		scrollPane.setViewportView(table);
		
		
		btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(Color.WHITE);
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
		btnNewButton_1.setBounds(34, 260, 179, 59);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(236, 260, 179, 59);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Update");
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(34, 357, 179, 59);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JButton("Clear order");
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(236, 357, 179, 59);
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel("Create Order");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(389, 29, 200, 43);
		contentPane.add(lblNewLabel);

		textQty = new JTextField();
		textQty.setBorder(null);
		textQty.setText("1");
		textQty.setBounds(34, 171, 96, 19);
		contentPane.add(textQty);
		textQty.setColumns(10);

		JLabel lblQty = new JLabel("Quantity");
		lblQty.setForeground(Color.WHITE);
		lblQty.setBounds(34, 157, 47, 15);
		contentPane.add(lblQty);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSize.setBounds(365, 154, 60, 19);
		contentPane.add(lblSize);

		comboBoxSize = new JComboBox(); //Lav bagefter - SizeDesc + QtyAvailable
		comboBoxSize.setModel(new DefaultComboBoxModel());
		comboBoxSize.setBounds(314, 170, 108, 21);
		contentPane.add(comboBoxSize);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBorder(null);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				findItem();
			}
		});
		btnSearch.setBounds(758, 88, 108, 43);
		contentPane.add(btnSearch);

		init();
	}

	public void init() throws DataAccessException {
		pc = new ProductCtrl();
		oUI = new OrderUI();
		oUI.registerOrder();
		openWindow();
	}
	protected void findItem() {
		
		
	    prod = pc.findProduct(txtSearchForProduct.getText().trim().toLowerCase());
		List<String> sizes = new ArrayList<>();
		for(Product product: prod) {
			sizes.add(product.getSize().getSizeDesc());	
		}
		
		String[] sz  = new String[sizes.size()];
		for(int i = 0; i < sizes.size(); i++) {
			sz[i] = sizes.get(i);
		}
		
		comboBoxSize.setModel(new DefaultComboBoxModel(sz));
		
	}
	
	protected void addItem() throws SQLException{
		String prodNo = txtSearchForProduct.getText().trim();
		boolean found = false;
		int i = 0;
		Size s = null;
		while(!found || i < prod.size()) {
			
			if(prod.get(i).getSize().getSizeDesc().equals(comboBoxSize.getSelectedItem())) {
				s = prod.get(i).getSize();
				found = true;
			}
			i++;
			
		}
		checkInt();
		int qty = Integer.parseInt(textQty.getText());
		Order order = oUI.addProduct(prodNo, qty, s);
		
		updateTable();
		
		
		
		
	}
	
	public void updateTable() {
		
		
		Product currProd = prod.get(prod.size()-1);
		
		
		String desc = currProd.getProductDescription();
			System.out.println(desc);
		String proNo = currProd.getProdNo();
			System.out.println(proNo);
		String qty = textQty.getText();
			System.out.println(qty);
		
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		model.addRow(new Object[]{desc,proNo,qty});
		
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
