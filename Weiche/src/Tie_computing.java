import java.util.ArrayList;

public class Tie_computing {

	static double w_radius = Weiche.radius;
	static double winkel = Weiche.angle;
	static double l = Weiche.length;
	static double s_laenge = Weiche.tie_length;
	static double s_abstand = Weiche.tie_dist;
	static double s_breite = Weiche.tie_width;
	static double s_hoehe = Weiche.tie_height;
	static double verlaengerung = Weiche.verlaengerung;
	static double l_max = Weiche.longest_tie;

	static void berechnen() {
		ArrayList<Cube> weichenSchwellenband = weichenSchwellenband();
		for (int i = 0; i < weichenSchwellenband.size(); i++) {
			weichenSchwellenband.get(i).setType("turnout");
		}
		Weiche.tie_list.addAll(weichenSchwellenband);

		// gerades Schwellenband nach Weichenschwellenband
		Cube letzteSchwelle = weichenSchwellenband.get(weichenSchwellenband.size() - 1);
		double beginn_gerade = letzteSchwelle.max()[0] + letzteSchwelle.wid() / 2. + s_abstand / 2.;
		ArrayList<Cube> geradesSchwellenband = geradesSchwellenband(beginn_gerade, l);
		for (int i = 0; i < geradesSchwellenband.size(); i++) {
			geradesSchwellenband.get(i).setType("straight");
		}
		Weiche.tie_list.addAll(geradesSchwellenband);

		// gebogenes Schwellenband nach Weichenschwellenband
		double beginn_kurve = beginn_gerade / w_radius;
		ArrayList<Cube> gebogenesSchwellenband = gebogenesSchwellenband(beginn_kurve, winkel, w_radius);
		for (int i = 0; i < gebogenesSchwellenband.size(); i++) {
			gebogenesSchwellenband.get(i).setType("curve");
		}
		Weiche.tie_list.addAll(gebogenesSchwellenband);

		// gerades Verlängerungsschsellenband vor Weichenschwellenband
		ArrayList<Cube> verlaengerungSchwelle = geradesSchwellenband(-s_abstand, -verlaengerung);
		for (int i = 0; i < verlaengerungSchwelle.size(); i++) {
			verlaengerungSchwelle.get(i).setType("approach");
		}
		Weiche.tie_list.addAll(verlaengerungSchwelle);
	}

	static ArrayList<Cube> geradesSchwellenband(double start, double end) {
		ArrayList<Cube> ausgabe = new ArrayList<Cube>();
		double[] dimensions = { s_breite, s_laenge, s_hoehe };
		if (start <= end) {
			for (double x = start; x <= end; x += s_abstand) {
				ausgabe.add(new Cube(new Point(x, 0., 0.), dimensions, 0., 2));
			}
		} else {
			for (double x = start; x >= end; x -= s_abstand) {
				ausgabe.add(new Cube(new Point(x, 0., 0.), dimensions, 0., 2));
			}
		}
		return ausgabe;
	}

	static ArrayList<Cube> gebogenesSchwellenband(double start, double end, double radius) {
		ArrayList<Cube> ausgabe = new ArrayList<Cube>();
		double dphi = s_abstand / radius; // delta phi
		for (double phi = start; phi <= end; phi += dphi) {
			double[] dimensions = { s_breite, s_laenge, s_hoehe };
			Cube newtie = new Cube(Point.origin(), dimensions, 0., 2);
			newtie.move(0., radius, 0.);
			newtie.rotate(-phi);
			newtie.move(0., -radius, 0.);
			ausgabe.add(newtie);
		}
		return ausgabe;
	}

	static ArrayList<Cube> weichenSchwellenband() {
		// boolean schwellenband_erstellt = false; // Speichert, ob
		// Einzel-Schwellenbänder bereits erstellt wurden
		double s_radius_rechts = 2. * w_radius + s_laenge / 2.;
		double winkelschritt = s_abstand / (2. * w_radius);
		double winkel; // Drehwinkel der aktuellen Weichenrost-Schwelle

		// double laengste_schwelle = s_laenge * 2; // Länge der längsten Schwelle
		ArrayList<Cube> ausgabe = new ArrayList<Cube>();
		double s_laenge_neu = s_laenge;
		for (winkel = 0.; s_laenge_neu <= l_max; winkel += winkelschritt) {
			s_laenge_neu = (s_radius_rechts / Math.cos(winkel) - 2. * w_radius) * 2.;
			double[] dimensions = { s_breite, s_laenge_neu, s_hoehe };
			Cube newtie = new Cube(Point.origin(), dimensions, 0., 2);
			newtie.move(0., w_radius * 2., 0.);
			newtie.rotate(-winkel);
			newtie.move(0., -w_radius * 2., 0.);
			ausgabe.add(newtie);
		}
		return ausgabe;
	}
}
