package timo.geometry;

import timo.vector.Point;

import java.util.ArrayList;

public class Curve {
	String name = "";
	String type = "";

	public ArrayList<Point> getVerts(int res) {
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		return ausgabe;
	}

	public String export_obj() {
		return "";
	}

	public void setName(String _name) {
		name = _name;
	}

	public String getName() {
		return name;
	}

	public void setType(String _type) {
		this.type = _type;
	}

	public String getType() {
		return this.type;
	}
}
