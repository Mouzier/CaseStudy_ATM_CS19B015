package atm_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


class Database_connection {
	private static Connection con; 
	
	
	public static Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:atm_database.db");
			//System.out.println("Connected!");
			
		} catch (ClassNotFoundException | SQLException e ) {
			System.out.println(e+"");
		}
		
		return con;
	}
	
	public static ResultSet connect_admin() throws SQLException {
		initialize_admin();
		Statement state = con.createStatement();
		ResultSet res =  state.executeQuery("SELECT login_id, password FROM admin_info");
		
		return res;
	}
	
	private static void initialize_admin() throws SQLException{
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'admin_info'");
	}
	
	private static void initialize_atm() throws SQLException{
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'atm_balance'");
	}
	
	public static ResultSet connect_atm() throws SQLException {
		initialize_atm();
		Statement state = con.createStatement();
		ResultSet res =  state.executeQuery("SELECT amount FROM atm_balance");
		
		return res;
	}
	
	public static ResultSet connect_customer() throws SQLException {
		initialize_customer();
		Statement state = con.createStatement();
		ResultSet res =  state.executeQuery("SELECT name, account_num, account_pin, balance, joint_pin, block_value, bank FROM customer_info");
		
		return res;
	}
	
	private static void initialize_customer() throws SQLException{
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'customer_info'");
	}
	
	public static void delete_record() throws SQLException{
		PreparedStatement ps1 = con.prepareStatement("Delete from admin_info");
		PreparedStatement ps2 = con.prepareStatement("Delete from customer_info");
		PreparedStatement ps3 = con.prepareStatement("Delete from atm_balance");
		ps1.execute();
		ps2.execute();	
		ps3.execute();
	}
	
	public static void store_customer_data(String name,int account_num, int account_pin, double balance, int joint_pin, int block_value, String bank) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO customer_info(name,account_num, account_pin, balance, joint_pin, block_value, bank) VALUES(?,?,?,?,?,?,?)");
		ps.setString(1, name);
		ps.setInt(2, account_num);
		ps.setInt(3, account_pin);
		ps.setDouble(4, balance);
		ps.setInt(5,  joint_pin);
		ps.setInt(6, block_value);
		ps.setString(7,bank);
		ps.execute();
	}
	
	public static void store_admin_data(String login_id, String password) throws SQLException{
		PreparedStatement ps = con.prepareStatement("INSERT INTO admin_info(login_id, password) VALUES(?,?)");
		ps.setString(1, login_id);
		ps.setString(2, password);
		ps.execute();
	}
	
	public static void store_atm_amount(double atm_balance) throws SQLException{
		PreparedStatement ps = con.prepareStatement("INSERT INTO atm_balance(amount) VALUES(?)");
		ps.setDouble(1, atm_balance);
		ps.execute();
	}
	
	
}