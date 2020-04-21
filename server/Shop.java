package server;

import java.util.ArrayList;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class Shop {
	
	/**
	 * Inventory object
	 */
	private Inventory theInventory;
	
	private Database d;
	
	/**
	 * ArrayList of Supplier objects
	 */
	private ArrayList <Supplier> supplierList;
	
	
	/**
	 * Constructs a Shop object
	 * @param inventory Inventory object
	 * @param suppliers ArrayList of Supplier objects
	 */
	public Shop (Inventory inventory, ArrayList <Supplier> suppliers, Database d) {
		theInventory = inventory;
		supplierList = suppliers;
		this.d = d;
	}
	
	/**
	 * getter function for Inventory object
	 * @return returns an Inventory object
	 */
	public Inventory getTheInventory () {
		return theInventory;
	}
	
	/**
	 * setter function for Inventory object
	 * @param inventory an Inventory object
	 */
	public void setTheInventory (Inventory inventory) {
		theInventory = inventory;
	}
	
	/**
	 * getter function for ArrayList of Supplier objects
	 * @return returns an ArrayList of Supplier objects
	 */
	public ArrayList<Supplier> getSupplierList (){
		return supplierList;
	}
	
	/**
	 * setter function for ArrayList of Supplier objects
	 * @param suppliers an ArrayList of Supplier objects
	 */
	public void setSupplierList (ArrayList <Supplier> suppliers){
		supplierList = suppliers;
	}
	
	/**
	 * Read the item list from the SQL database
	 * and calls the toString method in the Inventory class
	 * @return returns the output of the toString method in the Inventory class
	 */
	public String listAllItems() {
		theInventory.setItemList(d.readItemsFromDatabase());
		return theInventory.toString();
	}
	
	/**
	 * Decreases the item's quantity in the SQL database
	 * @param name the item's name
	 * @return returns a string indicating whether or not the item's quantity was decreased
	 */
	public String decreaseItem (String name) {
		if (theInventory.manageItem(name) == null){
			return "Couldn't not decrease item quantity!\n";
		}
		else
		{
			d.deleteQuantityFromItemsDatabase(name);
			return "Item quantity was decreased!\n";
		}
	}

	/**
	 * lists all the suppliers
	 */
	public void listAllSuppliers() {
		supplierList = d.readSuppliersFromDatabase();
		for (Supplier s: supplierList) {
			System.out.println(s);
		}
	}

	/**
	 * searches the Inventory for an item that matches by name
	 * @param name the item's name
	 * @return returns the item's information if a match is found
	 */
	public String getItem(String name) {
		Item theItem = theInventory.searchForItem(name);
		if (theItem == null)
		     return "Item " + name + " could not be found!\n";
		else
			 return outputItem (theItem);
			
	}

	/**
	 * searches the Inventory for an item that matches by ID
	 * @param id the item's ID
	 * @return returns the item's information if a match is found
	 */
	public String getItem(int id) {
		Item theItem = theInventory.searchForItem(id);
		if (theItem == null)
		     return "Item number " + id + " could not be found!\n";
		else
			return outputItem (theItem);
	}
	
	/**
	 * Outputs the item's information
	 * @param theItem an Item object
	 * @return returns the item's information
	 */
	private String outputItem (Item theItem){
		return "The item information is as follows: \n" + theItem;
	}

	/**
	 * Outputs the item's quantity
	 * @param name the item's name
	 * @return returns the item's quantity if it is greater than zero
	 */
	public String getItemQuantity(String name) {
		int quantity = theInventory.getItemQuantity(name);
		if (quantity < 0)
		    return "Item " + name + " could not be found!\n";
		else
			return "The quantity of Item " + name + " is: " + quantity + "\n";
	}

	/**
	 * calls the printOrder method in the Inventory class
	 * @return
	 */
	public String printOrder() {
		return theInventory.printOrder();
	}
}
