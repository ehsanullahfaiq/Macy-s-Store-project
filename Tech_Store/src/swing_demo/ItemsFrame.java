package swing_demo;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;




public class ItemsFrame extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtpname;
	private JTextField txtprice;
	private JTextField txtqty;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtpid;
	public void connect () {
		try {
			Class.forName("com.mysql.jdcb.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Store","root","Ehsan1999@1378");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","Ehsan1999@1378");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemsFrame frame = new ItemsFrame();
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
	public ItemsFrame() {
		connect ();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setBounds(24, 63, 126, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(24, 97, 126, 24);
		contentPane.add(lblPrice);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(24, 131, 126, 24);
		contentPane.add(lblQty);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(320, 63, 126, 24);
		contentPane.add(lblProductId);
		
		txtpname = new JTextField();
		txtpname.setBounds(138, 66, 96, 19);
		contentPane.add(txtpname);
		txtpname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(138, 100, 96, 19);
		contentPane.add(txtprice);
		
		txtqty = new JTextField();
		txtqty.setColumns(10);
		txtqty.setBounds(138, 134, 96, 19);
		contentPane.add(txtqty);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 183, 407, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname=txtpname.getText();
				//String price=txtprice.getText();
				int Price= Integer.parseInt(txtprice.getText());
				//String Qty=txtqty.getText();
				int Qty= Integer.parseInt(txtqty.getText());
				int Pid= Integer.parseInt(txtpid.getText());
				try {
					PreparedStatement pst=con.prepareStatement("INSERT INTO product_table(pid,P_name,pric,Qty) values(?,?,?,?)");
					//pst = con.prepareStatement("INSERT INTO product_table(P_name,pric,Qty)VALUES(?,?,?)");
					String Pname = null;
					pst.setInt(1, Integer.parseInt(txtpid.getText()));
					pst.setString(2,Pname );
					//pst.setString(3, Price);
					pst.setInt(4, Integer.parseInt(txtprice.getText()));
					//pst.setString(4, Qty);
					pst.setInt(3, Integer.parseInt(txtqty.getText()));
					
					int k = pst.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(null,"Record Succesfully Added");
						txtpname.setText("");
						txtprice.setText("");
						txtqty.setText("");
					}else {
						JOptionPane.showMessageDialog(null,"Record Fiald to save");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 22, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(101, 22, 85, 21);
		panel.add(btnUpdate);
		
		JButton btnDelet = new JButton("Delet");
		btnDelet.setBounds(196, 22, 85, 21);
		panel.add(btnDelet);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = txtpid.getSelectedText().toString();
				pst = setString(1,pid);
				try {
					rs=pst.executeQuery();
					if(rs.next()==true) {
						txtpname.setText(rs.getString(2));
						txtprice.setText(rs.getString(3));
						txtqty.setText(rs.getString(4));
					}
					else {
						JOptionPane.showMessageDialog(null,"No record found");
						
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					pst = con.prepareStatement("SELECT FROM product_table WHERE ID =?");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(448, 117, 85, 21);
		contentPane.add(btnSearch);
		
		txtpid = new JTextField();
		txtpid.setBounds(437, 66, 96, 19);
		contentPane.add(txtpid);
		txtpid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\MFP2\\Desktop\\DD.jpg"));
		lblNewLabel_1.setBounds(10, 264, 580, 330);
		contentPane.add(lblNewLabel_1);
	}

	protected PreparedStatement setString(int i, String pid) {
		// TODO Auto-generated method stub
		return null;
	}
}
