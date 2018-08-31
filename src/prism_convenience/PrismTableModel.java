/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
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
package prism_convenience;

import javax.swing.table.AbstractTableModel;

public class PrismTableModel extends AbstractTableModel {
	private int colCount, rowCount;
	private String[] columnNames;
	private Object[][] data;

	public PrismTableModel(int input_rowCount, int input_colCount, Object[][] input_data, String[] input_columnNames) {
		rowCount = input_rowCount;
		colCount = input_colCount;
		data = input_data;
		columnNames = input_columnNames;
	}
	
	public void updateTableModelPrism(int input_rowCount, int input_colCount, Object[][] input_data, String[] input_columnNames) {
		rowCount = input_rowCount;
		colCount = input_colCount;
		data = input_data;
		columnNames = input_columnNames;
	}
	

	@Override
	public int getColumnCount() {
		return colCount;
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public Class getColumnClass(int c) {
		return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
	}

	// Don't need to implement this method unless your table's editable.
	@Override
	public boolean isCellEditable(int row, int col) {
		return false; // all cells are not allowed for editing
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableDataChanged();		//With this we only update data and then fire the change
		
//		fireTableCellUpdated(row, col);			//This is dangerous because:
												// 1st:If columns or rows are not moved and not sorted then we can call table1.setValueAt(data1[0][1], 0, 1);
												// 2nd: If columns or rows are moved or sorted then we have to call: table1.setValueAt(data1[3][1], table1.convertRowIndexToModel(3), table1.convertColumnIndexToModel(1)); 	
	}

	public void match_DataType() {
	
	}
	
	public void update_model_overview() {
		
	}
};
