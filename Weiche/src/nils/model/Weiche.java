package nils.model;

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

    private final double angle = Math.toRadians(40);

    private ArrayList<Tie> tie_list = new ArrayList<>();
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

    public ArrayList<Tie> getTie_list() {
        return tie_list;
    }

    public ArrayList<Curve> getRail_list() {
        return rail_list;
    }

    public ArrayList<Kleineisen> getKlein_list() {
        return klein_list;
    }
}
