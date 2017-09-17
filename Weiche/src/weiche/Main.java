package weiche;

import weiche.components.KleineisenCalculator;
import weiche.components.Rail_computing;
import weiche.components.Tie_computing;
import weiche.io.Export_obj;
import weiche.model.Weiche;

public class Main {

    public static void main(String[] args){
		Tie_computing.berechnen();
		Rail_computing.compute();
		klein_list = KleineisenCalculator.berechnen();
		Export_obj.ausgabe();
		System.out.println("Fertig!");

    }

}
