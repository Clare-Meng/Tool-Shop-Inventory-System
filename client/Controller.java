package client;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Provides data fields and methods to create a Java data-type, 
 * representing a Controller object in the Tool Shop Application.
 * This class is the contains the necessary methods to control the
 * Listeners of the Graphical User Interface. This is the Controller
 * of the MVC Architecture
 * 
 * @author Clare Meng, Chathula Adikary
 * @since April 5th 2019
 * @v0.01
 *
 */
public class Controller {

	/**
	 * The Graphical User Interface
	 */
	View view;
	
	/**
	 * The Client (communication)
	 */
	Client client;
	
	/**
	 * Constructs a new object of type Controller
	 * with a GUI and Client 
	 * @param view - The GUI
	 * @param client - The Client communicator
	 */
	public Controller(View view, Client client){
		this.view = view;
		this.client = client;
		
		this.view.addBrowseListener(new BrowseListener());
		this.view.addIDListener(new IDListener());
		this.view.addNameListener(new NameListener());
		this.view.addCheckItemListener(new CheckItemListener());
		this.view.addDecreaseItemListener(new DecreaseItemListener());
		this.view.addPrintOrderListener(new PrintOrderListener());
	}
	
	/**
	 * 
	 * Provides data fields and methods to create a Java data-type, 
	 * representing a CheckItemListener object for the Java Tool 
	 * Shop Application.
	 * 
	 * @author Clare Meng, Chathula Adikary
	 * @since April 5th 2019
	 * @v0.01
	 *
	 */
	class CheckItemListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = JOptionPane.showInputDialog("Enter name of Item to check: ");
			String quantity = client.getQuantity(name);
			JOptionPane.showMessageDialog(null, quantity,
					"Quantity", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/**
	 * 
	 * Provides data fields and methods to create a Java data-type, 
	 * representing a IDListener object for the Java Tool 
	 * Shop Application.
	 * 
	 * @author Clare Meng, Chathula Adikary
	 * @since April 5th 2019
	 * @v0.01
	 *
	 */
	class IDListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
				String i = client.getItemByID(id);
				JOptionPane.showMessageDialog(null, i, "Item", JOptionPane.PLAIN_MESSAGE);
			}catch(NumberFormatException n){
				JOptionPane.showMessageDialog(null, "Please enter valid number!",
						"Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	/**
	 * 
	 * Provides data fields and methods to create a Java data-type, 
	 * representing a BrowseListener object for the Java Tool 
	 * Shop Application.
	 * 
	 * @author Clare Meng, Chathula Adikary
	 * @since April 5th 2019
	 * @v0.01
	 *
	 */
	class BrowseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.setTableModel(client.getItemList());
		}	
	}
	
	/**
	 * 
	 * Provides data fields and methods to create a Java data-type, 
	 * representing a BrowseListener object for the Java Tool 
	 * Shop Application.
	 * 
	 * @author Clare Meng, Chathula Adikary
	 * @since April 5th 2019
	 * @v0.01
	 *
	 */
	class NameListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = JOptionPane.showInputDialog("Enter name of Item: ");
			String i = client.getItemByName(name);
			JOptionPane.showMessageDialog(null, i, "Item", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/**
	 * 
	 * Provides data fields and methods to create a Java data-type, 
	 * representing a DecreaseItemListener object for the Java Tool 
	 * Shop Application.
	 * 
	 * @author Clare Meng, Chathula Adikary
	 * @since April 5th 2019
	 * @v0.01
	 *
	 */
	class DecreaseItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = JOptionPane.showInputDialog("Enter name of Item: ");
			String value = client.decreaseItemQuantity(name);
			view.setTableModel(client.getItemList());
			JOptionPane.showMessageDialog(null, value, "Item", JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	/**
	 * 
	 * Provides data fields and methods to create a Java data-type, 
	 * representing a PrintOrderListener object for the Java Tool 
	 * Shop Application.
	 * 
	 * @author Clare Meng, Chathula Adikary
	 * @since April 5th 2019
	 * @v0.01
	 *
	 */
	class PrintOrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String order = client.getPrintOrder();
			JOptionPane.showMessageDialog(null, order, "Order for Today",
					JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	public static void main(String[] args) {
		View g = new View(Toolkit.getDefaultToolkit().getScreenSize());
		Client client = new Client("localhost", 9009);
		Controller c = new Controller(g, client);
		g.pack();
		g.setVisible(true);
	}
}
