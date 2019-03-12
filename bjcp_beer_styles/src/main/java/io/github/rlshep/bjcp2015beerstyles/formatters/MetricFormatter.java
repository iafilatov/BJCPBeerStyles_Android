package io.github.rlshep.bjcp2015beerstyles.formatters;

import java.text.DecimalFormat;

public class MetricFormatter {
    private static final double SRM = 1.97;
    private static final double EBC_A = 616.868;
    private static final double EBC_B = 1111.14;
    private static final double EBC_C = 630.272;
    private static final double EBC_D = 135.997;

    //EBC = SRM × 1.97
    public static int getEBC(String inSRM) {
        int srm = 0;

        try {
            srm = (int)Math.round(new Integer(inSRM) * SRM);
        } catch (Exception e) {
            System.out.println(e);
        }

        return srm;
    }

    // (-1 * 616.868) + (1111.14 * SG) – (630.272 * SG^2) + (135.997 * SG^3)
    public static double getPlato(String inGravity) {
        DecimalFormat df = new DecimalFormat("#.#");
        double plato = 0.0;

        try {
            Double gravity = new Double(inGravity);
            plato = ((EBC_A * -1) + (EBC_B * gravity) - (EBC_C * Math.pow(gravity, 2)) + (EBC_D * Math.pow(gravity, 3)));
        } catch (Exception e) {
            System.out.println(e);
        }

        return Double.valueOf(df.format(plato));
    }
}