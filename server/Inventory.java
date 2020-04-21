package server;

import java.util.ArrayList;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class Inventory{
	
	/**
	 * ArrayList of Items
	 */
	private ArrayList <Item> itemList;
	
	/**
	 * Order object
	 */
	private Order myOrder;
	
	/**
	 * Constructs an Inventory object
	 * @param itemList ArrayList of Item objects
	 */
	public Inventory (ArrayList <Item> itemList) {
		this.itemList = itemList;
		myOrder = new Order ();
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
	 * @param itemList ArrayList of Item objects
	 */
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	
	/**
	 * Places an order for an item after it has been decreased
	 * @param name the name of the item
	 * @return returns an Item object
	 */
	public Item manageItem (String name){
		Item theItem = decreaseItem (name);
		
		if (theItem != null){
			placeOrder (theItem);
		}
		return theItem;
	}
	/**
	 * Adds an OrderLine for an item
	 * @param theItem an item object
	 */
	public void placeOrder (Item theItem){
		OrderLine ol = theItem.placeOrder();
		if (ol !=null){
			myOrder.addOrderLine(ol);
		}
	}
	
	/**
	 * Decreases the quantity for an item
	 * @param name the item's name
	 * @return returns an item object
	 */
	private Item decreaseItem (String name) {
		
		Item theItem = searchForItem (name);
		
		if (theItem == null)
			return null;
		
		if (theItem.decreaseItemQuantity() == true){
			
			return theItem;
		}
		return null;
		
	}
	
	/**
	 * getter function for item quantity
	 * @param name the item's name
	 * @return returns the quantity of the item
	 */
	public int getItemQuantity (String name){
		Item theItem = searchForItem (name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}
	
	/**
	 * searches the itemList by name for a match
	 * @param name the name of the item
	 * @return returns an item object
	 */
	public Item searchForItem (String name) {
		for (Item i: itemList) {
			if (i.getItemName().equals(name))
				return i;
		}
		return null;
	}
	
	/**
	 * toString function for the Inventory class
	 */
	public String toString () {
		String str = "";
		for (Item i: itemList) {
			str += i;
		}
		return str;
	}

	/**
	 * searches the itemList by ID for a match
	 * @param id the ID of the item
	 * @return returns an item object
	 */
	public Item searchForItem(int id) {
		for (Item i: itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}

	/**
	 * calls the toString method for an Order object
	 * @return returns a String
	 */
	public String printOrder() {
		return myOrder.toString();
	}

}
