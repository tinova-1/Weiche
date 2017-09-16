package nils.model;

import nils.components.Kleineisen;
import nils.geometry.Cube;
import nils.geometry.Curve;

import java.util.ArrayList;

public class Weiche {

    private final double radius = 200.;
    private final double length = 120.;
    private final double gauge = 16.5;
    private final double verlaengerung = 10.;

    private final double frog_length = 20.; // Length of "Frog" rails
    private final double guide_length = 20.; // Length of Guide rails
    private final double flange_freedom = 1.;
    private final double rail_foot = .5;
    private final double rail_head = .3;
    private final double rail_helper = (rail_foot - rail_head) / 2.;

    private final double angle = Math.toRadians(40);

    private final double tie_length = 20;
    private final double tie_width = 2;
    private final double tie_dist = 5;
    private final double tie_height = 1;
    private final double longest_tie = 40; //length of the longest tie

    private final double klein_h = .1; // Höhe timo.geometry.Kleineisen
    private final double klein_l = 1; // Länge timo.geometry.Kleineisen
    private final double klein_b = 1; // Breite timo.geometry.Kleineisen
    private final double klein_cyl_h = .3; // Höhe Schraube
    private final double klein_cyl_d = .5;// Durchmesser SChraubev

    private ArrayList<Cube> tie_list = new ArrayList<>();
    private ArrayList<Curve> rail_list = new ArrayList<>();
    private ArrayList<Kleineisen> klein_list = new ArrayList<>();

    public double getRadius() {
        return radius;
    }

    public double getLength() {
        return length;
    }

    public double getGauge() {
        return gauge;
    }

    public double getVerlaengerung() {
        return verlaengerung;
    }

    public double getFrog_length() {
        return frog_length;
    }

    public double getGuide_length() {
        return guide_length;
    }

    public double getFlange_freedom() {
        return flange_freedom;
    }

    public double getAngle() {
        return angle;
    }

    public double getTie_length() {
        return tie_length;
    }

    public double getTie_width() {
        return tie_width;
    }

    public double getTie_dist() {
        return tie_dist;
    }

    public double getTie_height() {
        return tie_height;
    }

    public double getLongest_tie() {
        return longest_tie;
    }

    public double getKlein_h() {
        return klein_h;
    }

    public double getKlein_cyl_h() {
        return klein_cyl_h;
    }

    public double getKlein_cyl_d() {
        return klein_cyl_d;
    }

    public ArrayList<Cube> getTie_list() {
        return tie_list;
    }

    public ArrayList<Curve> getRail_list() {
        return rail_list;
    }

    public ArrayList<Kleineisen> getKlein_list() {
        return klein_list;
    }
}
