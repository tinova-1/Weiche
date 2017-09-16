import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Export_obj {

	public static void ausgabe() throws IOException {
		ArrayList<Cube> tie_list = Weiche.tie_list;
		ArrayList<Kleineisen> klein_list = Weiche.klein_list;
//		ArrayList<Curve> rail_list = Weiche.rail_list;

		FileWriter ausgabe = new FileWriter(new File("ausgabe.obj"));

		for (int i = 0; i < tie_list.size(); i++) {
			ausgabe.write("g Tie" + i + "\n\n");
			ausgabe.write(tie_list.get(i).export_obj());
		}

		for (int i = 0; i < klein_list.size(); i++) {
			ausgabe.write("g Kleineisen"+i+"\n\n");
			ausgabe.write(klein_list.get(i).export_obj());
		}
		
		//rails
//		for(int i=0; i<rail_list.size();i++) {
//			ausgabe.write("o Rail"+i+"\n\n");
//			ausgabe.write(rail_list.get(i).export_obj());
//		}
//		System.out.println(rail_list.size());

		ausgabe.close();
	}

}
