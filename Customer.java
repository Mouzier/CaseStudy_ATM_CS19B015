package atm_classes;

import java.io.File;

public class Customer{
	private String name;
	protected int account_num;
	protected int pin_num;
	protected int joint_pin;
	protected double savings_account_amt;
	protected boolean block;
	protected String bank;
	public int block_value;
	//private int transact_key;
	public String transaction;
	public File file;
	
	
	Customer(String name, int account_num, int pin_num, double savings_account_amt, int joint_pin, int set_block,String bank){
		this.name = name;
		this.account_num = account_num;
		this.pin_num = pin_num;
		this.savings_account_amt = savings_account_amt;
		this.joint_pin = joint_pin;
		this.block_value = set_block;
		this.bank = bank;
		if(set_block == 1) {
			block = true;
		}
		else {
			block = false;
		}
	}
	
	Customer(){}
	
	public void set_account_num(int account_num) {
		this.account_num = account_num;
	}
	
	public void set_pin_num(int pin_num) {
		this.pin_num = pin_num;
	}
	
	public void set_savings_account_amt(double savings_account_amt) {
		this.savings_account_amt = savings_account_amt;
	}
	
	public void set_joint_pin(int joint_pin) {
		this.joint_pin = joint_pin;
	}
	
	public void set_block(boolean status) {
		this.block = status;
		if(block) {
			block_value = 1;
		}
		else {
			block_value = 0;
		}
	}
	
	public int get_account_num() {
		return account_num;
	}
	
	public int get_pin_num() {
		return pin_num;
	}
	
	public String get_name() {
		return name;
	}
	
	public String get_bank() {
		return bank;
	}
	
	public double get_savings_account_amt() {
		return savings_account_amt;
	}
	
	public int get_joint_pin() {
		return joint_pin;
	}
	
	public boolean get_block() {
		return block;
	}
	
	public void reset_data() {
		this.account_num = 0;
		this.pin_num = 0;
	}
}

class Customer_Data extends Customer{
	public double withdraw_amt;
	public double deposit_amt;
	public double transfer_amt;
	
	
}
// test
