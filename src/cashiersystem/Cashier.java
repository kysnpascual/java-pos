package cashiersystem;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.text.*; 
import java.util.*; 
import javax.swing.JOptionPane; 
import java.text.DecimalFormat;

//cashier class
public class Cashier extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//launch events 
	public void launchApp()	{	
		Menu menu = new Menu();
		menu.launchFrame();
	}

	//driver program
	public static void main(String args[]) {
		Menu menu = new Menu();
		menu.launchFrame();
	}	
}// End of Cashier class


//"program" class
class Program extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// GUI/swing components for system interface
	private JFrame programFrame;
	private JLabel programLabel;
	private JTextField programText;
	
	//frame launch events
	public void launchFrame() {	
		programFrame.setSize(200,350);

		programFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		programFrame.getContentPane().add(programLabel);
		programFrame.getContentPane().add(programText);
		programFrame.pack();

		// Centering the screen on the desktop
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = programFrame.getSize();
		programFrame.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		programFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		programFrame.setVisible(true);

		programText.selectAll();
		programText.addKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
}// End of program class


 //This is the Menu class
class Menu extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// GUI components
	private JFrame menuFrame;
	private JPanel menuNorthPanel,
				   menuSouthPanel,
				   menuCenterPanel,
				   menuTitlePanel,
				   menuDetailPanel,
				   menuChoicePanel;

	private JLabel menuStoreLabel,
				   menuTitleLabel,
				   menuHeaderLabel,
				   menuDateLabel,
				   menuChoiceLabel[],
				   menuGuideLabel;

	
	//Element listing of all the food NAMES
	public static String choice[] = {"Cappucino      ",
								   	"Iced Affogato  ",
					   				"Black Coffee   ",
					   				"Hot Mocha      ",
					   				"Caffe Latte    ",
					   				"Iced Macchiato ",
					   				"Black + Scones ",
					   				"Mocha + Crepes "};	

	//Element listing of all the food PRICE
	DecimalFormat df = new DecimalFormat("0.00");
	public static double price[] = {140.00,
					  				135.00,
					  				145.00,
					  				155.00,
					  				140.00,
					  				165.00,
					  				230.00,
					  				300.00};

	//tracks food order
	public static int ordered[];
				   
	String option[] = {"Proceed to Payment",
	                   "Exit Cashier"};

	//constructors initializing GUI components for Menu
	public Menu() {
		menuFrame = new JFrame("Menu:");
		menuFrame.getContentPane().setLayout(new BorderLayout(0,0));

		//the following uses RGB color codes
		menuNorthPanel = new JPanel();
		menuNorthPanel.setLayout(new FlowLayout());
		menuNorthPanel.setBackground(new Color (99,80,69)); //happy bean panel color
		
		menuSouthPanel = new JPanel();
		menuSouthPanel.setBackground(new Color(99,80,69)); //"use appropriate keys" panel

		menuTitlePanel = new JPanel();
		menuTitlePanel.setLayout(new BorderLayout(10,10));
		menuTitlePanel.setBackground(new Color(202,202,196)); //menu header panel

		menuDetailPanel = new JPanel();
		menuDetailPanel.setLayout(new GridLayout(2,1));
		menuDetailPanel.setBackground(new Color(160,160,162)); //date and time panel

		menuCenterPanel = new JPanel();
		menuCenterPanel.setLayout(new BorderLayout(0,0));
		menuCenterPanel.setBackground(new Color(160,255,150));

		menuStoreLabel = new JLabel(" HAPPY BEANS ");
		menuStoreLabel.setForeground(Color.white); //happy bean font color
		menuStoreLabel.setFont(new Font("Arial Black",Font.BOLD+Font.ITALIC,36));

		menuTitleLabel = new JLabel(" Menu: ", JLabel.CENTER);
		menuTitleLabel.setForeground(Color.black); //font color for menu
		menuTitleLabel.setFont(new Font("Courier New",Font.BOLD,30));

		// Acquire current date information
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
								DateFormat.SHORT,
								Locale.US);

		menuDateLabel = new JLabel("   Date/Time : " 
								   + dateFormat.format(calendar.getTime())  
								   + "  ");
		menuDateLabel.setForeground(Color.black); //font color for date and time
		menuDateLabel.setFont(new Font("Courier New",Font.BOLD,18));

		// Header
		menuHeaderLabel = new JLabel("             Food Item"
									+"              Price (PHP)"
									+"    Quantity");
		menuHeaderLabel.setForeground(new Color(222,184,135)); // font color
		menuHeaderLabel.setFont(new Font("Verdana",Font.BOLD,18));

		// Set the food items and menu choices
		menuChoicePanel = new JPanel();
		menuChoicePanel.setLayout(new GridLayout(choice.length+4,1,0,0));
		menuChoicePanel.setBackground(new Color(48,37,35)); //menu proper background color
		menuChoicePanel.add(menuHeaderLabel);

		// Extra 2 element for Quit and SubTotal options
		menuChoiceLabel = new JLabel[choice.length + 2];
		ordered = new int[choice.length];

		for (int i = 0; i < choice.length; i++)	{

			// Set default ordered amount of all food to 0	
			ordered[i] = 0;

			// Populating the menu table
			menuChoiceLabel[i] = new JLabel("  [" + (i+1) + "]  " + choice[i]
										   +"      "+ price[i] + "0"
										   +"       "+ ordered[i]);
			menuChoiceLabel[i].setForeground(Color.white);
			menuChoiceLabel[i].setFont(new Font("Courier New",Font.BOLD,18));

			menuChoicePanel.add(menuChoiceLabel[i]);
		}

		// Add options to menu
		menuChoiceLabel[choice.length] = new JLabel("  [0]  " + option[0]);
		menuChoiceLabel[choice.length+1] = new JLabel("  [Q]  " 
													  + option[1]);
		menuChoiceLabel[choice.length].setForeground(new Color(222,184,135)); //font color for [0] option
		menuChoiceLabel[choice.length+1].setForeground(new Color(222,184,135)); //font color for [q] option
		menuChoiceLabel[choice.length].setFont(new Font("Courier New",Font.BOLD,18));
		menuChoiceLabel[choice.length+1].setFont(new Font("Courier New",Font.BOLD,18));
		menuChoicePanel.add(menuChoiceLabel[choice.length]);
		menuChoicePanel.add(menuChoiceLabel[choice.length+1]);

		// In-system simple instructions
		menuGuideLabel = new JLabel("   Use the keyboard and press the appropriate keys in [ ]");
		menuGuideLabel.setForeground(Color.white); // font color appropriate key instruction
		menuGuideLabel.setFont(new Font("Verdana",Font.BOLD,14));	
	}

	//launch frame events
	public void launchFrame() {	

		menuFrame.setSize(200,350);

		// Arranging GUI components in Panel onto Frame
		menuDetailPanel.add(menuDateLabel);
		menuTitlePanel.add(menuTitleLabel, BorderLayout.WEST);
		menuTitlePanel.add(menuDetailPanel, BorderLayout.EAST);
		menuNorthPanel.add(menuStoreLabel, BorderLayout.NORTH);
		menuSouthPanel.add(menuGuideLabel, BorderLayout.SOUTH);		
		menuCenterPanel.add(menuTitlePanel, BorderLayout.NORTH);
		menuCenterPanel.add(menuChoicePanel, BorderLayout.CENTER);
		menuFrame.getContentPane().add(menuNorthPanel, BorderLayout.NORTH);
		menuFrame.getContentPane().add(menuCenterPanel, BorderLayout.CENTER);
		menuFrame.getContentPane().add(menuSouthPanel, BorderLayout.SOUTH);

		menuFrame.pack();		

		// Centering the screen on the desktop
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = menuFrame.getSize();
		menuFrame.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		menuFrame.addKeyListener(this);

		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);

	}

	private void refresh() {
		for (int i = 0; i < choice.length; i++)	{

			// Populating the menu table
			menuChoiceLabel[i].setText("  [" + (i+1) + "]  " + choice[i]
										   +"      "+ price[i] + "0"

										   +"       "+ ordered[i]);
		}		
	}

	// Unused interface methods
	public void keyTyped(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e) { 		
		switch(e.getKeyCode()) {
		case 49:
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
			// Accumulate food items
			ordered[e.getKeyCode() - 49]++;
			break;

		case 48:
			// Calculate total
			menuFrame.setVisible(false);
			SubTotal subTotal = new SubTotal();
			subTotal.launchFrame();
			
			break;

		case 81:
			// Quit program/exit cashier
			menuFrame.setVisible(false);
			JOptionPane.showMessageDialog(this, "Goodbye.",
				"Exit Cashier", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
			break;
		}

		// Refreshes the menu;
		this.refresh();
	}
}// End of Menu class

