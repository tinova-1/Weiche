import java.io.IOException;
import java.util.ArrayList;

public class Weiche {

	static final double radius = 200.;
	static final double length =120.;
	static final double gauge = 16.5;
	static final double verlaengerung = 10.;

	static final double frog_length = 20.; // Length of "Frog" rails
	static final double guide_length = 20.; // Length of Guide rails
	static final double flange_freedom = 1.;
	static final double rail_foot = .5;
	static final double rail_head = .3;
	static final double rail_helper = (rail_foot - rail_head) / 2.;

	static final double angle = Math.toRadians(40);

	public static final double tie_length = 20;
	public static final double tie_width = 2;
	public static final double tie_dist = 5;
	public static final double tie_height = 1;
	public static final double longest_tie = 40; //length of the longest tie

	public static final double klein_h = .1; // Höhe Kleineisen
	public static final double klein_l = 1; // Länge Kleineisen
	public static final double klein_b = 1; // Breite Kleineisen
	public static final double klein_cyl_h = .3; // Höhe Schraube
	public static final double klein_cyl_d = .5;// Durchmesser SChraube

	public static ArrayList<Cube> tie_list = new ArrayList<Cube>();
	public static ArrayList<Curve> rail_list = new ArrayList<Curve>();
	public static ArrayList<Kleineisen> klein_list = new ArrayList<Kleineisen>();

	public static void main(String args[]) throws IOException {
		Tie_computing.berechnen();
		Rail_computing.compute();
		klein_list = Kleineisen_berechnen.berechnen();
		Export_obj.ausgabe();
		System.out.println("Fertig!");
	}

}
