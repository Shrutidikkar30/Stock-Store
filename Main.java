import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Main extends JFrame {

	private JPanel Content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		Content = new JPanel();
		Content.setBackground(Color.WHITE);
		Content.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(Content);
		Content.setLayout(null);
		
		JLabel lblHeading = new JLabel("Stock Management System");
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setBackground(new Color(255, 255, 255));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 37));
		lblHeading.setBounds(67, 22, 876, 65);
		Content.add(lblHeading);
		
		JLabel lblUser = new JLabel("");
		lblUser.setBounds(67, 121, 218, 191);
		Content.add(lblUser);
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/user.jpg"));//pass the path of the image
		lblUser.setIcon(icon);
		
		JLabel lblPrdt = new JLabel("");
		lblPrdt.setBounds(701, 121, 218, 191);
		Content.add(lblPrdt);
		ImageIcon icon2=new ImageIcon(this.getClass().getResource("/product.jpg"));//pass the path of the image
		lblPrdt.setIcon(icon2);
		
		
		JLabel lblSales = new JLabel("");
		lblSales.setBounds(546, 431, 218, 191);
		Content.add(lblSales);
		ImageIcon icon5=new ImageIcon(this.getClass().getResource("/sales.jpg"));//pass the path of the image
		lblSales.setIcon(icon5);
		
		JLabel lblCat = new JLabel("");
		lblCat.setBounds(382, 121, 218, 191);
		Content.add(lblCat);
		ImageIcon icon6=new ImageIcon(this.getClass().getResource("/category.png"));//pass the path of the image
		lblCat.setIcon(icon6);
		
		JLabel lblPur = new JLabel("");
		lblPur.setBounds(206, 431, 218, 191);
		Content.add(lblPur);
		ImageIcon icon7=new ImageIcon(this.getClass().getResource("/supplier.jpg"));//pass the path of the image
		lblPur.setIcon(icon7);
		
		JButton btnUser = new JButton("Vendor");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vendor v=new Vendor();
				dispose();
				v.show();
			}
		});
		btnUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUser.setBounds(93, 322, 98, 37);
		Content.add(btnUser);
		
		JButton btnPrdt = new JButton("Product");
		btnPrdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product prdt=new Product();
				dispose();
				prdt.show();
			}
		});
		btnPrdt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnPrdt.setBounds(726, 322, 98, 37);
		Content.add(btnPrdt);
		
		JButton btnSale = new JButton("Sales");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sale s=new Sale();
				dispose();
				s.show();
			}
		});
		btnSale.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSale.setBounds(575, 634, 98, 37);
		Content.add(btnSale);
		
		JButton btnCat = new JButton("Categories");
		btnCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Category cat=new Category();
				dispose();
				cat.show();
			}
		});
		btnCat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnCat.setBounds(392, 322, 114, 37);
		Content.add(btnCat);
		
		JButton btnPur = new JButton("Purchase");
		btnPur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Purchase pr=new Purchase();
				dispose();
				pr.show();
			}
		});
		btnPur.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnPur.setBounds(232, 634, 104, 37);
		Content.add(btnPur);
	}
}
