package nils.geometry;

import nils.vector.Line;
import nils.vector.Point;
import nils.vector.VectorUtil;

import java.util.ArrayList;
import java.util.List;

public class Cube extends Body{

    private ArrayList<Point> verts;

    public enum View {
        CENTER_OF_CUBE(0, 0, 0), LOWER_LEFT_CORNER(0.5, 0.5, 0.5), CENTER_UPPER_PLANE(0, 0, -0.5), CENTER_FRONT_LOWER_LINE(0, 0.5, 0.5);

        private double dx, dy, dz;

        View(double dx, double dy, double dz) {
            this.dx = dx;
            this.dy = dy;
            this.dz = dz;
        }

        public List<Point> move(List<Point> points, double x, double y, double z) {
            points.forEach((Point p) -> p.move(this.dx * x, this.dy * y, this.dz * z));
            return points;
        }

    }

    public Cube(Point c, double[] dimensions, double zrot, View method) {
        verts = new ArrayList<>();
        double x = dimensions[0];
        double y = dimensions[1];
        double z = dimensions[2];
        this.verts.add(new Point(-x / 2., y / 2., z / 2.));
        this.verts.add(new Point(x / 2., y / 2., z / 2.));
        this.verts.add(new Point(-x / 2., -y / 2., z / 2.));
        this.verts.add(new Point(x / 2., -y / 2., z / 2.));
        this.verts.add(new Point(-x / 2., y / 2., -z / 2.));
        this.verts.add(new Point(x / 2., y / 2., -z / 2.));
        this.verts.add(new Point(-x / 2., -y / 2., -z / 2.));
        this.verts.add(new Point(x / 2., -y / 2., -z / 2.));

        method.move(this.verts, x, y, z);

        this.move(c.getx(), c.gety(), c.getz());
        this.rotate(zrot);
    }


    public Point getc() {
        Point p1 = verts.get(0);
        Point p2 = verts.get(7);
        return VectorUtil.middle(p1, p2);
    }

    public double getrot() {
        Point a = VectorUtil.subtract(verts.get(1), verts.get(0));
        Point b = new Point(1., 0., 0.);
        return VectorUtil.getAngle(a, b);
    }

    public double wid() {
        return VectorUtil.distPP(verts.get(0), verts.get(1));
    }

    // represents timo.geometry.Cube as line to compute timo.geometry.Kleineisen
    public Line represent() {
        Point c = VectorUtil.middle(this.verts.get(0), this.verts.get(3));
        Point dir = VectorUtil.subtract(this.getVerts().get(0), this.getVerts().get(2));
        Point d = VectorUtil.add(c, dir);
        return new Line(c, d);
    }

    public String export_obj() {
        String ausgabe = "";
        ArrayList<Point> corners = this.getVerts();
        for (int i = 0; i < corners.size(); i++) {
            Point e = corners.get(i);
            ausgabe += "v " + e.getx() + " " + e.gety() + " " + e.getz() + "\n";
        }

        // define faces
        ausgabe += "f -8 -7 -5 -6\n" + "f -1 -2 -4 -3\n" + "f -8 -7 -3 -4\n" + "f -7 -5 -1 -3\n" + "f -5 -6 -2 -1\n"
                + "f -8 -4 -2 -6\n\n";

        return ausgabe;
    }
}
