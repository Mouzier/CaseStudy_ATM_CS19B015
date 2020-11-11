package atm_classes;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Math;
  

class ATM_Machine{
	private ArrayList<Customer> c1 = new ArrayList<Customer>();
	private ArrayList<Admin> a1 = new ArrayList<Admin>();
	private double atm_balance;
	Scanner sc = new Scanner(System.in);
	
	ATM_Machine() throws SQLException{
		Database_connection.connect();
		ResultSet set = Database_connection.connect_admin();
		
		while(set.next()){
			a1.add(new Admin(set.getString("login_id"),set.getString("password")));
		}
		
		set = Database_connection.connect_customer();
		while(set.next()) {
			c1.add(new Customer(set.getString("name"),set.getInt("account_num"), set.getInt("account_pin"), set.getDouble("balance"),set.getInt("joint_pin"),set.getInt("block_value"),set.getString("bank")));
		}
		
		set = Database_connection.connect_atm();
		
		atm_balance = set.getDouble("amount");
		
		/*for(int i = 0; i<c1.size();i++) {
			while
		}*/
		
	}
	
	public void display_admin_data() {
		for(int i =0; i<a1.size();i++) {
			System.out.println(a1.get(i).get_login_id() + " " + a1.get(i).get_password());
		}
	}
	
	public void withdraw_money(int customer_id, double withdraw_amt) {
		for(int i=0; i<c1.size(); i++) {
			if(customer_id == c1.get(i).get_account_num())
			{
				double account_amt = c1.get(i).get_savings_account_amt();
				if(atm_balance > withdraw_amt)
				{
					if(account_amt > withdraw_amt)
					{
						if((account_amt - withdraw_amt)>1000){
							if(OTP_generator()) {
								account_amt -= withdraw_amt;
								if(c1.get(i).get_bank().equals("HDFC") || c1.get(i).get_bank().equals("KBank")) {
									account_amt -= 30;
									System.out.println("You will be charged an additional 30 rupees.");
								}
								atm_balance -= withdraw_amt;
								c1.get(i).set_savings_account_amt(account_amt);
							
								System.out.println("Take your money. Please count the cash");
								System.out.println("\nTransaction Bill\n");
								System.out.println("Current balance = " + account_amt);
								System.out.println("Thank you");
								
								if(c1.get(i).transaction != null) {
									c1.get(i).transaction = c1.get(i).transaction.concat(new String("!"));
									c1.get(i).transaction = c1.get(i).transaction.concat(new String("Self withdrawal : " + withdraw_amt)); 
								}
								else {
									c1.get(i).transaction = new String("Self withdrawal : " + withdraw_amt);
								}
								break;
							}
							else {
								System.out.println("Invalid otp. Cancelling transaction");
								break;
							}
						}
						else 
						{
							System.out.println("Bank balance drops below minimum amount (1000)");
							System.out.println("Thank you");
							break;
						}
					}
					else
					{
						System.out.println("Insufficient balance in account");
						break;
					}
				}
				else 
				{
					System.out.println("ATM has insufficient balance");
					break;
				}
			}
		}
	}
	
	public void deposit_money(int customer_id, double deposit_amt) {
		
		for(int i=0; i<c1.size();i++)
		{
			if(customer_id == c1.get(i).get_account_num()) 
			{
				if(OTP_generator()) {
					double account_amt = c1.get(i).get_savings_account_amt();
				
					account_amt += deposit_amt;
					atm_balance += deposit_amt;
					c1.get(i).set_savings_account_amt(account_amt);
					
					if(c1.get(i).transaction != null) {
						c1.get(i).transaction = c1.get(i).transaction.concat(new String("!"));
						c1.get(i).transaction = c1.get(i).transaction.concat(new String("Deposit : " + deposit_amt)); 
					}
					else {
						c1.get(i).transaction = new String("Deposit withdrawal : " + deposit_amt);
					}
					break;
				}
				else {
					System.out.println("Invalid OTP. Cancelling Transaction");
					break;
				}
			}
		}
	}
	
	public void transfer_money(int sender_id, int receiver_id, double transfer_amt) {
		
		boolean receiver_present = false;
		for(int i=0; i<c1.size(); i++)
		{
			if(sender_id == c1.get(i).get_account_num()) 
			{
				double sender_account_amt = c1.get(i).get_savings_account_amt();
				
				if(sender_account_amt> transfer_amt)
				{
					if((sender_account_amt - transfer_amt)>1000)
					{
						
						if(OTP_generator()) {
							sender_account_amt -= transfer_amt;
							c1.get(i).set_savings_account_amt(sender_account_amt);
							receiver_present = true;
							
							if(c1.get(i).transaction != null) {
								c1.get(i).transaction = c1.get(i).transaction.concat(new String("!"));
								c1.get(i).transaction = c1.get(i).transaction.concat(new String("Transfer amount : " + transfer_amt + " to " + receiver_id )); 
							}
							else {
								c1.get(i).transaction = new String("Transfer amount : " + transfer_amt + " to " + receiver_id );
							}
							break;
						}
						else {
							System.out.println("Invalid OTP. Cancelling transaction");
							break;
						}
					}
					else 
					{
						System.out.println("Bank balance drops below minimum amount (1000)");
						System.out.println("Thank you");
						break;
					}
				}
				else 
				{
					System.out.println("Insufficient balance in account");
					break;
				}
			}
		}
		
		if(receiver_present) {
			for(int j = 0; j<c1.size(); j++) 
			{
				if(receiver_id == c1.get(j).get_account_num()) 
				{
					double receiver_account_amt = c1.get(j).get_savings_account_amt();
					receiver_account_amt += transfer_amt;
					c1.get(j).set_savings_account_amt(receiver_account_amt);
					if(c1.get(j).transaction != null) {
						c1.get(j).transaction = c1.get(j).transaction.concat(new String("!"));
						c1.get(j).transaction = c1.get(j).transaction.concat(new String("Amount received : " + transfer_amt + " from " + sender_id )); 
					}
					else {
						c1.get(j).transaction = new String("Amount received : " + transfer_amt + " from " + sender_id );
					}
					break;				
				}
			}
		}
	}
	
