package timo.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Import_obj {

	File obj = new File("timo.geometry.Kleineisen.obj");
	Scanner sc = new Scanner(obj);

	public Import_obj() throws FileNotFoundException {
	}

	String import_obj() {

		String ausgabe = "";
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.startsWith("v ") || line.startsWith("f ")) {
				ausgabe += sc.nextLine() + "\n";
			}
		}
		ausgabe +="\n";
		return ausgabe;

	}
}
