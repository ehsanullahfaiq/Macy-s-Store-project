import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Taskbar.State;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class StoreFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreFrame frame = new StoreFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StoreFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 626);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Store Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(158, 10, 253, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(73, 115, 63, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel idtxt = new JLabel("ID");
		idtxt.setBounds(73, 166, 63, 20);
		contentPane.add(idtxt);
		
		textField = new JTextField();
		textField.setBounds(174, 106, 265, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(174, 169, 265, 37);
		contentPane.add(textField_1);
		
		JButton Addtxt = new JButton("Add");
		Addtxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name =textField.getText();
				int id= Integer.parseInt(idtxt.getText());
				 String msg;
		JButton jButton = new JButton();
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
				
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Store","root","Ehsan1999@1378");
					String query = "INSERT INTO Store values('" + Name + "','" + id +"')";
					PreparedStatement sta = Connection.prepareStatement(query);
					int x = State.executeUpdate();
	                  if (x == 0) {
	                      JOptionPane.showMessageDialog(Addtxt, "The record already exist");
	                   } else {
	                     
						JOptionPane.showMessageDialog(Addtxt ,
	                          "Welcome, " + msg + "Your Store is sucessfully created");
	                    }
	                    conn.close();        
			}catch(Exception e1){
				e1.printStackTrace();
					//preparedStatement ps = conn.preparedStatement("insert into"")
				
			}
			}});
		Addtxt.setBounds(412, 248, 85, 21);
		contentPane.add(Addtxt);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(73, 248, 85, 21);
		contentPane.add(btnClear);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(89, 466, 85, 21);
		contentPane.add(btnSearch);
			}
		
