import java.util.ArrayList;

public class Kleineisen_berechnen {
	static ArrayList<Cube> tie_list = Weiche.tie_list;
	static ArrayList<Curve> rail_list = Weiche.rail_list;
	static double tie_dist = Weiche.tie_dist;

	public static ArrayList<Kleineisen> berechnen() {
		ArrayList<Kleineisen> ausgabe = new ArrayList<Kleineisen>();

		// ArrayList<Point> test = new ArrayList<Point>();
		// for(int i=0; i<tie_list.size();i++) {
		// test.add(tie_list.get(i).represent().getp1());
		// }
		// for (int i = 0; i <test.size(); i++) {
		// tie_list.add(Cube.testCube(test.get(i)));
		// }
		//

		// Point points = Vector.intersection(tie_list.get(10).represent(),
		// rail_list.get(0)).get(0);

		// for(int i=0; i<tie_list.size(); i++) {
		//
		// ArrayList<Point> points =
		// Vector.intersArray(tie_list.get(i).represent(),rail_list);
		// for(int j = 0; j<points.size();j++) {
		// //System.out.println(points.get(j).getx()+" "+points.get(j).gety());
		// Kleineisen newklein = new Kleineisen();
		// Point pos = points.get(j);
		// newklein.move(pos.getx(),pos.gety(),pos.getz());
		// ausgabe.add(newklein);
		// }
		// }

		double res = .1; // resolution in mm
		double tol = .2; // tolerance in mm
		for (int i = 0; i < rail_list.size(); i++) {
			if (rail_list.get(i) instanceof Line) {
				Line rail = (Line) rail_list.get(i);
				for (int j = 0; j < tie_list.size(); j++) {
					Cube tie = tie_list.get(j);
					Line tie_line = tie.represent();
					for (double x = 0.; x <= rail.getDir().len(); x += res) {
						Point p1 = rail.getp1();
						Point dir = Vector.multiply(rail.getDir().normalize(), x);
						// p: laufender Punkt auf der Strecke
						Point p = Vector.add(p1, dir);

						if (match(tie, rail) && Vector.distPL(p, tie_line) <= tol) {
							Kleineisen newklein = new Kleineisen();
							newklein.move(p.getx(), p.gety(), p.getz());
							ausgabe.add(newklein);
							x += tie_dist / 2.;
						}
					}
				}

			} else if (rail_list.get(i) instanceof Arc) {

				Arc rail = (Arc) rail_list.get(i);

				for (int j = 0; j < tie_list.size(); j++) {
					Cube tie = tie_list.get(j);
					Line tie_line = tie.represent();
					if (match(tie, rail) && Vector.intersectionLA(tie_line, rail).size() > 0) {

						Point p = Vector.intersectionLA(tie_line, rail).get(0);

						Point arc_center = rail.getc();
						double arc_angle = Math.atan((p.gety() - arc_center.gety()) / (p.getx() - arc_center.getx()));
						// Periodizität des Tangens
						if (p.getx() - arc_center.getx() < 0.) {
							arc_angle += Math.PI;
						}

						Kleineisen newklein = new Kleineisen();
						newklein.rotate(arc_angle);
						newklein.move(p.getx(), p.gety(), p.getz());
						ausgabe.add(newklein);
					}
				}
			}
		}
		return ausgabe;
	}

	private static boolean match(Cube tie, Curve rail) {
		String tie_type = tie.getType();
		String rail_type = rail.getType();

		if (rail_type == tie_type) {
			return true;
		} else if (rail_type == "curve" && tie_type == "turnout") {
			return true;
		} else if (rail_type == "straight" && tie_type == "turnout") {
			return true;
		} else if (rail_type == "straight" && tie_type == "approach") {
			return true;
		} else {
			return false;
		}
	}
}
