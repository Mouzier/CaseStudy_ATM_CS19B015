package atm_classes;

import java.io.File;

public class File_Creation {
	
	public File file_creation(int customer_id) {
		File file = new File("new");
		String customer = Integer.toString(customer_id);
		file.renameTo(customer);
	}
}
