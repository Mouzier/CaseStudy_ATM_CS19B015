package atm_classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_GUI {
	static JFrame admin_portal = new JFrame();
	static JPanel admin_panel = new JPanel();
	private String login_id;
	
	public String admin_display() {
		admin_portal.setSize(350,200);
		admin_portal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin_portal.add(admin_panel);
		admin_panel.setLayout(null);
		
		JLabel instruction= new JLabel("Username :");
		instruction.setBounds(60,30,100,25);
		admin_panel.add(instruction);
		
		JTextField account_num = new JTextField();
		account_num.setBounds(180, 30, 100, 25);
		admin_panel.add(account_num);
		
		JButton enter = new JButton("Enter");
		enter.setBounds(120,70,100,25);
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login_id = account_num.getText();
			}
		});
		admin_panel.add(enter);
		
		admin_portal.setVisible(true);
		
		return login_id;
		
	}
}
