/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/

package prism_convenience;

import java.text.DecimalFormat;

public class NumberHandle {
	static DecimalFormat formatter = new DecimalFormat("###,###.###");
	
	public static String get_string_with_15_digits(Number number) {
		formatter.setMinimumFractionDigits(0);
		formatter.setMaximumFractionDigits(15);	// show value with max 10 digits after the dot if it is double value
		return formatter.format(number);
	}
}
