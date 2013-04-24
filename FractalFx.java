import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 4/25/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class FractalFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        int dimr = 1200;
        int dimi = 700;
        int[][] grid = new int[dimr][dimi];
        int maxIteration = 100;

        double rmin = -2.0;
        double rmax = 2.0;
        double rinc = (rmax-rmin)/(double)dimr;

        double imin = -1.0;
        double imax = 1.0;
        double iinc = (imax-imin)/(double)dimi;

        for (int i = 0; i < dimr; i++) {
            for (int j = 0; j < dimi; j++) {
                //Complex z = new Complex(0.0, 0.0);
                //Complex c = new Complex(rmin+rinc*i, imin+iinc*j);
                Complex c = new Complex(-0.4, 0.6);
                Complex z = new Complex(rmin+rinc*i, imin+iinc*j);
                for(int k = 0; k < maxIteration; k++) {
                    z = z.power(2).plus(c);

                }
                if(z.abs() < 1e20){
                    grid[i][j] = 1;
                }
            }
        }

        primaryStage.setTitle("Fractal");
        Group root = new Group();
        Canvas canvas = new Canvas(dimr,dimi);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        for(int i = 0; i < dimr; i++){
            for(int j = 0; j < dimi; j++) {
                if(grid[i][j]==1){
                    gc.strokeLine(i, j, i, j);
                }
            }
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
