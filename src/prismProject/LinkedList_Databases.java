package prismProject;

import java.io.File;
import java.util.LinkedList;

public class LinkedList_Databases extends LinkedList<Read_Item> {
	
	public LinkedList_Databases() {
		// This linked list saves all the read_database whenever a new databases is read so we don't have to read again
	}		
	
	public Read_Database return_read_database_if_exist(File file_database) {		
		for (int i = 0; i < this.size(); i++) {	
			if (this != null && this.get(i).file_database.equals(file_database)
					&& this.get(i).last_modify.equals(String.valueOf(file_database.lastModified()))) {
				return this.get(i).read_database;
			}
		}
		return null;	
	}
	
	public void update(File file_database, Read_Database read_database) {		
		Read_Item new_read_database_item = new Read_Item(file_database, String.valueOf(file_database.lastModified()), read_database);
		this.add(new_read_database_item);
	}	
	
	public void remove(File file_database) {		
		for (int i = 0; i < this.size(); i++) {	
			if (this.get(i).file_database.equals(file_database)) {
				this.remove(i);
			}
		}	
	}
}

class Read_Item {
	File file_database;
	String last_modify;
	Read_Database read_database;

	public Read_Item(File file_database, String last_modify, Read_Database read_database) {
		this.file_database = file_database;
		this.last_modify = last_modify;
		this.read_database = read_database;
	}
}