package server;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class Item {
	
	/**
	 * ID of the item
	 */
	private int itemId;
	
	/**
	 * name of the item
	 */
	private String itemName;
	
	/**
	 * quantity of the item
	 */
	private int itemQuantity;
	
	/**
	 * price of the item
	 */
	private double itemPrice;
	
	/**
	 * status of the item's order
	 */
	private boolean alreadyOrdered;
	
	/**
	 * Supplier object
	 */
	private Supplier theSupplier;
	
	/**
	 * maximum order quantity
	 */
	private static final int ORDERQUANTITYMAX = 50;
	
	/**
	 * minimum item quantity
	 */
	private static final int MINIMUMUMBER = 40; 	
	
	/**
	 * Constructs an Item object
	 * @param id the item's ID
	 * @param name the item's name
	 * @param quantityy the item's quantity
	 * @param price the item's price
	 * @param sup the supplier object
	 */
	public Item (int id, String name, int quantity, double price, Supplier sup) {
		
		itemId = id;
		itemName = name;
		itemQuantity = quantity;
		itemPrice = price;
		sup = theSupplier; 
		setAlreadyOrdered(false);
	}
	
	/**
	 * Decreases an item's quantity by one
	 * @return returns true if the item's quantity was decreased, else false
	 */
	public boolean decreaseItemQuantity () {
		if (itemQuantity > 0) {
			itemQuantity--;
		    return true;	
		}
		else
			return false;
	}
	
	/**
	 * generate an OrderLine
	 * @return returns an OrderLine object
	 */
	public OrderLine placeOrder (){
		OrderLine ol;
		if (getItemQuantity() < MINIMUMUMBER && alreadyOrdered == false){
			ol = new OrderLine (this, ORDERQUANTITYMAX - getItemQuantity());
			alreadyOrdered = true;
			return ol;
		}
	    return null;
	}

	/**
	 * getter function for Item ID
	 * @return returns the item's ID
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * setter function for Item ID
	 * @param itemId the item's ID
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * getter function for Item name
	 * @return returns the item's name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * setter function for Item name
	 * @param itemName the item's name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * getter function for Item quantity
	 * @return returns the item's quantity
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * setter function for Item quantity
	 * @param itemQuantity the item's quantity
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * getter function for Item price
	 * @return returns the item's price
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * setter function for Item price
	 * @param itemPrice the item's price
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	/**
	 * setter function for the supplier object
	 * @param sup Supplier object
	 */
	public void setTheSupplier (Supplier sup) {
		theSupplier = sup;
	}
	
	/**
	 * getter function for the supplier object
	 * @return returns a Supplier object
	 */
	public Supplier getTheSupplier () {
		return theSupplier;
	}
	
	/**
	 * toString function for the Item class
	 */
	public String toString () {
		return itemId + "," + itemName + "," + 
	           itemQuantity + "\n";
	}

	/**
	 * Checks if the item is already ordered
	 * @return returns true if the item is already ordered, else false
	 */
	public boolean isAlreadyOrdered() {
		return alreadyOrdered;
	}

	/**
	 * setter function for AlreadyOrdered
	 * @param boolean to check if the item is already ordered
	 */
	public void setAlreadyOrdered(boolean alreadyOrdered) {
		this.alreadyOrdered = alreadyOrdered;
	}

}
