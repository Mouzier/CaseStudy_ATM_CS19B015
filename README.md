# CaseStudy_ATM_CS19B015
Updated case study of atm
THIS IS THE INSTRUCTIONS ON THE FEATURES OF THE ATM PROGRAM AND HOW TO USE IT.


	The interface of this atm is menu-driven and command line based.
	The information is stored in a database "atm_database" (SQLite);
	
	NOTE!!!!! - You might need to configure the database 
			To configure the database, go to class "Database Connections" -
				a. Under the first function, connect()
					change this accordingly  --  con = DriverManager.getConnection("jdbc:sqlite:atm_database.db");

	INDIVIDUAL CONSTRAINTS -

	1.	I TRIED IMPLEMENTING GUI (HAS BEEN COMMENTED) BUT I WAS UNABLE TO TO INTEGRATE IT WITHOUT CHANGING THE CODE FUNDAMENTALLY. THE CODE WAS BASED WITH A 	COMMAND-LINE 		BASED INTERFACE IN MIND. I HAVE COMMENTED IT TO SHOW THAT I TRIED IMPLEMENTING IT. THE CLASSES "CUSTOMER_GUI" and "ADMIN_GUI" WAS MY IDEA
	2.	BLOCKING IS AN OPTION NOW

	COMMON CONSTRAINTS - 
	1. 	DATABASE IS PRESENT
	2.	 JOINT ACCOUNT - AN ACCOUNT CAN HAVE TWO PINS. (THIS IS MY INTERPRETATION OF A JOINT ACCOUNT)
	3. 	OTP GENERATION
	4. 	MONEY TRANSFER FROM ONE ACCOUNT TO ANOTHER (NEED TO ONLY ENTER THE ACCOUNT TO WHICH MONEY IS TO BE TRANSFERRED)
	5. 	CHANGE OF PIN IS POSSIBLE
	6.	TEMPORARILY STORES ALL TRANSACTION THAT HAPPENS WHEN THE PROGRAM IS RUNNING. (I AM STILL FIGURING OUT FILES IN JAVA)
	
	ADDITIONAL FEATURE
	1. 	EXTRA CHARGES IF ACCOUNT IS FROM ANOTHER BANK. THIS ATM BELONGS TO SBI.
	
	TO RUN THE PROGRAM, YOU HAVE TO RUN class "ATM".


	Everytime, the system starts - All the previous data in the system is transferred to to their respective arraylists.
	
	
	The atm has a limited cash in it. If the cash is empty or less than the amount to be withdrawn, it displays "Insufficient amount".
	Minimum amount in account has to be 1000.

	The atm allows for two type of users:
	
	1. CUSTOMER
		In order to access the functionalities, the customer must input their account number (5-digit) and their pin (4-digit)
		
		a. Withdraw amount
		b. Deposit amount
		c. Transfer amount to people in the system
		d. See their balance
		e. Change the pin
		f. Block account	NOTE : IF AN ACCOUNT IS BLOCKED, IT CAN BE UNBLOCKED ONLY BY THE ADMIN


	2.Admin
		In order to access the functionalities, the admin must input their login id (String - case sensitive) and password (String - case sensitive)
		
		a. See all customer details
		b. Add new customer to system
		c. Remove customer from system
		d. Add a new admin
		e. Remove an admin
		f. Change atm balance
		g. See atm balance
		h. Shutdown system

	When the system is shutdown, all the previous record in the database is deleted and updated by the latest values of the system.
