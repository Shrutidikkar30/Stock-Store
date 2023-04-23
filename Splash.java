import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Splash extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
				int x;
				//create frame
					Splash frame = new Splash();
					frame.setVisible(true);
				
					try {
						for(x=0;x<=100;x++) {
						Splash.progressBar.setValue(x);
						Thread.sleep(200);
						Splash.lblNewLabel_1.setText(Integer.toString(x)+"%");
						if(x==100) {
							frame.dispose();
							LoginPage login=new LoginPage();
							login.show();
						}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		setUndecorated(true);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		//add progress bar
		progressBar = new JProgressBar();
		progressBar.setBounds(26, 651, 951, 25);
		progressBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(32, 178, 170));
		contentPane.add(progressBar);
		//create an object image icon class
		JLabel lblNewLabel = new JLabel(" ");
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/load.gif"));//pass the path of the image
		//add an object of image icon class
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(89, 38, 665, 292);
		contentPane.add(lblNewLabel);
		
		//add JLabel
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(427, 607, 108, 34);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		contentPane.add(lblNewLabel_1);
		
		//add JLabel
		lblNewLabel_2 = new JLabel("Loading....");
		lblNewLabel_2.setBounds(369, 328, 385, 79);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_2.setForeground(new Color(32, 178, 170));
		contentPane.add(lblNewLabel_2);
		
		//add Jlabel
		lblNewLabel_3 = new JLabel("Please Wait...");
		lblNewLabel_3.setBounds(369, 417, 308, 89);
		lblNewLabel_3.setForeground(new Color(32, 178, 170));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 40));
		contentPane.add(lblNewLabel_3);
	}
}
