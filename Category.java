import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Category extends JFrame {

	private JPanel contentPane;
	private JTextField txtCat;
	private JButton btnAdd;
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
					Category frame = new Category();
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
	public Category() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550,250,400,400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel("Category");
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeading.setBounds(115, 29, 174, 52);
		contentPane.add(lblHeading);
		
		JLabel lblCat = new JLabel("Enter Category");
		lblCat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCat.setBounds(63, 118, 174, 26);
		contentPane.add(lblCat);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m=new Main();
				dispose();
				m.show();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.setBounds(24, 345, 92, 26);
		contentPane.add(btnBack);
		
		txtCat = new JTextField();
		txtCat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCat.setBounds(63, 158, 199, 34);
		contentPane.add(txtCat);
		txtCat.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCat.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter category.","Error",JOptionPane.ERROR_MESSAGE);

				}
				else {
					/*create table cat( cate varchar2(20));
					  */
					MyConnection con=new MyConnection();
					dbcon=con.getConnection();
					try {
						pstate=dbcon.prepareStatement("select * from cat where cate=?");
						pstate.setString(1, txtCat.getText());
						ResultSet rs=pstate.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null,"Category is already exist.","Error",JOptionPane.ERROR_MESSAGE);
						}else {
							try {
								pstate=dbcon.prepareStatement("insert into cat values (?)");
								pstate.setString(1,txtCat.getText().trim());
								i=pstate.executeUpdate();
								if(i>0) {
									JOptionPane.showMessageDialog(null,"Category added successfully","Success",JOptionPane.INFORMATION_MESSAGE);
									txtCat.setText(" ");
								}
							}catch(Exception exTable){
								JOptionPane.showMessageDialog(null,"Exception while inserting records.","Error",JOptionPane.ERROR_MESSAGE);

							}
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception while checking category.","Error",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex);
					}
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(244, 345, 97, 26);
		contentPane.add(btnAdd);
		
		
		
		
			}
}
