package timo.vector;

import timo.geometry.Curve;

public class Point extends Curve {

	double x = 0., y = 0., z = 0.; // x y and z coordinates

	public Point(double _x, double _y, double _z) {
		x = _x;
		y = _y;
		z = _z;
	}

	public Point(double[] xyz) {
		x = xyz[0];
		y = xyz[1];
		z = xyz[2];
	}

	public Point() {
		// timo.vector.Point with coordinates [0,0,0]
	}

	public void move(double dx, double dy, double dz) {
		x += dx;
		y += dy;
		z += dz;
	}

	public void move(double[] dxyz) {
		x += dxyz[0];
		y += dxyz[1];
		z += dxyz[2];
	}

	public void rotate(double dphi) { // rotate a point around the center of [xy] axis
		double r = Vector.pythargoras(x, y);
		double phi = Math.atan(y / x);

		// Periodizität des Tangens
		if (x < 0.)
			phi += Math.PI;

		phi += dphi;

		x = r * Math.cos(phi);
		y = r * Math.sin(phi);
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public double getz() {
		return z;
	}

	public double len() {
		return Vector.distPP(this, Point.origin());
	}

	public Point normalize() {
		double len = this.len();
		return Vector.multiply(this, 1. / len);
	}

	public double[] getxyz() {
		double[] ausgabe = { x, y, z };
		return ausgabe;
	}

	public static Point origin() {
		return new Point();
	}

	public String export_obj() {
		return "v " + x + " " + y + " " + z + "\n";
	}
}
