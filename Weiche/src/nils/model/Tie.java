package nils.model;

import nils.geometry.Cube;

public class Tie {

    public static final double tie_length = 20;
    public static final double tie_width = 2;
    public static final double tie_dist = 5;
    public static final double tie_height = 1;
    public static final double longest_tie = 40; //length of the longest tie

    private Cube representation;

    private Tie(){}

    public Tie(Cube representation) {
        this.representation = representation;
    }

    public Cube getRepresentation() {
        return representation;
    }
}
