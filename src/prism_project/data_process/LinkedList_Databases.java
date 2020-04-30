/*
 * Copyright (C) 2016-2020 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM. If not, see <http://www.gnu.org/licenses/>.
 */

package prism_project.data_process;

import java.io.File;
import java.util.LinkedList;

public class LinkedList_Databases extends LinkedList<LinkedList_Databases_Item> {
	
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
		LinkedList_Databases_Item new_read_database_item = new LinkedList_Databases_Item(file_database, String.valueOf(file_database.lastModified()), read_database);
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


