import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtUname;
	private JPasswordField ptxtPass;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelForm = new JPanel();
		panelForm.setBackground(new Color(32, 178, 170));
		panelForm.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelForm.setBounds(0, 0, 393, 700);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel formHeading = new JLabel("Login Form");
		formHeading.setForeground(new Color(255, 255, 255));
		formHeading.setHorizontalAlignment(SwingConstants.CENTER);
		formHeading.setBounds(10, 26, 358, 49);
		formHeading.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panelForm.add(formHeading);
		
		JLabel lblUname = new JLabel("Username");
		lblUname.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblUname.setForeground(new Color(255, 255, 255));
		lblUname.setBounds(21, 208, 99, 29);
		panelForm.add(lblUname);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(21, 271, 99, 29);
		panelForm.add(lblPass);
		
		txtUname = new JTextField();
		txtUname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtUname.setBounds(146, 212, 222, 29);
		panelForm.add(txtUname);
		txtUname.setColumns(10);
		
		ptxtPass = new JPasswordField();
		ptxtPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ptxtPass.setBounds(146, 280, 222, 29);
		panelForm.add(ptxtPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 28));
		btnLogin.setBounds(123, 373, 128, 41);
		panelForm.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=txtUname.getText();
				String password=ptxtPass.getText();
				
					//if both username and password are blank...
					if(username.equals("")&&password.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please enter the username and password.","Error",JOptionPane.ERROR_MESSAGE);
					}
					//if username is enter but password is null...
					else if(!(username.equals(""))&& password.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please enter the password.","Error",JOptionPane.ERROR_MESSAGE);
					}
					//if password is enter but username is null...
					else if(username.equals("")&& !(password.equals("")))
					{
						JOptionPane.showMessageDialog(null,"Please enter the username.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(!(username.equals("Admin")||username.equals("admin")||username.equals("ADMIN"))) {
						JOptionPane.showMessageDialog(null,"Please enter the correct username.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						if(username.equals("Admin")||username.equals("admin")||username.equals("ADMIN")) {
							if(password.equals("admin")) {
								JOptionPane.showMessageDialog(null,"Login successful");
								Main ma=new Main();
								ma.show();
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null,"Please enter the correct password.","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
					
					}
					
				}
		});
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(453, 103, 516, 500);
		contentPane.add(lblImg);
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/login.jpg"));//pass the path of the image
		//add an object of image icon class
		lblImg.setIcon(icon);
		
		
	}
	
}
