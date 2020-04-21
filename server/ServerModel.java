package server;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class ServerModel implements Runnable{

	/**
	 * Database object
	 */
	private Database d;
	
	/**
	 * Supplier ArrayList
	 */
	private ArrayList<Supplier> suppliers;
	
	/**
	 * Inventory object
	 */
	private Inventory theInventory;
	
	/**
	 * Shop object
	 */
	private Shop theShop;
	
	/**
	 * Scanner object
	 */
	private Scanner scan;
	
	/**
	 * Socket object
	 */
	private Socket socket;
	
	/**
	 * socketOut to write to the client
	 */
	private PrintWriter socketOut;
	
	/**
	 * socketIn to read from the client
	 */
	private BufferedReader socketIn;

	/**
	 * Constructs a ServerModel object by reading from the SQL database
	 * @param socket Socket object
	 */
	ServerModel(Socket socket) {
		this.d = new Database();
		suppliers = new ArrayList<Supplier>();
		this.suppliers = d.readSuppliersFromDatabase();
		theInventory = new Inventory(d.readItemsFromDatabase());
		theShop = new Shop(theInventory, suppliers, d);
		scan = new Scanner(System.in);
		this.socket = socket;
		
		try {
			socketOut = new PrintWriter(socket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Error creating stream!");
		}
	}
	
	/**
	 * method to print order
	 */
	private void printOrder() {
		socketOut.print(theShop.printOrder());
		socketOut.println('\0');
	}

	/**
	 * method to decrease the item quantity
	 * @param name the item's name
	 */
	private void decreaseItem(String name) {
		socketOut.print(theShop.decreaseItem(name));
		socketOut.println('\0');
	}

	/**
	 * method to check the item's quantity
	 * @param name the item's name
	 */
	private void checkItemQuantity(String name) {
		socketOut.print(theShop.getItemQuantity(name));
		socketOut.println('\0');
	}

	/**
	 * Getter for Name from Client
	 * @return - The Name
	 */
	private String getItemName() {
		String name = "";
		try {
			name = socketIn.readLine();
		} catch (IOException e) {
			System.err.println(new Timestamp(System.currentTimeMillis()) + " Unable to get name from " + socket.getInetAddress());
		}
		return name;

	}

	/**
	 * Getter for ID from Client
	 * @return - The ID
	 */
	private int getItemID() {
		int id = 0;
		try {
			id = Integer.parseInt(socketIn.readLine());
		} catch (IOException e) {
			System.err.println(new Timestamp(System.currentTimeMillis()) + " Unable to get id from " + socket.getInetAddress());
		}
		
		return id;
	}
	
	/**
	 * method to write string to the client 
	 * stops writing when \0 is reached
	 * @param s
	 */
	public void writeToClient(String s){
		socketOut.print(s);
		socketOut.println('\0');
	}

	/**
	 * searches for the item by ID
	 * @param id the item's ID
	 */
	private void searchForItemById(int id) {
		writeToClient(theShop.getItem(id));
	}

	/**
	 * searches for the item by name
	 * @param name the item's name
	 */
	private void searchForItemByName(String name) {
		writeToClient(theShop.getItem(name));
	}
	
	/**
	 * lists all the items in the shop
	 */
	private void listAllItems(){
		writeToClient(theShop.listAllItems());
	}

	/**
	 * reads input from the client and chooses the corresponding option in the menu
	 */
	@Override
	public void run() {
		
		while(true){
			int choice = 0;
		
			try {
				choice = Integer.parseInt(socketIn.readLine());
			} catch (NumberFormatException e) {
				System.err.println("Invalid option by user!");
			} catch (IOException e) {
				System.err.println(new Timestamp(System.currentTimeMillis()) + " " + 
						socket.getInetAddress() + " disconnected!");	
					try {
						socket.close();
						return;
					} catch (IOException e1) {
						System.err.println(new Timestamp(System.currentTimeMillis()) + 
								" Unable to close socket for " + socket.getInetAddress());
					}
			}
			
			System.out.println(new Timestamp(System.currentTimeMillis()) + " " + socket.getInetAddress() + " entered: " + choice);
	
			switch (choice) {
	
			case 1:
				listAllItems();
				break;
			case 2:
				searchForItemByName(getItemName());
				break;
			case 3:
				searchForItemById(getItemID());
				break;
			case 4:
				checkItemQuantity(getItemName());
				break;
			case 5:
				decreaseItem(getItemName());
				break;
			case 6:
				printOrder();
				break;
			default:
				System.out.println("\nInvalid selection Please try again!");
				break;
	
			}
		}
	}

}
