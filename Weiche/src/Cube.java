import java.util.ArrayList;

public class Cube extends Body {
	String tie_type = "";

	Cube(Point c, double[] dimensions, double zrot, int method) {
		/*
		 * method 0: c is center of cube method 1: c is on lower left corner of cube
		 * method 2: c is center of upper plane method 3: c is center of front lower
		 * line
		 */
		double x = dimensions[0];
		double y = dimensions[1];
		double z = dimensions[2];
		this.verts.add(new Point(-x / 2., y / 2., z / 2.));
		this.verts.add(new Point(x / 2., y / 2., z / 2.));
		this.verts.add(new Point(-x / 2., -y / 2., z / 2.));
		this.verts.add(new Point(x / 2., -y / 2., z / 2.));
		this.verts.add(new Point(-x / 2., y / 2., -z / 2.));
		this.verts.add(new Point(x / 2., y / 2., -z / 2.));
		this.verts.add(new Point(-x / 2., -y / 2., -z / 2.));
		this.verts.add(new Point(x / 2., -y / 2., -z / 2.));

		switch (method) {
		case 1:
			this.move(x / 2., y / 2., z / 2.);
			break;
		case 2:
			this.move(0., 0., -z / 2.);
			break;
		case 3:
			this.move(0., y / 2., z);
			break;
		}

		this.move(c.getx(), c.gety(), c.getz());
		this.rotate(zrot);
	}

	public static Cube testCube(Point pos) {
		double[] dimensions = { 2, 2, 2 };
		return new Cube(pos, dimensions, 0., 0);
	}

	public Point getc() {
		Point p1 = verts.get(0);
		Point p2 = verts.get(7);
		return Vector.middle(p1, p2);
	}

	public double getrot() {
		Point a = Vector.subtract(verts.get(1), verts.get(0));
		Point b = new Point(1., 0., 0.);
		return Vector.getAngle(a, b);
	}

	public double wid() {
		return Vector.distPP(verts.get(0), verts.get(1));
	}

	// represents Cube as line to compute Kleineisen
	public Line represent() {
		Point c = Vector.middle(this.verts.get(0), this.verts.get(3));
		Point dir = Vector.subtract(this.getVerts().get(0), this.getVerts().get(2));
		Point d = Vector.add(c, dir);
		return new Line(c, d);
	}

	public void setType(String type) {
		this.tie_type = type;
	}

	public String getType() {
		return this.tie_type;
	}

	public String export_obj() {
		String ausgabe = "";
		ArrayList<Point> corners = this.getVerts();
		for (int i = 0; i < corners.size(); i++) {
			Point e = corners.get(i);
			ausgabe += "v " + e.getx() + " " + e.gety() + " " + e.getz() + "\n";
		}

		// define faces
		ausgabe += "f -8 -7 -5 -6\n" + "f -1 -2 -4 -3\n" + "f -8 -7 -3 -4\n" + "f -7 -5 -1 -3\n" + "f -5 -6 -2 -1\n"
				+ "f -8 -4 -2 -6\n\n";

		return ausgabe;
	}
}
