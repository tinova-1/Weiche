import java.util.ArrayList;

public class Curve {
	String name = "";
	String type = "";

	ArrayList<Point> getVerts(int res) {
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		return ausgabe;
	}

	String export_obj() {
		return "";
	}

	void setName(String _name) {
		name = _name;
	}
	
	String getName() {
		return name;
	}
	
	void setType(String _type) {
		this.type = _type;
	}
	
	String getType() {
		return this.type;
	}
}
