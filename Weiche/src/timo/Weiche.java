package timo;

import timo.components.Kleineisen;
import timo.components.KleineisenCalculator;
import timo.components.Rail_computing;
import timo.components.Tie_computing;
import timo.geometry.Cube;
import timo.geometry.Curve;
import timo.io.Export_obj;

import java.io.IOException;
import java.util.ArrayList;

public class Weiche {

	public static final double radius = 200.;
	public static final double length =120.;
	public static final double gauge = 16.5;
	public static final double verlaengerung = 10.;

	public static final double frog_length = 20.; // Length of "Frog" rails
	public static final double guide_length = 20.; // Length of Guide rails
	public static final double flange_freedom = 1.;
	public static final double rail_foot = .5;
	public static final double rail_head = .3;
	public static final double rail_helper = (rail_foot - rail_head) / 2.;

	public static final double angle = Math.toRadians(40);

	public static final double tie_length = 20;
	public static final double tie_width = 2;
	public static final double tie_dist = 5;
	public static final double tie_height = 1;
	public static final double longest_tie = 40; //length of the longest tie

	public static final double klein_h = .1; // Höhe timo.geometry.Kleineisen
	public static final double klein_l = 1; // Länge timo.geometry.Kleineisen
	public static final double klein_b = 1; // Breite timo.geometry.Kleineisen
	public static final double klein_cyl_h = .3; // Höhe Schraube
	public static final double klein_cyl_d = .5;// Durchmesser SChraube

	public static ArrayList<Cube> tie_list = new ArrayList<>();
	public static ArrayList<Curve> rail_list = new ArrayList<>();
	public static ArrayList<Kleineisen> klein_list = new ArrayList<>();

	public static void main(String args[]) throws IOException {
		Tie_computing.berechnen();
		Rail_computing.compute();
		klein_list = KleineisenCalculator.berechnen();
		Export_obj.ausgabe();
		System.out.println("Fertig!");
	}

}
