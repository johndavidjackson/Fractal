/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 4/25/13
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class FractalFactory {

    public static final int MANDELBROT = 0;
    public static final int JULIA1 = 1;
    public static final int JULIA2 = 2;
    public static final int JULIA3 = 3;
    public static final int JULIA4 = 4;
    public static final int JULIA5 = 5;

    private int function;

    private int dimr;
    private int dimi;
    private int[][] grid;
    private double[][] grid2;
    private int maxIteration;

    private double rmin;
    private double rmax;
    private double rinc;

    private double imin;
    private double imax;
    private double iinc;

    public FractalFactory() {
        this.dimr = 1200;
        this.dimi = 700;
        this.grid = new int[dimr][dimi];
        this.grid2 = new double[dimr][dimi];
        this.maxIteration = 50;

        this.function = FractalFactory.MANDELBROT;

        this.rmin = -2.0;
        this.rmax = 2.0;
        this.rinc = (this.rmax - this.rmin) / (double) this.dimr;

        this.imin = -1.0;
        this.imax = 1.0;
        this.iinc = (this.imax - this.imin) / (double) this.dimi;
    }

    public FractalFactory(int dimr, int dimi, double rmin, double rmax, double imin, double imax) {
        this.rmin = rmin;
        this.imin = imin;
        this.rmax = rmax;
        this.imax = imax;

        this.dimr = dimr;
        this.dimi = dimi;

        this.grid = new int[dimr][dimi];
        this.grid2 = new double[dimr][dimi];

        this.function = FractalFactory.MANDELBROT;

        this.rinc = (rmax - rmin) / (double) dimr;
        this.iinc = (imax - imin) / (double) dimi;
    }

    public void setLimits(double rmin, double rmax, double imin, double imax, int mi) {
        this.rmin = rmin;
        this.imin = imin;
        this.rmax = rmax;
        this.imax = imax;
        this.maxIteration = mi;

        this.rinc = (rmax - rmin) / (double) dimr;
        this.iinc = (imax - imin) / (double) dimi;
    }

    public void setFunction(int func) {
        this.function = func;
    }


    public double getRmin() {
        return this.rmin;
    }

    public double getRmax() {
        return this.rmax;
    }

    public double getImin() {
        return this.imin;
    }

    public double getImax() {
        return this.imax;
    }

    public int getMaxIteration() {
        return this.maxIteration;
    }

    public int getFunction() {
        return this.function;
    }


    public void setGrid(int dimr, int dimi) {
        this.dimr = dimr;
        this.dimi = dimi;

        this.grid = new int[dimr][dimi];
        this.grid2 = new double[dimr][dimi];
    }

    public int getDimr() {
        return this.dimr;
    }

    public int getDimi() {
        return this.dimi;
    }


    public int[][] getGrid() {
        this.generate();
        return this.grid;
    }

    public double[][] getGridVals() {
        this.generate();
        return this.grid2;
    }

    private void generate() {
        this.grid = new int[dimr][dimi];
        this.grid2 = new double[dimr][dimi];

        switch (this.function) {
            case FractalFactory.MANDELBROT:
                this.Mandelbrot();
                break;
            case FractalFactory.JULIA1:
                this.Julia1();
                break;
            case FractalFactory.JULIA2:
                this.Julia2();
                break;
            case FractalFactory.JULIA3:
                this.Julia3();
                break;
            case FractalFactory.JULIA4:
                this.Julia4();
                break;
            case FractalFactory.JULIA5:
                this.Julia5();
                break;
            default:
                this.Mandelbrot();
                break;
        }

    }

    private void Julia1() {
        this.JuliaFunction(new Complex(-0.618, 0.0));
    }

    private void Julia2() {
        this.JuliaFunction(new Complex(-0.4, 0.6));
    }

    private void Julia3() {
        this.JuliaFunction(new Complex(0.285, 0.0));
    }

    private void Julia4() {
        this.JuliaFunction(new Complex(0.285, 0.01));
    }

    private void Julia5() {
        this.JuliaFunction(new Complex(-0.8, 0.156));
    }

    private void JuliaFunction(Complex c) {
        System.out.println("Julia");

        for (int i = 0; i < this.dimr; i++) {
            for (int j = 0; j < this.dimi; j++) {

                Complex z = new Complex(this.rmin + this.rinc * i, this.imin + this.iinc * j);
                for (int k = 0; k < this.maxIteration; k++) {
                    z = z.power(2).plus(c);
                }
                if (z.abs() < 1e20) {
                    this.grid[i][j] = 1;
                    this.grid2[i][j] = z.abs();
                }
            }
        }
    }

    private void Mandelbrot() {
        System.out.println("Mandelbrot");

        for (int i = 0; i < this.dimr; i++) {
            for (int j = 0; j < this.dimi; j++) {
                Complex z = new Complex(0.0, 0.0);
                Complex c = new Complex(this.rmin + this.rinc * i, this.imin + this.iinc * j);
                for (int k = 0; k < this.maxIteration; k++) {
                    z = z.power(2).plus(c);
                }
                if (z.abs() < 1e20) {
                    this.grid[i][j] = 1;
                    this.grid2[i][j] = z.abs();
                }
            }
        }
    }
}
