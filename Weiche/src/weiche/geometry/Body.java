package weiche.geometry;

import timo.geometry.Cube;
import timo.geometry.Cube.View;

import java.util.ArrayList;

public class Body {
	String type = "";
	
	void setType (String _type) {
		this.type = _type;
	}
	
	String getType() {
		return this.type;
	}
	
	public ArrayList<Point> verts = new ArrayList<Point>();

	public String export_obj() {
		System.out.println("Kein OBJ-Export in Klasse timo.geometry.Body!");
		return "";
	}

	public void move(double dx, double dy, double dz) {
		for (int i = 0; i < verts.size(); i++) {
			verts.get(i).move(dx, dy, dz);
		}
	}

	public void rotate(double dphi) {
		for (int i = 0; i < verts.size(); i++) {
			verts.get(i).rotate(dphi);
		}
	}

	public ArrayList<Point> getVerts() {
		return verts;
	}

	// maximum value in each axis
	public double[] max() {
		double[] ausgabe = new double[3];
		// initialize ausgabe with minimum values
		for (int i = 0; i < ausgabe.length; i++) {
			ausgabe[i] = Double.MIN_VALUE;
		}
		for (int i = 0; i < verts.size(); i++) {
			double[] xyz = verts.get(i).getxyz();
			for (int j = 0; j < xyz.length; j++) {
				if (xyz[j] > ausgabe[j])
					ausgabe[j] = xyz[j];
			}
		}
		return ausgabe;
	}

	// minimum value in each axis
	public double[] min() {
		double[] ausgabe = new double[3];
		// initialize ausgabe with maximum vlaues
		for (int i = 0; i < ausgabe.length; i++) {
			ausgabe[i] = Double.MAX_VALUE;
		}
		for (int i = 0; i < verts.size(); i++) {
			double[] xyz = verts.get(i).getxyz();
			for (int j = 0; j < xyz.length; j++) {
				if (xyz[i] < ausgabe[i])
					ausgabe[i] = xyz[i];
			}
		}
		return ausgabe;
	}
	
	public Cube boundingBox() {
		double[] min = this.min();
		double[] max = this.max();
		double[] dimensions = new double[3];
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i]=max[i]-min[i];
		}
		Point c = new Point(min);
		return new Cube(c,dimensions,0., Cube.View.LOWER_LEFT_CORNER);
	}

}
