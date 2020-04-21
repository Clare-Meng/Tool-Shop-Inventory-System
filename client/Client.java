package client;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Provides data fields and methods to create a Java data-type, 
 * representing a Client object in the Tool Shop Application.
 * This class is the contains the necessary methods to write to / 
 * read from the socket.
 * 
 * @author Clare Meng, Chathula Adikary
 * @since April 5th 2019
 * @v0.01
 *
 */
public class Client {

	/**
	 * The Socket
	 */
	Socket socket;
	
	/**
	 * The Reader for the Socket
	 */
	BufferedReader socketIn;
	
	/**
	 * The Writer for the Socket
	 */
	PrintWriter socketOut;
	
	/**
	 * Constructs a new object of type Client with a socket connection
	 * to the Server at serverName and portNum
	 * @param serverName - The IP Address of the Server
	 * @param portNum - The Port number of the Server
	 */
	public Client(String serverName, int portNum){
		try {
			socket = new Socket(serverName, portNum);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Unable find Server!");
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to connect to Server!");
			System.exit(1);
		}
	}
	
	/**
	 * Gets the Item List from the Server
	 * 
	 * @return - A DefaultTableModel with 
	 * all Items from Server
	 */
	public DefaultTableModel getItemList(){
		String input;
		String[] item;
		
		DefaultTableModel m = new DefaultTableModel();
		m.setColumnIdentifiers(new String[]{"Item ID", "Item Name", "Item Quantity"});
		socketOut.println("1");
		try {
			while((input = socketIn.readLine()).charAt(0) != '\0'){
				item = input.split(",");
				m.addRow(item);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The server disconnected");
		}
		return m;
	}

	/**
	 * Gets the Item data from Server by ID
	 * @param ID - The ID of the Item
	 * @return - Data about Item
	 */
	public String getItemByID(int ID) {
		String input = "", item = "";
		socketOut.println("3");
		socketOut.println(ID);
		try {
			while((input = socketIn.readLine()).charAt(0) != '\0'){
				item += input;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The server disconnected");
		}
		
		return item;
	}

	/**
	 * Gets the Item data from Server by Name
	 * @param name - The name of the item
	 * @return - Data about Item
	 */
	public String getItemByName(String name) {
		String input = "";
		socketOut.println("2");
		socketOut.println(name);
		String item = "";
		
		try {
			while((input = socketIn.readLine()).charAt(0) != '\0'){
				item += input;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The server disconnected");
		}
		
		return item;
	}
	
	/**
	 * Gets quantity of an Item from the Server by Name
	 * @param name - The name of the Item
	 * @return - The quantity of the Item
	 */
	public String getQuantity(String name){
		String input = "", quantity = "";
		socketOut.println("4");
		socketOut.println(name);
		try {
			while((input = socketIn.readLine()).charAt(0) != '\0'){
				quantity += input;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The server disconnected");
		}
		return quantity;
	}
	
	/**
	 * Decreases Item quantity from Server by Name
	 * @param name - The name of the Item
	 * @return - Message whether Item was successfully returned
	 */
	public String decreaseItemQuantity(String name){
		String input = "", message = "";
		socketOut.println("5");
		socketOut.println(name);
		
		try {
			while((input = socketIn.readLine()).charAt(0) != '\0'){
				message += input;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The server disconnected");
		}
		
		return message;
	}

	/**
	 * Prints the Order from Server
	 * @return - The Order in String representation
	 */
	public String getPrintOrder() {
		String input = "", order = "";
		socketOut.println("6");
		try{
			while((input = socketIn.readLine()).charAt(0) != '\0'){
				order += input + "\n";
			}
		} catch (IOException e){
			JOptionPane.showMessageDialog(null, "The server disconnected");
		}
		return order;
	}
}
