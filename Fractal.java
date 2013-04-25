/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 4/24/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */

public class Fractal {
    public static void main(String[] args) {
        int dimr = 21;
        int dimi = 21;
        int[][] grid = new int[dimr][dimi];
        int maxIteration = 40;

        double rmin = -2.0;
        double rmax = 0.7;
        double rinc = (rmax - rmin) / (double) dimr;

        double imin = -1.0;
        double imax = 1.0;
        double iinc = (imax - imin) / (double) dimi;

        for (int i = 0; i < dimr; i++) {
            for (int j = 0; j < dimi; j++) {
                Complex z = new Complex(0.0, 0.0);
                Complex c = new Complex(rmin + rinc * i, imin + iinc * j);
                for (int k = 0; k < maxIteration; k++) {
                    z = z.power(2).plus(c);
                }
                if (z.abs() < 100000000.0) {
                    grid[i][j] = 1;
                }
            }
        }

        //Print Grid
        for (int i = 0; i < dimr; i++) {
            String t = "";
            for (int j = 0; j < dimi; j++) {
                t = t.concat(Integer.toString(grid[j][i]));
            }
            System.out.println(t);
        }
    }
}
