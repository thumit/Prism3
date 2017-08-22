package prismProject;

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