//subtotal class
class SubTotal extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variable to calculate total payments
	double perItemTotal;
	float grandTotal;

	// GUI components
	private JFrame subTotalFrame; 
	private JPanel subTotalNorthPanel,
				   subTotalSouthPanel,
				   subTotalCenterPanel,
				   subTotalTitlePanel,
				   subTotalDetailPanel,
				   subTotalOrderedPanel;

	private JLabel subTotalStoreLabel,
				   subTotalTitleLabel,
				   subTotalHeaderLabel,
				   subTotalDateLabel,
				   subTotalOrderedLabel[],
				   subTotalGrandTotalLabel,
				   subTotalGuideLabel;
							   					
	//initializing GUI components for subtotal class
	public SubTotal() {
		subTotalFrame = new JFrame("Billing Statement");
		subTotalFrame.getContentPane().setLayout(new BorderLayout(0,0));

		subTotalNorthPanel = new JPanel();
		subTotalNorthPanel.setLayout(new FlowLayout());
		subTotalNorthPanel.setBackground(new Color (99,80,69)); //happy bean panel color

		subTotalSouthPanel = new JPanel();
		subTotalSouthPanel.setBackground(new Color(99,80,69)); //press esc to return panel color

		subTotalTitlePanel = new JPanel();
		subTotalTitlePanel.setLayout(new BorderLayout(10,10));
		subTotalTitlePanel.setBackground(new Color(202,202,196)); //billing statement header panel color

		subTotalDetailPanel = new JPanel();
		subTotalDetailPanel.setLayout(new GridLayout(2,1));
		subTotalDetailPanel.setBackground(new Color(160,160,162)); //date and time panel

		subTotalCenterPanel = new JPanel();
		subTotalCenterPanel.setLayout(new BorderLayout(0,0));
		subTotalCenterPanel.setBackground(new Color(160,255,150));

		subTotalStoreLabel = new JLabel(" HAPPY BEANS ");
		subTotalStoreLabel.setForeground(Color.white); //happy bean font color
		subTotalStoreLabel.setFont(new Font("Arial Black",Font.BOLD+Font.ITALIC,36));

		subTotalTitleLabel = new JLabel(" BILLING STATEMENT ", JLabel.CENTER);
		subTotalTitleLabel.setForeground(Color.black); //font color
		subTotalTitleLabel.setFont(new Font("Courier New",Font.BOLD,23));

		// Acquire current date information
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
								DateFormat.SHORT,
								Locale.US);

		subTotalDateLabel = new JLabel("   Date/Time : " 
								   	 + dateFormat.format(calendar.getTime())  
								   	 + "  ");
		subTotalDateLabel.setForeground(Color.black); //font color
		subTotalDateLabel.setFont(new Font("Courier New",Font.BOLD,18));

		// Header
		subTotalHeaderLabel = new JLabel("    Food Item"
										+"           Unit Price"
										+"    Quantity"
										+"    Total (PHP)   ");
		subTotalHeaderLabel.setForeground(new Color(222,184,135)); // font color for item/price/quantity
		subTotalHeaderLabel.setFont(new Font("Verdana",Font.BOLD,18));

		// Display the food items ordered on the menu
		subTotalOrderedPanel = new JPanel();
		subTotalOrderedPanel.setBackground(new Color(48,37,35)); //menu proper background color
		subTotalOrderedPanel.add(subTotalHeaderLabel);

		// Extra 2 element for Quit and SubTotal options
		subTotalOrderedLabel = new JLabel[Menu.choice.length];
		perItemTotal = 0;
		grandTotal = 0;
		int count = 0;

		for (int i = 0; i < Menu.choice.length; i++)	{

			if (Menu.ordered[i] > 0) {

			perItemTotal = Menu.ordered[i] * Menu.price[i];
			grandTotal+= perItemTotal;
			count++;

			// Populating the menu table
			subTotalOrderedLabel[i] = new JLabel("  " + Menu.choice[i]
										   	   +"    " + Menu.price[i] + "0"
										       +"        " + Menu.ordered[i]
										       +"       " + perItemTotal + "0");
			subTotalOrderedLabel[i].setForeground(Color.white);
			subTotalOrderedLabel[i].setFont(new Font("Courier New",Font.BOLD,18));

			subTotalOrderedPanel.add(subTotalOrderedLabel[i]);

			// Reset the ordered amount of all food type ordered to 0	
			Menu.ordered[i] = 0;
			}
		}

		// Show grand total figure
		
		subTotalGrandTotalLabel = new JLabel(" Total Amount: PHP " + grandTotal + "0");
		subTotalGrandTotalLabel.setForeground(Color.yellow);  //font color
		subTotalGrandTotalLabel.setFont(new Font("Courier New",Font.BOLD,30));			

		subTotalOrderedPanel.add(subTotalGrandTotalLabel);

		// In-system simple instructions
		subTotalGuideLabel = new JLabel("   Press [ Esc ] to return to menu");
		subTotalGuideLabel.setForeground(Color.white); //font color for esc key
		subTotalGuideLabel.setFont(new Font("Verdana",Font.BOLD,14));	

		subTotalOrderedPanel.setLayout(new GridLayout(count+2,1,0,0));
	}

	
	public void launchFrame() {	

		subTotalFrame.setSize(200,350);

		// Arranging GUI components in Panel onto Frame
		subTotalDetailPanel.add(subTotalDateLabel);
		subTotalTitlePanel.add(subTotalTitleLabel, BorderLayout.WEST);
		subTotalTitlePanel.add(subTotalDetailPanel, BorderLayout.EAST);
		subTotalNorthPanel.add(subTotalStoreLabel, BorderLayout.NORTH);
		subTotalSouthPanel.add(subTotalGuideLabel, BorderLayout.SOUTH);		
		subTotalCenterPanel.add(subTotalTitlePanel, BorderLayout.NORTH);
		subTotalCenterPanel.add(subTotalOrderedPanel, BorderLayout.CENTER);
		subTotalFrame.getContentPane().add(subTotalNorthPanel, BorderLayout.NORTH);
		subTotalFrame.getContentPane().add(subTotalCenterPanel, BorderLayout.CENTER);
		subTotalFrame.getContentPane().add(subTotalSouthPanel, BorderLayout.SOUTH);

		subTotalFrame.pack();		

		// Centering the screen on the desktop
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = subTotalFrame.getSize();
		subTotalFrame.setLocation(((screenSize.width - frameSize.width) / 2),
							((screenSize.height - frameSize.height) / 2));		

		subTotalFrame.addKeyListener(this);

		subTotalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		subTotalFrame.setVisible(true);
		
		//start transaction
		int receivePayment = Integer.parseInt(JOptionPane.showInputDialog(null, "Amount Received: "));
		if (receivePayment >= grandTotal) {
			char discount = JOptionPane.showInputDialog(null, "The customer is: " 
					+ "\n" + "[A] PWD/Senior Citizen"
					+ "\n" + "[B] Student"
					+ "\n" + "[C] Non-PWD/Senior/Student").charAt(0);
			     if (discount == 'A' || discount == 'a') {
			    	 
			    	 DecimalFormat df = new DecimalFormat("0.00");
			 		//compute for discount and change
			 		float discountedBill = grandTotal - (grandTotal * 25/100);
			 		JOptionPane.showMessageDialog(null, "The customer was granted 25% discount for PWDs and Senior Citizens."
			 				+ "\n\n" +"The discounted price is PHP " + df.format(discountedBill));
			 		float billChange = receivePayment - discountedBill;
			 	
			 		//show bill change
			 		String msg = "Change is PHP " + df.format(billChange) + "." 
			 				+ "\n" + "Process completed.";
			 		JOptionPane.showMessageDialog(null, msg);
			     }
			     
			     else if (discount == 'B' || discount == 'b') {
			    	 DecimalFormat df = new DecimalFormat("0.00");
				 		//compute for discount and change
			    	 float discountedBill = grandTotal - (grandTotal * 10/100);
				 		JOptionPane.showMessageDialog(null, "The customer was granted 10% discount for Students."
				 				+"\n\n" +"The discounted price is PHP " + df.format(discountedBill));
				 		
				 		float billChange = receivePayment - discountedBill;
				 	
				 		//show bill change
				 		String msg = "Change is PHP " + df.format(billChange) + "."
				 				+ "\n" + "Process completed.";
				 		JOptionPane.showMessageDialog(null, msg);
				     }
			     
			     else if (discount == 'C' || discount == 'c'){
			    	 DecimalFormat df = new DecimalFormat("0.00");
					//compute for change
			    	 float billChange = receivePayment - grandTotal;
				
					//show bill change
					String msg = "Change is PHP " + df.format(billChange) + "."
							+ "\n" + "Process completed.";
					JOptionPane.showMessageDialog(null, msg);
			     }
			     
			     else {
			    	 JOptionPane.showMessageDialog(null, "You have entered an invalid key.");
			     } //end of if else 
		} 
		
		else {
			JOptionPane.showMessageDialog(null, "Insufficient Amount.");
		} //end of nested if
	}

	// Unused interface methods
	public void keyTyped(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e) { 

		// [Escape] key is pressed
		if (e.getKeyCode() == 27) {
			subTotalFrame.setVisible(false);
			Menu menu = new Menu();
			menu.launchFrame();
		}
	}
}// End of SubTotal class 
