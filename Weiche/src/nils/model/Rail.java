package nils.model;

import nils.geometry.Curve;

public class Rail {

    public static final double rail_foot = .5;
    public static final double rail_head = .3;
    public static final double rail_helper = (rail_foot - rail_head) / 2.;

    private Curve representation;

    private Rail() {}

    public Rail(Curve representation) {
        this.representation = representation;
    }
}
