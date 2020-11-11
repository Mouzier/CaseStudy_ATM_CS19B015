package atm_classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Customer_GUI {
	static JFrame customer_portal = new JFrame();
	static JPanel customer_panel = new JPanel();
	private int account_number;
	
	public int customer_display() {
		customer_portal.setSize(350,200);
		customer_portal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customer_portal.add(customer_panel);
		customer_panel.setLayout(null);
		
		JLabel instruction= new JLabel("Account number :");
		instruction.setBounds(60,30,100,25);
		customer_panel.add(instruction);
		
		JTextField account_num = new JTextField();
		account_num.setBounds(180, 30, 100, 25);
		customer_panel.add(account_num);
		
		JButton enter = new JButton("Enter");
		enter.setBounds(120,70,100,25);
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account_data = account_num.getText();
				account_number = Integer.parseInt(account_data);
			}
		});
		
		
		customer_panel.add(enter);
		
		customer_portal.setVisible(true);
		
		return account_number;
		
	}
}
