/*import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;*/

public class Export_svg {

	/*public static void ausgabe() throws IOException {

		FileWriter ausgabe = new FileWriter(new File("ausgabe.svg"));

		File header = new File("Header.txt");
		File footer = new File("Footer.txt");

		Scanner sc_header = new Scanner(header);
		Scanner sc_footer = new Scanner(footer);

		while (sc_header.hasNextLine()) {
			ausgabe.write(sc_header.nextLine() + "\n");
		}

		/////////// Ties
		ArrayList<Tie> tie_list = Weiche.tie_list;
		for (int j = 0; j < tie_list.size(); j++) {
			Tie i = tie_list.get(j);

			double svg_x = i.xpos - i.wid / 2.;
			double svg_y = i.ypos - i.len / 2.;
			double svg_rot = Math.toDegrees(i.rot);
			int svg_id = j;

			ausgabe.write("    <rect\n");
			ausgabe.write("       id=\"" + svg_id + "\"\n");
			ausgabe.write("       width=\"" + i.wid + "\"\n");
			ausgabe.write("       height=\"" + i.len + "\"\n");
			ausgabe.write("       x=\"" + svg_x + "\"\n");
			ausgabe.write("       y=\"" + svg_y + "\"\n");
			ausgabe.write("       transform=\"rotate(" + svg_rot + ")\"\n");
			ausgabe.write("       />\n");
		}

		/////////// Rail
		ArrayList<Curve> rail_list = Weiche.rail_list;
		for (int j = 0; j < rail_list.size(); j++) {
			Curve i = rail_list.get(j);

			if (i instanceof Line) {
				Line e = (Line) i;
				double x = e.getp1()[0];
				double y = e.getp1()[1];

				double dx = e.getDir()[0];
				double dy = e.getDir()[1];

				ausgabe.write(
						"    <path\n"+
						"       style=\"fill:none;stroke:#000000;stroke-width:0.2px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\"\n"+
						"       d=\"m " + x + "," + y + " " + dx + "," + dy + "\"\n"+
						"       id=\"line"+j+"\" />\n");
			} else if(i instanceof Arc) {
				Arc e = (Arc) i;
				double r = e.getr();
				double start = e.getStart()-Math.PI/2.;
				double end = e.getEnd()-Math.PI/2.;
				double x = e.getc()[0];
				double y = e.getc()[1];
				ausgabe.write(
						"    <path\r\n" + 
						"       id=\"arc"+j+"\"\r\n" + 
						"       style=\"fill:none;stroke:#000000;stroke-width:0.2;stroke-opacity:1\"\r\n" + 
						"       sodipodi:type=\"arc\"\r\n" + 
						"       sodipodi:cx=\""+x+"\"\r\n" + 
						"       sodipodi:cy=\""+y+"\"\r\n" + 
						"       sodipodi:rx=\""+r+"\"\r\n" + 
						"       sodipodi:ry=\""+r+"\"\r\n" + 
						"       sodipodi:start=\""+start+"\"\r\n" + 
						"       sodipodi:end=\""+end+"\"\r\n" + 
						//"       d=\"m 4.4789846,301.60095 a 5.909306,6.8608041 0 0 1 -4.17851037,2.00949\"\r\n" + 
						"       sodipodi:open=\"true\" />\n");
			}

		}


		while (sc_footer.hasNextLine()) {
			ausgabe.write(sc_footer.nextLine() + "\n");
		}

		sc_header.close();
		sc_footer.close();
		ausgabe.close();
	}*/
}