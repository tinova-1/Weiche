package nils.geometry;

import nils.vector.Point;

import java.util.ArrayList;

public abstract class Curve {

    String name = "";
    Type type;

    public ArrayList<Point> getVerts(int res) {
        ArrayList<Point> ausgabe = new ArrayList<Point>();
        return ausgabe;
    }

    public String export_obj() {
        return "";
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setType(Type _type) {
        this.type = _type;
    }

    public Type getType() {
        return this.type;
    }


    // ENUM eindeutig vergleichbar
    public enum Type {
        STRAIGHT, CURVE, TURNOUT;
    }

}
