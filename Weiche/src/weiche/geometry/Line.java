package weiche.geometry;

import timo.geometry.Curve;

import java.util.ArrayList;

public class Line extends Curve {
	Point p1;
	Point p2;
	Point direction;

	double a;
	double b;
	double c;

	public Line(Point pos1, Point pos2) {
		p1 = pos1;
		p2 = pos2;
		direction = Vector.subtract(p1, p2);

		a = p1.gety() - p2.gety();
		b = p2.getx() - p1.getx();
		c = p2.getx() * p1.gety() - p1.getx() * p2.gety();
	}

	// Koordinatenform ax+by=c

	public Point getDir() {
		return direction;
	}

	public Point getp1() {
		return p1;
	}

	Point getp2() {
		return p2;
	}

	double geta() {
		return a;
	}

	double getb() {
		return b;
	}

	double getc() {
		return c;
	}

	public ArrayList<Point> getVerts(int res) {
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		ausgabe.add(getp1());
		ausgabe.add(getp2());
		return ausgabe;
	}

	public String export_obj() {
		String ausgabe = "";
		ausgabe += "o timo.vector.Line\n";
		ausgabe += p1.export_obj();
		ausgabe += p2.export_obj();
		ausgabe += "f -1 -2\n";
		return ausgabe;
	}
}
