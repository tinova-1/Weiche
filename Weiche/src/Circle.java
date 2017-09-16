import java.util.ArrayList;

public class Circle extends Curve {
	Point c; // center point
	double r; // radius

	Circle(Point center, double radius) {
		c = new Point(center.getx(),center.gety(),0.);
		r = radius;
	}

	Point getc() {
		return c;
	}

	double getr() {
		return r;
	}

	ArrayList<Point> getVerts(int res) {
		// res: resolution (number of verts)
		ArrayList<Point> verts = new ArrayList<Point>();
		double dphi = 2. * Math.PI / (double) res;
		for (double phi = 0; phi < 2. * Math.PI; phi += dphi) {
			Point vert = new Point(r, 0., 0.);
			vert.rotate(phi);
			verts.add(vert);
		}
		return verts;
	}

	String export_obj() {
		String ausgabe = "";
		ArrayList<Point> verts = this.getVerts(48);
		for (int i = 0; i < verts.size()-1; i++) {
			ausgabe += verts.get(i).export_obj();
			ausgabe += verts.get((i + 1) % verts.size()).export_obj();
			ausgabe += "f -1 -2\n";
		}
		return ausgabe;
	}
}
