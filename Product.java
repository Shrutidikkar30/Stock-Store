import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Product extends JFrame {

	private JPanel contentPane;
	private JTextField txtName,txtDesc,txtCode,txtRetail,txtCost,txtQty,txtOrder;
	private JTable table;
	Connection dbcon=null;
	PreparedStatement pstate=null;
	Statement state=null;
	int i;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
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
	public Product() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel("PRODUCT");
		lblHeading.setBounds(388, 32, 246, 70);
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 45));
		contentPane.add(lblHeading);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBounds(24, 119, 391, 466);
		pnlForm.setBackground(new Color(32, 178, 170));
		contentPane.add(pnlForm);
		pnlForm.setLayout(null);
		
		JLabel lblName = new JLabel("Product Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(10, 62, 129, 32);
		pnlForm.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtName.setBounds(132, 65, 216,32);
		pnlForm.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblCat = new JLabel("Category");
		lblCat.setForeground(new Color(255, 255, 255));
		lblCat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCat.setBounds(10, 20, 109, 32);
		pnlForm.add(lblCat);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setBounds(132, 23, 213, 30);
		pnlForm.add(comboBox);
		
		MyConnection con=new MyConnection();
		dbcon=con.getConnection();
		try {
			pstate=dbcon.prepareStatement("select * from cat");
			ResultSet rs=pstate.executeQuery();
			comboBox.removeAllItems();
			comboBox.addItem("Select");
			while(rs.next()) {
				comboBox.addItem(rs.getString(1));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Exception.","Error",JOptionPane.ERROR_MESSAGE);

		}
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDesc.setForeground(new Color(255, 255, 255));
		lblDesc.setBounds(10, 118, 98, 24);
		pnlForm.add(lblDesc);
		
		txtDesc = new JTextField();
		txtDesc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDesc.setBounds(132, 117, 213,32);
		pnlForm.add(txtDesc);
		txtDesc.setColumns(10);
		
		JLabel lblBar = new JLabel("Barcode");
		lblBar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblBar.setForeground(new Color(255, 255, 255));
		lblBar.setBounds(10, 172, 98, 24);
		pnlForm.add(lblBar);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtCode.setBounds(132, 171, 213, 32);
		pnlForm.add(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblCp = new JLabel("Cost Price");
		lblCp.setForeground(new Color(255, 255, 255));
		lblCp.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCp.setBounds(10, 226, 98, 24);
		pnlForm.add(lblCp);
		
		JLabel lblRetail = new JLabel("Retail Price");
		lblRetail.setForeground(new Color(255, 255, 255));
		lblRetail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRetail.setBounds(10, 273, 98, 24);
		pnlForm.add(lblRetail);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblQty.setForeground(new Color(255, 255, 255));
		lblQty.setBounds(10, 323, 98, 24);
		pnlForm.add(lblQty);
		
		JLabel lblReorder = new JLabel("Reorder Level");
		lblReorder.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblReorder.setForeground(new Color(255, 255, 255));
		lblReorder.setBounds(10, 376, 121, 24);
		pnlForm.add(lblReorder);
		
		txtCost = new JTextField();
		txtCost.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtCost.setBounds(132, 225, 213,32);
		pnlForm.add(txtCost);
		txtCost.setColumns(10);
		
		txtRetail = new JTextField();
		txtRetail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtRetail.setBounds(135, 272, 213,32);
		pnlForm.add(txtRetail);
		txtRetail.setColumns(10);
		
		txtQty = new JTextField();
		txtQty.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtQty.setBounds(135, 322, 213,32);
		pnlForm.add(txtQty);
		txtQty.setColumns(10);
		
		txtOrder = new JTextField();
		txtOrder.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtOrder.setBounds(135, 375, 213,32);
		pnlForm.add(txtOrder);
		txtOrder.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"Category is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtCode.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()||txtCost.getText().trim().isEmpty()||txtDesc.getText().trim().isEmpty()||txtOrder.getText().trim().isEmpty()||txtQty.getText().trim().isEmpty()||txtRetail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtCost.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Cost must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtQty.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Quantity must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtCode.getText().matches("^[0-9]*$"))) {
			JOptionPane.showMessageDialog(null,"Barcode must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					/*
					 create table prdt(
					 	prdtCat varchar2(10),
					 	prdtNme varchar2(10),
					 	prdtDesc varchar2(20),
					 	prdtCode varchar2(10),
					 	prdtCost varchar2(10),
					 	prdtRp varchar2(10),
					 	prdtQty varchar2(5),
					 	prdtRl varchar2(10)
					 );
					 */
					try {
						pstate=dbcon.prepareStatement("select * from prdt where prdtCode=?");
						pstate.setString(1, txtCode.getText());
						ResultSet rs=pstate.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null,"Product with same code is alread exist.","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
							DefaultTableModel tObj=(DefaultTableModel)table.getModel();
							String ca=(String) comboBox.getSelectedItem();
							tObj.addRow(new Object[] {ca,txtName.getText(),txtDesc.getText(),txtCode.getText(),txtCost.getText(),txtRetail.getText(),txtQty.getText(),txtOrder.getText()});
							pstate=dbcon.prepareStatement("insert into prdt values(?,?,?,?,?,?,?,?)");
							String cate=(String) comboBox.getSelectedItem();
							pstate.setString(1,cate );
							pstate.setString(2, txtName.getText());
							pstate.setString(3, txtDesc.getText());
							pstate.setString(4, txtCode.getText());
							pstate.setString(5, txtCost.getText());
							pstate.setString(6, txtRetail.getText());
							pstate.setString(7, txtQty.getText());
							pstate.setString(8, txtOrder.getText());
							i=pstate.executeUpdate();
							if(i>0) {
								JOptionPane.showMessageDialog(null,"Product added successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception","Error",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex);
					}
				}
			}
		});
		btnAdd.setBounds(24, 622, 131, 39);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCode.setEditable(false);
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"Category is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtCode.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()||txtCost.getText().trim().isEmpty()||txtDesc.getText().trim().isEmpty()||txtOrder.getText().trim().isEmpty()||txtQty.getText().trim().isEmpty()||txtRetail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtCost.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Cost must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtQty.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Quantity must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						pstate=dbcon.prepareStatement("update prdt set prdtCat=?,prdtNme=?,prdtDesc=?,prdtCost=?,prdtRp=?,prdtQty=?,prdtRl=? where prdtCode=?");
						String ca=(String) comboBox.getSelectedItem();
						pstate.setString(1, ca);
						pstate.setString(2, txtName.getText());
						pstate.setString(3, txtDesc.getText());
						pstate.setString(4, txtCost.getText());
						pstate.setString(5, txtRetail.getText());
						pstate.setString(6, txtQty.getText());
						pstate.setString(7, txtOrder.getText());
						pstate.setString(8, txtCode.getText());
						i=pstate.executeUpdate();
						if(i>0) {
							JOptionPane.showMessageDialog(null,"Product updated successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							comboBox.setSelectedIndex(0);
							txtName.setText(" ");
							txtDesc.setText(" ");
							txtCode.setText(" ");
							txtCost.setText(" ");
							txtRetail.setText(" ");
							txtQty.setText(" ");
							txtOrder.setText(" ");
							int  c;
							try {
								MyConnection m=new MyConnection();
								dbcon=m.getConnection();
								pstate=dbcon.prepareStatement("select * from prdt");
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
							txtCode.setEditable(true);
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception at update.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEdit.setBounds(177, 622, 131, 39);
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnEdit);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"Category is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtCode.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()||txtCost.getText().trim().isEmpty()||txtDesc.getText().trim().isEmpty()||txtOrder.getText().trim().isEmpty()||txtQty.getText().trim().isEmpty()||txtRetail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtCost.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Cost must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtQty.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Quantity must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtCode.getText().matches("^[0-9]*$"))) {
			JOptionPane.showMessageDialog(null,"Barcode must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						pstate=dbcon.prepareStatement("delete from prdt where prdtCode=?");
						pstate.setString(1, txtCode.getText());
						i=pstate.executeUpdate();
						if(i>0) {
							JOptionPane.showMessageDialog(null,"Product deleted successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							comboBox.setSelectedIndex(0);
							txtName.setText(" ");
							txtDesc.setText(" ");
							txtCode.setText(" ");
							txtCost.setText(" ");
							txtRetail.setText(" ");
							txtQty.setText(" ");
							txtOrder.setText(" ");
							int  c;
							try {
								MyConnection m=new MyConnection();
								dbcon=m.getConnection();
								pstate=dbcon.prepareStatement("select * from prdt");
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
							txtCode.setEditable(true);
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception at delete.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton_2.setBounds(336, 622, 131, 39);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedIndex(0);
				txtName.setText(" ");
				txtDesc.setText(" ");
				txtCode.setText(" ");
				txtCost.setText(" ");
				txtRetail.setText(" ");
				txtQty.setText(" ");
				txtOrder.setText(" ");
			}
		});
		btnNewButton_3.setBounds(488, 622, 131, 39);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 112, 525, 466);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCode.setEditable(false);
				DefaultTableModel tObj=(DefaultTableModel)table.getModel();
				int selected=table.getSelectedRow();
				comboBox.setSelectedItem(tObj.getValueAt(selected, 0).toString());
				txtName.setText(tObj.getValueAt(selected, 1).toString());
				txtDesc.setText(tObj.getValueAt(selected, 2).toString());
				txtCode.setText(tObj.getValueAt(selected, 3).toString());
				txtCost.setText(tObj.getValueAt(selected, 4).toString());
				txtRetail.setText(tObj.getValueAt(selected, 5).toString());
				txtQty.setText(tObj.getValueAt(selected, 6).toString());
				txtOrder.setText(tObj.getValueAt(selected, 7).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Category", "Product Name ", "Description ", "Barcode", "Cost Price", "Retail Price", "Quantity", "Reorder Level"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		int  c;
		try {
			MyConnection m=new MyConnection();
			dbcon=m.getConnection();
			pstate=dbcon.prepareStatement("select * from prdt");
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
			System.out.println(exe);
		}
	
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m=new Main();
				dispose();
				m.show();
			}
		});
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(10, 10, 21, 22);
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/back.jpg"));//pass the path of the image
		//add an object of image icon class
		btnBack.setIcon(icon);
		contentPane.add(btnBack);
	}
}
