package timo.components;

import timo.Weiche;
import timo.geometry.Arc;
import timo.geometry.Circle;
import timo.vector.Line;
import timo.vector.Point;
import timo.vector.Vector;

public class Rail_computing {
	public static double a = Weiche.angle;
	public static double len = Weiche.length;
	public static double gauge = Weiche.gauge;
	public static double flange = Weiche.flange_freedom;
	public static double frog = Weiche.frog_length;
	public static double guide = Weiche.guide_length;
	public static double approach = Weiche.verlaengerung;

	public static double r;
	public static double r_outer;
	public static double r_inner;

	public static void compute() {
		// gauge = timo.Weiche.gauge + versatz;

		r = Weiche.radius;
		r_outer = r + gauge / 2.;
		r_inner = r - gauge / 2.;

		// outer straight rail
		Point pm = new Point(0., -r, 0.);
		Point p0r = new Point(0, -gauge / 2., 0.);
		// double[] p0l = { 0, -gauge / 2. };
		Point p1l = new Point(len, gauge / 2., 0.);
		Point p1r = new Point(len, -gauge / 2., 0.);
		Point p0rf = new Point(0, -gauge / 2. + flange, 0.);
		// double[] p0lf = { 0, gauge / 2. + flange };
		Point p1rf = new Point(len, -gauge / 2. + flange, 0.);
		// double[] p1lf = { len, -gauge / 2. + flange };
		Point p8l = new Point(-approach, gauge / 2., 0.);
		Point p8r = new Point(-approach, -gauge / 2., 0.);

		Circle outer_radius = new Circle(pm, r_outer);
		Circle outer_flange = new Circle(pm, r_outer - flange);

		Line inner_straight = new Line(p0r, p1r);
		Line inner_flange = new Line(p0rf, p1rf);

		// Herst�ckspitze
		Point p3 = Vector.intersectionLC(inner_straight, outer_radius).get(0);
		Point p4 = Vector.intersectionLC(inner_flange, outer_radius).get(0);
		Point p5 = Vector.intersectionLC(inner_straight, outer_flange).get(0);
		Point p6 = new Point(p4.getx() + frog, -gauge / 2. + flange, 0.);

		double a1 = Math.asin(p3.getx() / r_outer);
		double a2 = Math.asin(p4.getx() / r_outer);
		double a3 = Math.asin(p5.getx() / (r_outer - flange));
		double a4 = a3 + frog / (r_outer - flange);

		Line l1l = new Line(p8l, p1l);
		Line l1r = new Line(p8r, p5);
		Line l3r = new Line(p3, p1r);
		Line l4l = new Line(p4, p6);
		
		l1l.setName("l1l");
		l1r.setName("l1r");
		l3r.setName("l3r");
		l4l.setName("l4l");
		l1l.setType("straight");
		l1r.setType("straight");
		l3r.setType("straight");
		l4l.setType("straight");

		Arc l2l = new Arc(pm, r_outer, 0., a2);
		Arc l2r = new Arc(pm, r_inner, 0., a);
		Arc l3l = new Arc(pm, r_outer, a1, a);
		Arc l4r = new Arc(pm, r_outer - flange, a3, a4);
		
		l2l.setName("l2l");
		l2r.setName("l2r");
		l3l.setName("l3l");
		l4r.setName("l4r");
		l2l.setType("curve");
		l2r.setType("curve");
		l3l.setType("curve");
		l4r.setType("curve");

		Weiche.rail_list.add(l1l);
		Weiche.rail_list.add(l1r);
		Weiche.rail_list.add(l3r);
		Weiche.rail_list.add(l4l);
		Weiche.rail_list.add(l2l);
		Weiche.rail_list.add(l2r);
		Weiche.rail_list.add(l3l);
		Weiche.rail_list.add(l4r);

	}
}
