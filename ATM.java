package atm_classes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ATM{
	static JFrame atm_portal = new JFrame();
	static JPanel atm_panel = new JPanel();
	public static void main(String[] args) throws SQLException {
		ATM_Machine atm1 = new ATM_Machine();
		Customer_Data current_customer = new Customer_Data();
		Admin current_admin = new Admin();
		Scanner sc = new Scanner(System.in);
		Customer_GUI customer_gui = new Customer_GUI();
		Admin_GUI admin_gui = new Admin_GUI();
		
		System.out.println("Welcome");
		
		boolean machine_loop = true;
		
		
		
		/*
		atm_portal.setSize(350,200);
		atm_portal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atm_portal.add(atm_panel);
		atm_panel.setLayout(null);
		
		JLabel label1 = new JLabel("Hello");
		label1.setBounds(135,10,100,25);
		atm_panel.add(label1);
		
		JLabel label2 = new JLabel("Choose one of the following :");
		label2.setBounds(80,40,200,25);
		atm_panel.add(label2);
		
		JButton admin = new JButton("Admin");
		admin.setBounds(50, 80, 100, 25);
		admin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm_portal.dispose();
				String login_id = admin_gui.admin_display();
				boolean admin_secure = false;
				if(atm1.check_admin_login_id(login_id)) {
					current_admin.set_login_id(login_id);
					admin_secure = true;
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Your login_id is not present in the system");
				}
			}
		});
		atm_panel.add(admin);
		
		JButton customer = new JButton("Customer");
		customer.setBounds(170, 80, 100, 25);
		customer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atm_portal.dispose();
				int account_num = customer_gui.customer_display();
				boolean customer_secure = false;
				if(atm1.check_customer(account_num)) {
					current_customer.set_account_num(account_num);
					customer_secure = true;
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Your account is not in the system");
				}
				
				
			}
			
		});
		atm_panel.add(customer);
		
		atm_portal.setVisible(true);*/
		
		while(machine_loop) {
			System.out.println("\nChoose one of the following options");
			System.out.println("1.Customer\n2.Admin\n");
			int option = sc.nextInt();
			System.out.println("");
			switch(option) {
			case 1 :
					System.out.println("Enter your account number");
					int account_num = sc.nextInt();
					boolean customer_access = false;
					if(atm1.check_customer(account_num)) {
						customer_access = true;
						current_customer.set_account_num(account_num);
					}
					else {
						System.out.println("Your account is not in the system");
						System.out.println("Thank you for using the system");
						continue;
					}
					
					if(customer_access) {
						System.out.println("Enter your 4-digit atm pin");
						int pin = sc.nextInt();
						boolean customer_secure = atm1.check_customer_pin(account_num, pin);
						
						if(customer_secure) {
							boolean customer_loop = true;
							while(customer_loop) {
								System.out.println("");
								System.out.println("What do you want to do");
								System.out.println("1.Withdraw from savings account\n2.Deposit to savings account");
								System.out.println("3.Transfer to savings account\n4.Check balance in savings account\n5.Change pin\n6.Block account\n7.Previous transaction");
								int customer_choice = sc.nextInt();
								System.out.println("");
								
								switch(customer_choice) {
								case 1: 
										System.out.println("Enter the amount you want to withdraw");
										current_customer.withdraw_amt = sc.nextDouble();
										atm1.withdraw_money(current_customer.get_account_num(), current_customer.withdraw_amt);
										customer_loop = false;
										break;
								case 2:
										System.out.println("Enter the amount you want to deposit");
										current_customer.deposit_amt = sc.nextDouble();
										atm1.deposit_money(current_customer.get_account_num(), current_customer.deposit_amt);
										customer_loop = false;
										break;
								case 3:
										System.out.println("Enter the amount you want to transfer");
										current_customer.transfer_amt = sc.nextDouble();
										System.out.println("Enter the receiver account num");
										int receiver_num = sc.nextInt();
										
										boolean receiver_present = atm1.check_customer(receiver_num);
										if(receiver_present)
										{
											atm1.transfer_money(current_customer.get_account_num(),receiver_num,current_customer.transfer_amt);
										}
										else
										{
											System.out.println("Receiver account not present in system");
										}
										customer_loop = false;
										break;
								case 4:
										double customer_balance = atm1.get_customer_balance(current_customer.get_account_num());
										System.out.println("Your balance : " + customer_balance);
										customer_loop = false;
										break;
								case 5:
										System.out.println("Enter your old pin");
										int old_pin = sc.nextInt();
										if(atm1.check_customer_pin(current_customer.get_account_num(), old_pin)){
											System.out.println("Enter your new pin");
											int new_pin = sc.nextInt();
											atm1.change_pin(current_customer.get_account_num(),new_pin);
										}
										else {
											System.out.println("Your old pin does not match");
										}
										customer_loop = false;
										break;
								case 6:
										System.out.println("Do you want to block your account? \n1.Yes  2.No");
										int choice = sc.nextInt();
										if(choice==1) {
											atm1.set_customer_block(current_customer.get_account_num(), false);
										}
										else {
											System.out.println("Ok.Thank you");
										}
										customer_loop = false;
										break;
								case 7:
										System.out.println("Last transactions");
										atm1.display_transact(current_customer.get_account_num());
										break;
								default:
										System.out.println("Invalid input");
										customer_loop = false;
										break;
								}
							}
						}
						else {
							System.out.println("Wrong pin\nEnding transaction");
						}
						System.out.println("");
						break;
					}
			case 2: 
					System.out.println("Enter login_id");
					String login_id = sc.next();
					boolean admin_access = false;
					
					if(atm1.check_admin_login_id(login_id)) {
						admin_access = true;
						current_admin.set_login_id(login_id);
					}
					else {
						System.out.println("Your details are not registered in the system");
						System.out.println("");
						continue;
					}
					
					if(admin_access) {
						System.out.println("Enter your password");
						String password = sc.next();
						boolean admin_secure = atm1.check_admin_password(login_id, password);
						System.out.println("");
						if(admin_secure) {
							boolean admin_loop = true;
							while(admin_loop) {
								System.out.println("What do you want to do");
								System.out.println("1.Customer Detail\n2.Add customer\n3.Remove customer\n4.Change atm balance\n5.Check atm balance");
								System.out.println("6.Add another admin\n6.Remove admin\n8.Go back\n9.Shutdown system\n10.Change block status of account");
								int admin_choice = sc.nextInt();
								System.out.println("");
								switch(admin_choice) {
								case 1:
										atm1.display_customer_data();
										break;
								case 2:
										System.out.println("Enter customer name");
										String customer_name = sc.next();
										System.out.println("Enter account number");
										int customer_account_num = sc.nextInt();
										System.out.println("Enter account pin");
										int customer_account_pin = sc.nextInt();
										System.out.println("Enter account balance");
										double account_balance = sc.nextDouble();
										System.out.println("Enter joint_pin (if not present, input 0");
										int joint_pin = sc.nextInt();
										System.out.println("Bank of account: 1.SBI 2.HDFC 3.KBank");
										int num = sc.nextInt();
										String bank = new String();
										boolean bank_loop = true;
										while(bank_loop){
											switch(num) {
												case 1:
														bank = new String("SBI");
														bank_loop = false;
														break;
												case 2:
														bank = new String("HDFC");
														bank_loop = false;
														break;
												case 3:
														bank = new String("KBank");
														bank_loop = false;
														break;
												default:
														System.out.println("Invalid value.");
														break;
											}
										}
										atm1.add_customer(customer_name, customer_account_num, customer_account_pin, account_balance, joint_pin, 1, bank);
										break;
								case 3:
										System.out.println("Enter customer account number");
										atm1.remove_customer(sc.nextInt());
										break;
								case 4:
										System.out.println("Enter new balance of atm");
										double atm_balance = sc.nextDouble();
										atm1.set_atm_balance(atm_balance);
										break;
								case 5: 
										System.out.println("Current balance of the atm : " + atm1.get_atm_balance());
										break;
								case 6:
										System.out.println("Enter admin id");
										String admin_id = sc.next();
										System.out.println("Enter admin passward");
										String admin_password= sc.next();
										atm1.add_admin(admin_id,admin_password);
										break;
								case 7:
										System.out.println("Enter admin id");
										String remove_admin_id = sc.next();
										System.out.println("Enter admin password");
										String remove_admin_password = sc.next();;
										atm1.remove_admin(remove_admin_id, remove_admin_password);
										break;
								case 8:
										admin_loop = false;
										break;
								case 9: 
										Database_connection.delete_record();
										atm1.store_admin_data();
										atm1.store_customer_data();
										atm1.store_atm_amount();
										System.exit(0);
								case 10:
										System.out.println("Enter customer id");
										int block_customer_id = sc.nextInt();
										System.out.println("Do you want to : 1.Block 2. Unblock");
										int block_value = sc.nextInt();
										if(block_value == 1) {
											atm1.set_customer_block(block_customer_id, false);
										}
										else {
											atm1.set_customer_block(block_customer_id, true);
										}
										break;
								default:
									System.out.println("Invalid input");
									break;
								}
								
								System.out.println("");
							}	
						}
					}
					break;
					
			default:
					System.out.println("Invalid input\n");
					break;
			}
		}
		sc.close();
	}
}
