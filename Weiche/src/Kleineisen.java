import java.util.ArrayList;

public class Kleineisen {
	ArrayList<Body> bodies = new ArrayList<Body>();
	double l = Weiche.klein_l;
	double h = Weiche.klein_h;
	double b = Weiche.klein_b;
	double cyl_h = Weiche.klein_cyl_h;
	double cyl_d = Weiche.klein_cyl_d;

	public Kleineisen() {
		double[] dimensions = { l, b, h };
		Body platte = new Cube(Point.origin(), dimensions, 0., 2);
		Body schraube = new Cylinder(cyl_d / 2., cyl_h, 6);

		bodies.add(platte);
		bodies.add(schraube);

		this.move(0., 0., h);
	}

	public void move(double dx, double dy, double dz) {
		for (int i = 0; i < bodies.size(); i++) {
			bodies.get(i).move(dx, dy, dz);
		}
	}

	public void rotate(double phi) {
		for (int i = 0; i < bodies.size(); i++) {
			bodies.get(i).rotate(phi);
		}
	}

	String export_obj() {
		String ausgabe = "";
		for (int i = 0; i < bodies.size(); i++) {
			ausgabe += bodies.get(i).export_obj();
		}
		return ausgabe;
	}
}
