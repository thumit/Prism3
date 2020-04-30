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

import java.util.LinkedList;

public class LinkedList_Layers extends LinkedList<Layer_Item> {
	public LinkedList_Layers() {
	}
}

class Layer_Item {
	String layer_id, layer_description;
	LinkedList<Attribute_Item> attributes;

	public Layer_Item(String layer_id, String layer_description, LinkedList<Attribute_Item> attributes) {
		this.layer_id = layer_id;
		this.layer_description = layer_description;
		this.attributes = attributes;
	}
}

class Attribute_Item {
	String attribute_id, attribute_description;

	public Attribute_Item(String attribute_id, String attribute_description) {
		this.attribute_id = attribute_id;
		this.attribute_description = attribute_description;
	}
}
