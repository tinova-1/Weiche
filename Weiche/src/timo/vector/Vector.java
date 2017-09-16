package timo.vector;

import timo.geometry.Arc;
import timo.geometry.Circle;
import timo.geometry.Curve;

import java.util.ArrayList;

public class Vector {

	public static ArrayList<Point> intersArray(Curve c1, ArrayList<Curve> c2) {
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		for (int i = 0; i < c2.size(); i++) {
			ausgabe.addAll(intersection(c1, c2.get(i)));
		}
		return ausgabe;
	}

	public static ArrayList<Point> intersection(Curve c1, Curve c2) {
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		if (c1 instanceof Line && c2 instanceof Line) {
			ausgabe.addAll(intersectionLS((Line) c1, (Line) c2));
		} else if (c1 instanceof Line && c2 instanceof Arc) {
			ausgabe.addAll(intersectionLA((Line) c1, (Arc) c2));
		} else if (c1 instanceof Arc && c2 instanceof Line) {
			ausgabe.addAll(intersectionLA((Line) c2, (Arc) c1));
		} else if (c1 instanceof Line && c2 instanceof Circle) {
			ausgabe.addAll(intersectionLC((Line) c1, (Circle) c2));
		} else if (c2 instanceof Line && c1 instanceof Circle) {
			ausgabe.addAll(intersectionLC((Line) c2, (Circle) c1));
		}
		return ausgabe;
	}

	public static Point intersectionLL(Line line1, Line line2) {
		double a1 = line1.geta();
		double b1 = line1.getb();
		double c1 = line1.getc();
		double a2 = line2.geta();
		double b2 = line2.getb();
		double c2 = line2.getc();

		// Schnittpunkt
		double xs = (c1 * b2 - c2 * b1) / (a1 * b2 - a2 * b1);
		double ys = (a1 * c2 - a2 * c1) / (a1 * b2 - a2 * b1);
		Point s = new Point(xs, ys, 0.);
		return s;
	}

	// s = Strecke
	public static ArrayList<Point> intersectionLS(Line l, Line s) {
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		Point p = intersectionLL(l, s); // Schnittpunkt beider Geraden
		double _P1P2 = s.getDir().len();
		double _P1P = Vector.distPP(s.getp1(), p);
		if (_P1P2 >= _P1P)
			ausgabe.add(p);
		return ausgabe;
	}

	public static ArrayList<Point> intersectionLC(Line line, Circle circle) {
		// Bezeichnungen siehe Wikipedia
		double a = line.geta();
		double b = line.getb();
		double c = line.getc();
		double x0 = circle.getc().getx();
		double y0 = circle.getc().gety();
		double r = circle.getr();

		double d = c - a * x0 - b * y0;

		double x1 = x0 + (a * d + b * rt(sq(r) * (sq(a) + sq(b)) - sq(d))) / (sq(a) + sq(b));
		double x2 = x0 + (a * d - b * rt(sq(r) * (sq(a) + sq(b)) - sq(d))) / (sq(a) + sq(b));
		double y1 = y0 + (b * d - a * rt(sq(r) * (sq(a) + sq(b)) - sq(d))) / (sq(a) + sq(b));
		double y2 = y0 + (b * d + a * rt(sq(r) * (sq(a) + sq(b)) - sq(d))) / (sq(a) + sq(b));

		ArrayList<Point> s = new ArrayList<Point>();

		if (!Double.isNaN(x1)) {
			s.add(new Point(x1, y1, 0.));
			if (!(x1 == x2 && y1 == y2))
				s.add(new Point(x2, y2, 0.));
		}

		return s;
	}

	public static ArrayList<Point> intersectionLA(Line line, Arc arc) {
		// intersections between line and circle, to test if it line crosses arc
		ArrayList<Point> ausgabe = new ArrayList<Point>();
		Point arc_center = arc.getc();
		ArrayList<Point> inters = intersectionLC(line, (Circle) arc);
		for (int i = 0; i < inters.size(); i++) {
			Point p = inters.get(i);
			double arc_angle = Math.atan((p.gety() - arc_center.gety()) / (p.getx() - arc_center.getx()));
			//Periodizit�t des Tangens
			if(p.getx()-arc_center.getx()<0.) {
				arc_angle +=Math.PI;
			}

			// double arc_angle = Math
			// .atan((arc_center.gety() - inters.get(i).gety()) / (arc_center.getx() -
			// inters.get(i).getx()));
			if (arc.getStart() <= arc_angle && arc_angle <= arc.getEnd()) {
				ausgabe.add(inters.get(i));
			}
		}
		return ausgabe;
	}

	public static double distPP(Point p1, Point p2) {
		double x1 = p1.getx();
		double y1 = p1.gety();
		double z1 = p1.getz();
		double x2 = p2.getx();
		double y2 = p2.gety();
		double z2 = p2.getz();

		// Pythargoras
		return rt(sq(x2 - x1) + sq(y2 - y1) + sq(z2 - z1));
	}

	public static double distPL(Point p, Line l) {
		Point a = l.getp1();
		Point b = l.getDir();
		return Vector.crossP(Vector.subtract(p, a), b).len() / b.len();
	}

	public static double scalar(Point p1, Point p2) {
		double a1 = p1.getx();
		double a2 = p1.gety();
		double a3 = p1.getz();
		double b1 = p2.getx();
		double b2 = p2.gety();
		double b3 = p2.getz();
		return a1 * b1 + a2 * b2 + a3 * b3;
	}

	public static Point crossP(Point p1, Point p2) {
		double x = p1.gety() * p2.getz() - p2.gety() * p1.getz();
		double y = p1.getz() * p2.getx() - p2.getz() * p1.getx();
		double z = p1.getx() * p2.gety() - p2.getx() * p1.gety();

		return new Point(x, y, z);
	}

	private static double rt(double arg) { // square root
		return Math.sqrt(arg);
	}

	private static double sq(double arg) { // square number
		return Math.pow(arg, 2.);
	}

	public static double pythargoras(double a, double b) {
		return rt(sq(a) + sq(b));
	}

	public static Point add(Point p1, Point p2) {
		double x = p2.getx() + p1.getx();
		double y = p2.gety() + p1.gety();
		double z = p2.getz() + p1.getz();
		return new Point(x, y, z);
	}

	public static Point subtract(Point p1, Point p2) {
		double x = p2.getx() - p1.getx();
		double y = p2.gety() - p1.gety();
		double z = p2.getz() - p1.getz();
		return new Point(x, y, z);
	}

	public static Point multiply(Point p1, double factor) {
		double x = p1.getx() * factor;
		double y = p1.gety() * factor;
		double z = p1.getz() * factor;
		return new Point(x, y, z);
	}

	public static Point middle(Point p1, Point p2) {
		return Vector.multiply(Vector.add(p1, p2), .5);
	}

	public static double getAngle(Point a, Point b) {
		return Math.acos(Vector.scalar(a, b) / (a.len() * b.len()));
	}
}
