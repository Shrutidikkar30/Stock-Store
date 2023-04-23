import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Sale extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtQty;
	private JTextField txtCst;
	private JTextField txtPay;
	private JTable table;
	private JTextField txtBal;
	Connection dbcon=null;
	PreparedStatement pstate;
	MyConnection mcon=new MyConnection();
	String q,qt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sale frame = new Sale();
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
	public Sale() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel("SALE");
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblHeading.setBounds(24, 23, 307, 58);
		contentPane.add(lblHeading);
		
		JLabel lblNewLabel = new JLabel("Vendor :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(643, 23, 74, 26);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(714, 28, 197, 21);
		contentPane.add(comboBox);
		
		MyConnection con=new MyConnection();
		dbcon=con.getConnection();
		try {
			pstate=dbcon.prepareStatement("select name from vendor");
			ResultSet rs=pstate.executeQuery();
			comboBox.removeAllItems();
			comboBox.addItem("Select");
			while(rs.next()) {
				comboBox.addItem(rs.getString(1));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Exception.","Error",JOptionPane.ERROR_MESSAGE);

		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 224, 750, 404);
		contentPane.add(scrollPane);
		/*
		 create table sale(
		 vendor varchar2(10),
		 code varchar2(10),
		 name varchar2(10),
		 price varchar2(20),
		 qty varchar2(10),
		 total varchar2(20),
		 pay varchar2(20),
		 bal varchar2(20)
		 );
		 */
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Vendor", "Code", "Name", "Price", "Quantity", "Total", "Payment", "Balance"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		int  c;
		try {
			MyConnection m=new MyConnection();
			dbcon=m.getConnection();
			pstate=dbcon.prepareStatement("select * from sale");
			ResultSet rs=pstate.executeQuery();
			ResultSetMetaData rsd=rs.getMetaData();
			c=rsd.getColumnCount();
			DefaultTableModel d=(DefaultTableModel)table.getModel();
			d.setRowCount(0);
			while(rs.next()) {
				Vector v2=new Vector();
				for(int i=1;i<=c;i++) {
					v2.add(rs.getString(1));
					v2.add(rs.getString(2));
					v2.add(rs.getString(3));
					v2.add(rs.getString(4));
					v2.add(rs.getString(5));
					v2.add(rs.getString(6));
					v2.add(rs.getString(7));
					v2.add(rs.getString(8));
				}
				d.addRow(v2);
			}
		}catch(Exception exe) {
			JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println(exe);
		}
		
		JLabel lblCode = new JLabel("Product Code");
		lblCode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCode.setBounds(36, 116, 93, 21);
		contentPane.add(lblCode);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtCode.setBounds(24, 155, 143, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
					String code=txtCode.getText();
					dbcon=mcon.getConnection();
					pstate=dbcon.prepareStatement("select * from prdt where prdtCode=?");
					pstate.setString(1, code);
					ResultSet rs=pstate.executeQuery();
					if(rs.next()==false) {
						JOptionPane.showMessageDialog(null,"Barcode not found.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String prdtName=rs.getString("prdtNme");
						String prdtPrice=rs.getString("prdtCost");
						
						txtName.setText(prdtName);
						txtPrice.setText(prdtPrice);
						txtName.setEditable(false);
						txtPrice.setEditable(false);
					}
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception.","Error",JOptionPane.ERROR_MESSAGE);
						//System.out.println(ex);
					}
				}
			}
		});
		
		JLabel lblName = new JLabel("Product Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setBounds(279, 116, 93, 21);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtName.setBounds(276, 155, 143, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPrice.setBounds(505, 116, 93, 21);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPrice.setBounds(505, 156, 143, 26);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQty.setBounds(726, 116, 93, 21);
		contentPane.add(lblQty);
		
		txtQty = new JTextField();
		txtQty.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtQty.setBounds(721, 156, 143, 26);
		contentPane.add(txtQty);
		txtQty.setColumns(10);
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						q=txtQty.getText();
						dbcon=mcon.getConnection();
						pstate=dbcon.prepareStatement("select * from prdt where prdtCode=?");
						pstate.setString(1, txtCode.getText());
						ResultSet rs=pstate.executeQuery();
						rs.next();
						qt=rs.getString("prdtQty");
						if(Integer.parseInt(q)>Integer.parseInt(qt)) {
							JOptionPane.showMessageDialog(null,"Quantity is out of stock.","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
						double price=Double.parseDouble(txtPrice.getText());
						int qty=Integer.parseInt(txtQty.getText());
						double total=0;
						total=price*qty;
						txtCst.setText(Double.toString(total));
						txtCst.setEditable(false);
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception at quantity.","Error",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex);
					}
				}
			}
		});
		
		JLabel lblTotal = new JLabel("Total Cost");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTotal.setBounds(843, 290, 93, 21);
		contentPane.add(lblTotal);
		
		txtCst = new JTextField();
		txtCst.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtCst.setBounds(820, 340, 143, 26);
		contentPane.add(txtCst);
		txtCst.setColumns(10);
		
		JLabel lblPay = new JLabel("Payment");
		lblPay.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPay.setBounds(843, 405, 93,21);
		contentPane.add(lblPay);
		
		txtPay = new JTextField();
		txtPay.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPay.setBounds(820, 455, 143, 26);
		contentPane.add(txtPay);
		txtPay.setColumns(10);
		txtPay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String t=txtCst.getText();
					String p=txtPay.getText();
					if(Double.parseDouble(t)<Double.parseDouble(p)) {
						JOptionPane.showMessageDialog(null,"Payment is greater than total bill.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
					double bal=Double.parseDouble(t)-Double.parseDouble(p);
					txtBal.setText(Double.toString(bal));
					txtBal.setEditable(false);
					}
				}
			}
		});
		
		JButton btnAdddb = new JButton("Add");
		btnAdddb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"vendor is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtCode.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()||txtCst.getText().trim().isEmpty()||txtPrice.getText().trim().isEmpty()||txtBal.getText().trim().isEmpty()||txtPay.getText().trim().isEmpty()||txtQty.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						DefaultTableModel tObj=(DefaultTableModel)table.getModel();
						String ven=(String) comboBox.getSelectedItem();
						tObj.addRow(new Object[] {ven,txtCode.getText(),txtName.getText(),txtPrice.getText(),txtQty.getText(),txtCst.getText(),txtPay.getText(),txtBal.getText()});
						dbcon=mcon.getConnection();
						pstate=dbcon.prepareStatement("insert into sale values(?,?,?,?,?,?,?,?)");
						pstate.setString(1, ven);
						pstate.setString(2,txtCode.getText());
						pstate.setString(3, txtName.getText());
						pstate.setString(4, txtPrice.getText());
						pstate.setString(5, txtQty.getText());
						pstate.setString(6, txtCst.getText());
						pstate.setString(7, txtPay.getText());
						pstate.setString(8, txtBal.getText());
						int i=pstate.executeUpdate();
						if(i>0) {
							JOptionPane.showMessageDialog(null,"Product saled successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
							pstate=dbcon.prepareStatement("update prdt set prdtQty=? where prdtCode=?");
							int newq=Integer.parseInt(qt)-Integer.parseInt(q);
							pstate.setString(1, Integer.toString(newq));
							pstate.setString(2, txtCode.getText());
							int s=pstate.executeUpdate();
							comboBox.setSelectedIndex(0);
							txtCode.setText(" ");
							txtName.setText(" ");
							txtPrice.setText(" ");
							txtQty.setText(" ");
							txtCst.setText(" ");
							txtPay.setText(" ");
							txtBal.setText(" ");
							txtName.setEditable(true);
							txtCst.setEditable(true);
							txtPrice.setEditable(true);
							txtBal.setEditable(true);
						}
						else {
							JOptionPane.showMessageDialog(null,"Product not get purchased successfully.","Error",JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception exe) {
						JOptionPane.showMessageDialog(null,"Exception at insertion of records.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAdddb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdddb.setBounds(394, 645, 120, 45);
		contentPane.add(btnAdddb);
		
		JLabel lblBal = new JLabel("Balance");
		lblBal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblBal.setBounds(843, 525, 93,21);
		contentPane.add(lblBal);
		
		txtBal = new JTextField();
		txtBal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtBal.setBounds(820, 568, 143, 26);
		contentPane.add(txtBal);
		txtBal.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m=new Main();
				dispose();
				m.show();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnBack.setBounds(24, 654, 93, 36);
		contentPane.add(btnBack);
	}
}
