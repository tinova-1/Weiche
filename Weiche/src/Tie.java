public class Tie {

	double xpos, ypos, rot, len, wid;

	Tie(double x, double y, double r, double l) {
		xpos = x;
		ypos = y;
		rot = r;
		len = l;
		wid = Weiche.tie_width;
	}

	void translate(double dx, double dy) {
		xpos += dx;
		ypos += dy;
	}

	void rotate(double phi) {
		// rotates around [0,0], phi in radians
		double r = Math.sqrt(Math.pow(xpos, 2.) + Math.pow(ypos, 2.));
		double theta = Math.atan2(xpos, ypos);

		theta += phi;

		xpos = r * Math.sin(theta);
		ypos = r * Math.cos(theta);

		rot += phi;
	}

	public double xmax() {
		return xpos + wid / 2. + len / 2. * Math.sin(rot);
	}
}
