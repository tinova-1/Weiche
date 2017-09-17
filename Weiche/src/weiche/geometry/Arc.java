package weiche.geometry;

import java.util.ArrayList;

public class Arc extends Circle {
	// start and end angles
	double st;
	double en;

	// input: angles as seen from turnout, st and en values: correct mathematical
	// values
	public Arc(Point center, double radius, double start, double end) {
		super(center, radius);

		st = Math.PI * .5-end;
		en = Math.PI * .5-start;
	}

	public double getStart() {
		return st;
	}

	public double getEnd() {
		return en;
	}

	public ArrayList<Point> getVerts(int res) {
		// res: resolution (number of verts in one circle)
		ArrayList<Point> verts = new ArrayList<Point>();
		double dphi = 2. * Math.PI / (double) res;
		for (double phi = st; phi < en; phi += dphi) {
			Point vert = new Point(r, 0., 0.);
			vert.rotate(phi);
			verts.add(vert);
		}
		Point lastvert = new Point(r, 0., 0.);
		lastvert.rotate(en);
		verts.add(lastvert);
		return verts;
	}

	public String export_obj() {
		String ausgabe = "";
		ArrayList<Point> verts = this.getVerts(48);
		for (int i = 0; i < verts.size() - 1; i++) {
			ausgabe += verts.get(i).export_obj();
			ausgabe += verts.get(i + 1).export_obj();
			ausgabe += "f -1 -2\n";
		}
		return ausgabe;
	}

}