	public double get_customer_balance(int customer_id) {
		for(int i = 0; i<c1.size(); i++) {
			if(customer_id == c1.get(i).get_account_num()) {
				return c1.get(i).get_savings_account_amt();
			}
		}
		return 0.0;
	}
	
	public boolean check_customer_pin(int customer_id, int customer_pin) {
		for(int i = 0; i<c1.size(); i++) {
			if(customer_id == c1.get(i).get_account_num()) {
				if(c1.get(i).get_block()) {
					if(customer_pin == c1.get(i).get_pin_num() || customer_pin == c1.get(i).get_joint_pin()) {
						return true;
					}
				}
				else {
					System.out.println("Your account is blocked");
					System.out.println("Go to bank to unblock it");
				}
			}
		}
		return false;
	}
	
	public boolean check_customer(int customer_id) {
		for(int i = 0; i< c1.size(); i++) {
			if(customer_id == c1.get(i).get_account_num()) {
				return true;
			}
		}
		return false;
	}
	
	public void display_customer_data() {
		for(int i = 0; i < c1.size(); i++) {
			System.out.println("Customer name : " + c1.get(i).get_name());
			System.out.println("Customer account number : " + c1.get(i).get_account_num());
			System.out.println("Customer savings account balance : " + c1.get(i).get_savings_account_amt());
		}
	}
	
	public void add_customer(String customer_name, int account_num, int account_pin, double account_balance,int joint_pin, int set_block,String bank) {
		c1.add(new Customer(customer_name, account_num, account_pin, account_balance, joint_pin, set_block, bank));
		System.out.println("Done successfully");
	}
	
	public void remove_customer(int account_num) {
		for(int i = 0; i<c1.size(); i++) {
			if(account_num == c1.get(i).get_account_num()) {
				c1.remove(i);
			}
		}
	}
	
	public boolean check_admin_login_id(String login_id) {
		for(int i = 0; i<a1.size(); i++) {
			if(login_id.equals(a1.get(i).get_login_id())){
				return true;
			}
		}
		return false;
	}
	
	public boolean check_admin_password(String login_id, String password) {
		for(int i = 0; i<a1.size(); i++) {
			if(login_id.equals(a1.get(i).get_login_id())) {
				if(password.equals(a1.get(i).get_password())) {
					return true;
				}
			}
		}
		System.out.println("Wrong pin");
		return false;
	}
	
	public void set_atm_balance(double atm_balance) {
		this.atm_balance = atm_balance;
	}
	
	public double get_atm_balance() {
		return atm_balance;
	}
	
	public void add_admin(String admin_id, String admin_password) {
		a1.add(new Admin(admin_id,admin_password));
		System.out.println("Done successfully");
	}
	
	public void remove_admin(String admin_id, String admin_password) {
		for(int i = 0; i<a1.size();i++) {
			if(admin_id == a1.get(i).get_login_id()) {
				if(admin_password == a1.get(i).get_password()) {
					a1.remove(i);
				}
				else {
					System.out.println("Invalid password");
				}
			}
			else {
				System.out.println("Admin id not present in system");
			}
		}
	}
	
	public void store_customer_data() throws SQLException {
		for(int i =0 ;i<c1.size();i++) {
			Database_connection.store_customer_data(c1.get(i).get_name(), c1.get(i).get_account_num(), c1.get(i).get_pin_num(), c1.get(i).get_savings_account_amt(), c1.get(i).get_joint_pin(),c1.get(i).block_value,c1.get(i).get_bank());
		}
	}
	
	public void store_admin_data() throws SQLException {
		try {
			for(int i=0; i <a1.size();i++) {
				Database_connection.store_admin_data(a1.get(i).get_login_id(), a1.get(i).get_password());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void store_atm_amount() throws SQLException{
		Database_connection.store_atm_amount(atm_balance);
	}
	
	public boolean OTP_generator() {
		int hold = 100000;
		int issue_id = (int)(Math.random()*hold);
		
		System.out.println("This is your otp :" + issue_id);
		System.out.print("Enter the otp you received :  ");
		int otp = sc.nextInt();
		if(otp == issue_id){
			return true;
		}
		
		return false;
	}
	
	public void change_pin(int account_num, int new_pin) {
		for(int i =0; i<c1.size();i++) {
			if(account_num == c1.get(i).get_account_num()) {
				c1.get(i).set_pin_num(new_pin);
				System.out.println("Your pin has been changed");
			}
		}
	}
	
	public void set_customer_block(int customer_id, boolean status) {
		for(int i =0; i<c1.size();i++) {
			if(customer_id == c1.get(i).get_account_num()) {
				c1.get(i).set_block(status);
			}
		}
	}
	
	public void display_transact(int customer_id) {
		for(int i =0;i<c1.size();i++) {
			if(customer_id == c1.get(i).get_account_num()) {
				char [] transact_array = c1.get(i).transaction.toCharArray();
				
				for(int j =0; j< transact_array.length; j++) {
					if(transact_array[j] == '!') {
						System.out.println("");
					}
					else {
						System.out.print(transact_array[j]);
					}
				}
				System.out.println("");
			}
		}
	}
}
