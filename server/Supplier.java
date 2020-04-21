package server;

import java.util.ArrayList;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class Supplier {
	
	/**
	 * supplier ID
	 */
	private int supId;
	
	/**
	 * supplier name
	 */
	private String supName;
	
	/**
	 * supplier address
	 */
	private String supAddress;
	
	/**
	 * supplier contact name
	 */
	private String supContactName;
	
	/**
	 * ArrayList of Item objects
	 */
	private ArrayList <Item> itemList;
	
	/**
	 * Constructs a Supplier object
	 * @param id supplier ID
	 * @param name supplier name
	 * @param address supplier address
	 * @param contactName supplier contact name
	 */
	public Supplier (int id, String name, String address, String contactName) {
		
		supId = id;
		supName = name;
		supAddress = address;
		supContactName = contactName;
		itemList = new ArrayList <Item>();
	}

	/**
	 * getter function for supplier ID
	 * @return returns the supplier ID
	 */
	public int getSupId() {
		return supId;
	}

	/**
	 * setter function for supplier ID
	 * @param supId the supplier's ID
	 */
	public void setSupId(int supId) {
		this.supId = supId;
	}

	/**
	 * getter function for supplier name
	 * @return returns the supplier's name
	 */
	public String getSupName() {
		return supName;
	}

	/**
	 * setter function for supplier name
	 * @param supName the supplier's name
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}

	/**
	 * getter function for supplier address
	 * @return returns the supplier's address
	 */
	public String getSupAddress() {
		return supAddress;
	}

	/**
	 * setter function for supplier address
	 * @param supAddress the supplier's address
	 */
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	/**
	 * getter function for supplier contact name
	 * @return returns the supplier's contact name
	 */
	public String getSupContactName() {
		return supContactName;
	}

	/**
	 * setter function for supplier contact name
	 * @param supContactName the supplier's contact name
	 */
	public void setSupContactName(String supContactName) {
		this.supContactName = supContactName;
	}
	
	/**
	 * toString method for the Supplier class
	 */
	public String toString () {
		return supName + " Supplier ID: " + supId+ "\n";
	}

	/**
	 * getter function for ItemList
	 * @return returns an ArrayList of Item objects
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}

	/**
	 * setter function for ItemList
	 * @param itemList an ArrayList of Item objects
	 */
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
}
