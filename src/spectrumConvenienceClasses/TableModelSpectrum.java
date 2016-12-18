package spectrumConvenienceClasses;

import javax.swing.table.AbstractTableModel;

public class TableModelSpectrum extends AbstractTableModel {
	private int colCount, rowCount;
	private String[] columnNames;
	private Object[][] data;

	public TableModelSpectrum(int input_colCount, int input_rowCount, String[] input_columnNames, Object[][] input_data) {
		colCount = input_colCount;
		rowCount = input_rowCount;
		data = input_data;
		columnNames = input_columnNames;
	}

	public int getColumnCount() {
		return colCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class getColumnClass(int c) {
		return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
	}

	// Don't need to implement this method unless your table's editable.
	public boolean isCellEditable(int row, int col) {
		return false; // all cells are not allowed for editing
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableDataChanged();		//With this we only update data and then repaint the table later to get the view of the updated data
		
//		fireTableCellUpdated(row, col);			//This is dangerous because:
												// 1st: we have to call table1.setValueAt(data1[0][1], 0, 1);
												// 2nd: If columns or rows are moved or sorted then we have to call: table1.setValueAt(data1[3][1], table1.convertRowIndexToModel(3), table1.convertColumnIndexToModel(1)); 	
	}
};
