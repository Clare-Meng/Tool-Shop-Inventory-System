package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Provides data fields and methods to create a Java data-type, 
 * representing a View object for the Java Tool 
 * Shop Application. This is the View of MVC
 * Architecture.
 * 
 * @author Clare Meng, Chathula Adikary
 * @since April 5th 2019
 * @v0.01
 *
 */
public class View extends JFrame{
	
	/**
	 * The List Tools Button
	 */
	private JButton browseTool = new JButton("List Tools");
	
	/**
	 * The Search By Name Button
	 */
	private JButton searchName = new JButton("Search by Name");
	
	/**
	 * The Search by ID Button
	 */
	private JButton searchID = new JButton("Search by ID");
	
	/**
	 * The Check Quantity Button
	 */
	private JButton checkQuantity = new JButton("Check Quantity");
	
	/**
	 * The Decrease Quantity Button
	 */
	private JButton decreaseQuantity = new JButton("Decrease Quantity");
	
	/**
	 * The Print Order Button
	 */
	private JButton printOrder = new JButton("Print Order");
	
	/**
	 * The List of Tools Label
	 */
	private JLabel listLabel = new JLabel("List of Tools");
	
	/**
	 * The Item Table
	 */
	private JTable listTable;
	
	/**
	 * The Scroll Pane for the Table
	 */
	private JScrollPane listScroll;
	
	
	/**
	 * Constructs a new object of type View
	 * with all necessary buttons, labels
	 * and tables
	 * @param dim - The Dimension of the Frame
	 */
	public View(Dimension dim) {
		setTitle("Tool Shop Application");
		setBounds(0, 0, dim.width/2, dim.height/2);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel labels = new JPanel();
		labels.add(listLabel);
		
		JPanel txtArea = new JPanel();
		String[] colName = {"Item ID", "Item Name", "Item Quantity"};
		
		DefaultTableModel tableModel = new DefaultTableModel(colName, 10);
		listTable = new JTable(tableModel);
		listScroll = new JScrollPane(listTable);

		listTable.setEnabled(false);
		listTable.setFillsViewportHeight(true);
		txtArea.add(listScroll);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2,3));
		buttons.add(browseTool);
		buttons.add(searchName);
		buttons.add(searchID);
		buttons.add(checkQuantity);
		buttons.add(decreaseQuantity);
		buttons.add(checkQuantity);
		buttons.add(printOrder);
		
		getContentPane().add(labels, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(txtArea), BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * Setter for the ActionListener of List All Items Button
	 * @param browseListener - The ActionListener for the List Items Button
	 */
	public void addBrowseListener(ActionListener browseListener){
		browseTool.addActionListener(browseListener);
	}
	
	/**
	 * Setter for the ActionListener of Check Item by ID Button
	 * @param idListener - The ActionListener for the Check Item by ID Button
	 */
	public void addIDListener(ActionListener idListener){
		searchID.addActionListener(idListener);
	}
	
	/**
	 * Setter for the ActionListener of Check Item by Name Button
	 * @param nameListener - The ActionListener for the Check Item by Name Button
	 */
	public void addNameListener(ActionListener nameListener){
		searchName.addActionListener(nameListener);
	}
	
	/**
	 * Setter for the ActionListener of Check Item Quantity Button
	 * @param checkItemListener - The ActionListener for the Check Item Quantity Button
	 */
	public void addCheckItemListener(ActionListener checkItemListener){
		checkQuantity.addActionListener(checkItemListener);
	}
	
	/**
	 * Setter for the ActionListener of the Decrease Item Quantity Button
	 * @param decreaseItemListener - The ActionListener for Decrease Item Quantity Button
	 */
	public void addDecreaseItemListener(ActionListener decreaseItemListener){
		decreaseQuantity.addActionListener(decreaseItemListener);
	}
	
	/**
	 * Setter for the ActionListener of the Print Order Button
	 * @param printOrderListener - The ActionListener for the Print Order Button
	 */
	public void addPrintOrderListener(ActionListener printOrderListener){
		printOrder.addActionListener(printOrderListener);
	}
	
	/**
	 * Setter for the Table Model of the Item Table
	 * @param m - The Table Model of the Item Table
	 */
	public void setTableModel(DefaultTableModel m){
		listTable.setModel(m);
	}
	
	
}
