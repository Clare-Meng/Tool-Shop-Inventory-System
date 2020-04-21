package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */

public class Database {
	
	/**
	 * ArrayList of Items
	 */
	private ArrayList<Item> items;
	
	/**
	 * ArrayList of Suppliers
	 */
	private ArrayList<Supplier> suppliers;
	
	/**
	 * Searches for the supplierId in the suppliers ArrayList
	 * @param supplierId supplier ID
	 * @return returns a Supplier object
	 */
	private Supplier findSupplier(int supplierId) {
		Supplier theSupplier = null;
		for (Supplier s : suppliers) {
			if (s.getSupId() == supplierId) {
				theSupplier = s;
				break;
			}
		}
		return theSupplier;
	}
	
	/**
	 * Establishes connection to SQL database
	 * Reads from items table in the database
	 * Adds the results from the columns in SQL to the ArrayList of Items
	 * @return returns an ArrayList of Item objects
	 */
	public ArrayList<Item> readItemsFromDatabase()
	{
		this.items = new ArrayList<Item>();
		
		String conString = "jdbc:mysql://localhost:3306/mytestdb";
		String username = "root";
		String password = "root";
		
		String sql = "SELECT * FROM mytestdb.items";
		try
		{
			Connection con = DriverManager.getConnection(conString,  username,  password);
			Statement s = con.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next())
			{
				String ID = rs.getString(1);
				String name = rs.getString(2);
				String quantity = rs.getString(3);
				String price = rs.getString(4);
				String supplierID = rs.getString(5);
				Supplier theSupplier = findSupplier(Integer.parseInt(supplierID));
				
				Item myItem = new Item(Integer.parseInt(ID), name, Integer.parseInt(quantity), Double.parseDouble(price), theSupplier);
				items.add(myItem);
				theSupplier.getItemList().add(myItem);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return items;
	}
	
	/**
	 * Establishes connection to SQL database
	 * Reads from Suppliers table in the database
	 * Adds the results from the columns in SQL to the ArrayList of Suppliers
	 * @return returns an ArrayList of Supplier objects
	 */
	public ArrayList<Supplier> readSuppliersFromDatabase()
	{
		this.suppliers = new ArrayList<Supplier>();
		
		String conString = "jdbc:mysql://localhost:3306/mytestdb";
		String username = "root";
		String password = "root";
		
		String sql = "SELECT * FROM mytestdb.Suppliers";
		try
		{
			Connection con = DriverManager.getConnection(conString,  username,  password);
			Statement s = con.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next())
			{
				String supplierID = rs.getString(1);
				String companyName = rs.getString(2);
				String address = rs.getString(3);
				String salesContact = rs.getString(4);
				suppliers.add(new Supplier(Integer.parseInt(supplierID), companyName, address, salesContact));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return suppliers;
	}
	
	/**
	 * Takes the name of the item as an input string and
	 * decreases the item's quantity in the SQL database
	 * @param inputString name of the item in which the quantity should be decreased
	 */
	public void deleteQuantityFromItemsDatabase(String inputString)
	{
		String conString = "jdbc:mysql://localhost:3306/mytestdb";
		String username = "root";
		String password = "root";
		
		String sql1 = "SELECT * FROM mytestdb.items";
		String sql2 = "SELECT Quantity FROM mytestdb.items WHERE Name = ?";
		String sql3 = "UPDATE items SET Quantity = ? WHERE Name = ?";

		try
		{
			//establish connection
			Connection con = DriverManager.getConnection(conString,  username,  password);
			
			//select everything from SQL
			Statement st1 = con.prepareStatement(sql1);
			ResultSet rs1 = st1.executeQuery(sql1);
			
			//get the corresponding quantity
			PreparedStatement st2 = con.prepareStatement(sql2);
			
			st2.setString(1, inputString);
			ResultSet rs2 = st2.executeQuery();
			String output = "";
			
			while(rs2.next())
			{
				output = rs2.getString(1);
			}
			
			//perform update in SQL
			PreparedStatement st3 = con.prepareStatement(sql3);
			int quantity = Integer.parseInt(output);
			quantity = quantity - 1;
			String outputQuantity = Integer.toString(quantity);
			
			st3.setString(1, outputQuantity);
			st3.setString(2, inputString);
			st3.executeUpdate();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
