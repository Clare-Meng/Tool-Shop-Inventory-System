package server;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */

public class Order {
	
	/**
	 * today's date
	 */
	private Date today;
	
	/**
	 * order ID
	 */
	private int orderId;
	
	/**
	 * ArrayList of OrderLine objects
	 */
	private ArrayList <OrderLine> orderLines;
	
	/**
	 * Constructs an Order object
	 */
	public Order () {
		today = Calendar.getInstance().getTime();
		orderLines = new ArrayList <OrderLine> ();
	}
	
	/**
	 * Adds an OrderLine to the OrderLine ArrayList
	 * @param ol OrderLine object
	 */
	public void addOrderLine (OrderLine ol) {
		orderLines.add(ol);
	}
	
	/**
	 * getter function for OrderLine ArrayList
	 * @return returns an OrderLine ArrayList
	 */
	public ArrayList <OrderLine> getOrderLines (){
		return orderLines;
	}
	/**
	 * setter function for OrderLine ArrayList
	 * @param lines OrderLine ArrayList
	 */
	public void setOrderLines (ArrayList <OrderLine> lines){
		orderLines = lines;
	}

	/**
	 * getter function for order ID
	 * @return returns the Order ID
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * setter function for Order ID
	 * @param orderId the Order ID
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * toString function for the Order class
	 */
	public String toString (){
		String order = "Order Date: " + today.toString() + "\n";
		String str = "";
		for (OrderLine ol: orderLines) {
			str += ol;
			str += "------------------------\n";
		}
		if (str == "")
			str = "There are corrently no orderlines\n";
		
		order += str;
		return order;
	}

}
