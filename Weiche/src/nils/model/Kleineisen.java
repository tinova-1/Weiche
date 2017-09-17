package nils.model;

import nils.geometry.Cube;
import nils.geometry.Cylinder;

public class Kleineisen {

    public static final double klein_h = .1; // Höhe Kleineisen
    public static final double klein_l = 1; // Länge Kleineisen
    public static final double klein_b = 1; // Breite Kleineisen
    public static final double klein_cyl_h = .3; // Höhe Schraube
    public static final double klein_cyl_d = .5;// Durchmesser Schraube

    private Cube base;
    private Cylinder screw;

    public Cube getBase() {
        return base;
    }

    public Cylinder getScrew() {
        return screw;
    }

    public void move(double dx, double dy, double dz) {
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).move(dx, dy, dz);
        }
    }

    public void rotate(double phi) {
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).rotate(phi);
        }
    }

    public String export_obj() {
        String ausgabe = "";
        for (int i = 0; i < bodies.size(); i++) {
            ausgabe += bodies.get(i).export_obj();
        }
        return ausgabe;
    }
}
