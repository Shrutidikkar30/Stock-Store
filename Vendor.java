import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class Vendor extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPh;
	private JTextField txtMail;
	private JTextArea txtAdd;
	private JTable tblRecord;
	private JButton btnAdd;
	Connection dbcon=null;
	PreparedStatement pstate=null;
	Statement state=null;
	String m;
	int rowcount;
	private static boolean b=true;
	int i;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendor frame = new Vendor();
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
	public Vendor() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel("VENDOR");
		lblHeading.setBounds(388, 32, 246, 70);
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 45));
		contentPane.add(lblHeading);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBounds(24, 118, 391, 466);
		pnlForm.setBackground(new Color(32, 178, 170));
		contentPane.add(pnlForm);
		pnlForm.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(27, 82, 92, 32);
		pnlForm.add(lblName);
		
		JLabel lblph = new JLabel("Phone No");
		lblph.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblph.setForeground(new Color(255, 255, 255));
		lblph.setBounds(27, 146, 92, 32);
		pnlForm.add(lblph);
		
		JLabel lblMail = new JLabel("Email");
		lblMail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMail.setForeground(new Color(255, 255, 255));
		lblMail.setBounds(27, 218, 92, 32);
		pnlForm.add(lblMail);
		
		JLabel lblAdd = new JLabel("Address");
		lblAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdd.setForeground(new Color(255, 255, 255));
		lblAdd.setBounds(27, 291, 92, 32);
		pnlForm.add(lblAdd);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtName.setBounds(129, 79, 209, 35);
		pnlForm.add(txtName);
		txtName.setColumns(10);
		
		txtPh = new JTextField();
		txtPh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtPh.setBounds(129, 143, 216, 35);
		pnlForm.add(txtPh);
		txtPh.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMail.setBounds(129, 215, 216, 35);
		pnlForm.add(txtMail);
		txtMail.setColumns(10);
		
		txtAdd = new JTextArea();
		txtAdd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtAdd.setBounds(129, 291, 169, 115);
		pnlForm.add(txtAdd);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().trim().isEmpty()||txtPh.getText().trim().isEmpty()||txtMail.getText().trim().isEmpty()||txtAdd.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter all details.","Error",JOptionPane.ERROR_MESSAGE);

				}
				else if(!(txtPh.getText().matches("^[0-9]*$")&&txtPh.getText().length()==10)){
					JOptionPane.showMessageDialog(null,"Please enter valid mobile number.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtMail.getText().matches("^[a-zA-Z0-0]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$"))) {
					JOptionPane.showMessageDialog(null,"Please enter valid mail id","Error",JOptionPane.ERROR_MESSAGE);
				}
				/*
				create table vendor(
						   name varchar2(20),
						   phone varchar2(20),
						   email varchar2(50),
						   addr varchar2(20)
						    );
						    */
				else {
				//JDBC codes
				MyConnection con=new MyConnection();
				dbcon=con.getConnection();
				m=txtMail.getText();
				try {
					pstate=dbcon.prepareStatement("select * from vendor where email=?");
					pstate.setString(1, txtMail.getText());
					ResultSet rs=pstate.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null,"Vendor with same email is exist.","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						try {
							DefaultTableModel tObj=(DefaultTableModel)tblRecord.getModel();
							tObj.addRow(new Object[] {txtName.getText(),txtPh.getText(),txtMail.getText(),txtAdd.getText()});
							pstate=dbcon.prepareStatement("insert into vendor values (?,?,?,?)");
							pstate.setString(1,txtName.getText());
							pstate.setString(2,txtPh.getText());
							pstate.setString(3,txtMail.getText());
							pstate.setString(4,txtAdd.getText());
							i=pstate.executeUpdate();
							if(i>0) {
								JOptionPane.showMessageDialog(null,"Vendor added successfully","Success",JOptionPane.INFORMATION_MESSAGE);

							}
						}catch(Exception exTable){
							JOptionPane.showMessageDialog(null,"Exception while inserting records.","Error",JOptionPane.ERROR_MESSAGE);

						}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Exception while checking email.","Error",JOptionPane.ERROR_MESSAGE);
					System.out.println(ex);
				}
				
				}
			}
		});
		btnAdd.setBounds(24, 622, 131, 39);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnAdd);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(465, 129, 489, 455);
		contentPane.add(scrollPane);
		
		tblRecord = new JTable();
		tblRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//btnAdd.setEnabled(false);
				txtMail.setEditable(false);
				DefaultTableModel tObj=(DefaultTableModel)tblRecord.getModel();
				int selected=tblRecord.getSelectedRow();
				txtName.setText(tObj.getValueAt(selected, 0).toString());
				txtPh.setText(tObj.getValueAt(selected, 1).toString());
				txtMail.setText(tObj.getValueAt(selected, 2).toString());
				txtAdd.setText(tObj.getValueAt(selected, 3).toString());
			}
		});
		tblRecord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblRecord.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Phone No", "Email", "Address"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblRecord.getColumnModel().getColumn(0).setMinWidth(16);
		scrollPane.setViewportView(tblRecord);
		
			int  c;
			try {
				MyConnection m=new MyConnection();
				dbcon=m.getConnection();
				pstate=dbcon.prepareStatement("select * from vendor");
				ResultSet rs=pstate.executeQuery();
				ResultSetMetaData rsd=rs.getMetaData();
				c=rsd.getColumnCount();
				DefaultTableModel d=(DefaultTableModel)tblRecord.getModel();
				d.setRowCount(0);
				while(rs.next()) {
					Vector v2=new Vector();
					for(int i=1;i<=c;i++) {
						v2.add(rs.getString(1));
						v2.add(rs.getString(2));
						v2.add(rs.getString(3));
						v2.add(rs.getString(4));
					}
					d.addRow(v2);
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
			
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMail.setEditable(false);
				if(txtName.getText().trim().isEmpty()||txtPh.getText().trim().isEmpty()||txtAdd.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter all details.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtPh.getText().matches("^[0-9]*$")&&txtPh.getText().length()==10)){
					JOptionPane.showMessageDialog(null,"Please enter valid mobile number.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					pstate=dbcon.prepareStatement("update vendor set name=?, phone=?,addr=? where email=?");
					pstate.setString(1, txtName.getText());
					pstate.setString(2, txtPh.getText());
					pstate.setString(3, txtAdd.getText());
					pstate.setString(4, txtMail.getText());
					i=pstate.executeUpdate();
					if(i>0) {
						JOptionPane.showMessageDialog(null,"Vendor updated successfully","Success",JOptionPane.INFORMATION_MESSAGE);
						txtName.setText(" ");
						txtPh.setText(" ");
						txtMail.setText(" ");
						txtAdd.setText(" ");
						int  c;
						try {
							MyConnection m=new MyConnection();
							dbcon=m.getConnection();
							pstate=dbcon.prepareStatement("select * from vendor");
							ResultSet rs=pstate.executeQuery();
							ResultSetMetaData rsd=rs.getMetaData();
							c=rsd.getColumnCount();
							DefaultTableModel d=(DefaultTableModel)tblRecord.getModel();
							d.setRowCount(0);
							while(rs.next()) {
								Vector v2=new Vector();
								for(int i=1;i<=c;i++) {
									v2.add(rs.getString(1));
									v2.add(rs.getString(2));
									v2.add(rs.getString(3));
									v2.add(rs.getString(4));
								}
								d.addRow(v2);
							}
						}catch(Exception ee) {
							JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
							//System.out.println(ee);
						}
						txtMail.setEditable(true);
					}
				}catch(Exception u) {
					JOptionPane.showMessageDialog(null,"Exception at update","Error",JOptionPane.ERROR_MESSAGE);
				}
				txtMail.setEditable(true);
				}
			}
		});
		btnEdit.setBounds(177, 622, 131, 39);
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnEdit);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMail.setEditable(false);
				if(txtName.getText().trim().isEmpty()||txtPh.getText().trim().isEmpty()||txtAdd.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter all details.","Error",JOptionPane.ERROR_MESSAGE);

				}
				else if(!(txtPh.getText().matches("^[0-9]*$")&&txtPh.getText().length()==10)){
					JOptionPane.showMessageDialog(null,"Please enter valid mobile number.","Error",JOptionPane.ERROR_MESSAGE);
				}
				try {
					pstate=dbcon.prepareStatement("delete from vendor where email=?");
					pstate.setString(1, txtMail.getText());
					i=pstate.executeUpdate();
					if(i>0) {
						JOptionPane.showMessageDialog(null,"Vendor deleted","Success",JOptionPane.INFORMATION_MESSAGE);
						txtName.setText(" ");
						txtPh.setText(" ");
						txtMail.setText(" ");
						txtAdd.setText(" ");
						txtMail.setEditable(true);
						int  c;
						try {
							MyConnection m=new MyConnection();
							dbcon=m.getConnection();
							pstate=dbcon.prepareStatement("select * from vendor");
							ResultSet rs=pstate.executeQuery();
							ResultSetMetaData rsd=rs.getMetaData();
							c=rsd.getColumnCount();
							DefaultTableModel d=(DefaultTableModel)tblRecord.getModel();
							d.setRowCount(0);
							while(rs.next()) {
								Vector v2=new Vector();
								for(int i=1;i<=c;i++) {
									v2.add(rs.getString(1));
									v2.add(rs.getString(2));
									v2.add(rs.getString(3));
									v2.add(rs.getString(4));
								}
								d.addRow(v2);
							}
						}catch(Exception exe) {
							JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
							System.out.println(exe);
						}
					}
				}catch(Exception u) {
					JOptionPane.showMessageDialog(null,"Exception at update","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(336, 622, 131, 39);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText(" ");
				txtPh.setText(" ");
				txtMail.setText(" ");
				txtAdd.setText(" ");
				//btnAdd.enable(true);
				txtMail.setEditable(true);
			}
		});
		btnNewButton_3.setBounds(488, 622, 131, 39);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnNewButton_3);
		
		
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
