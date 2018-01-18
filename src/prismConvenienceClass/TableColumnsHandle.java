/*******************************************************************************
 * Copyright (C) 2016-2018 Dung Nguyen
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prismConvenienceClass;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


public class TableColumnsHandle {
	private HashMap<String, TableColumn> hashMap_columns;
	private DefaultTableColumnModel defaultTableColumnModel;
	
	public TableColumnsHandle(JTable table) {
		hashMap_columns = new HashMap<String, TableColumn>();
		defaultTableColumnModel = (DefaultTableColumnModel) table.getColumnModel();
		Enumeration<TableColumn> enumeration = defaultTableColumnModel.getColumns();

		while (enumeration.hasMoreElements()) {
			TableColumn tableColumn = enumeration.nextElement();
			hashMap_columns.put((String) tableColumn.getIdentifier(), tableColumn);
		}
	}
	
	public void setColumnVisible(String column_name, boolean setVisible) {
		TableColumn tableColumn = hashMap_columns.get(column_name);
		if (setVisible) {
			// Using a sorted map removes the need to check column index/position
			SortedMap<Integer, TableColumn> sortedMap = new TreeMap<Integer, TableColumn>();

			// Retrieve all visible columns
			Enumeration<TableColumn> enumeration1 = defaultTableColumnModel.getColumns();

			while (enumeration1.hasMoreElements()) {
				TableColumn column = enumeration1.nextElement();
				sortedMap.put(column.getModelIndex(), column);
			}

			// Add the column of interest to the sorted map
			sortedMap.put(tableColumn.getModelIndex(), tableColumn);

			// Remove all visible columns
			for (TableColumn column : sortedMap.values()) {
				defaultTableColumnModel.removeColumn(column);
			}

			// Add all previously visible columns as well as the column of interest
			for (TableColumn column : sortedMap.values()) {
				defaultTableColumnModel.addColumn(column);
			}
		} else {
			defaultTableColumnModel.removeColumn(tableColumn);
		}
	}
}


