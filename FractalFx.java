import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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

    private ImageView imageView;

    public void setImageView(ImageView iw) {
        this.imageView = iw;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final FractalFactory ff = new FractalFactory();

        primaryStage.setTitle("Fractal");

        final ImageView iw = new ImageView();
        iw.setSmooth(true);

        this.setImageView(iw);

        BorderPane root = new BorderPane();
        final StackPane imagePane = new StackPane();

        imagePane.getChildren().add(iw);

        final ContextMenu cMenu = new ContextMenu();
        this.generateContextMenu(cMenu, ff, this);

        root.setCenter(imagePane);

        imagePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    cMenu.show(imagePane, mouseEvent.getSceneX(), mouseEvent.getSceneY());
                } else {
                    enlargeImage((int) mouseEvent.getX(), (int) mouseEvent.getY(), iw, ff);
                }
            }
        });

        Scene scene = new Scene(root, ff.getDimr(), ff.getDimi());
        primaryStage.setScene(scene);
        primaryStage.show();

        updateImage(ff);

    }

    public void enlargeImage(int x, int y, ImageView iw, FractalFactory ff) {

        double rSpan = (ff.getRmax() - ff.getRmin()) / 2.0;
        double iSpan = (ff.getImax() - ff.getImin()) / 2.0;

        double rCenter = ((double) x / (double) ff.getDimr()) * rSpan * 2.0 + ff.getRmin();
        double iCenter = ((double) y / (double) ff.getDimi()) * iSpan * 2.0 + ff.getImin();

        ff.setLimits(
                rCenter - rSpan / 2,
                rCenter + rSpan / 2,
                iCenter - iSpan / 2,
                iCenter + iSpan / 2,
                ff.getMaxIteration() + 2
        );

        updateImage(ff);
    }

    public void updateImage(FractalFactory ff) {

        int[][] grid = ff.getGrid();
        WritableImage image = new WritableImage(ff.getDimr(), ff.getDimi());

        PixelWriter pw = image.getPixelWriter();

        for (int i = 0; i < ff.getDimr(); i++) {
            for (int j = 0; j < ff.getDimi(); j++) {

                if (grid[i][j] == 1) {
                    pw.setColor(i, j, Color.BLACK);
                }
            }
        }

        this.imageView.setImage(image);
        //pb.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
    }

    public void generateContextMenu(ContextMenu cm, final FractalFactory ff, final FractalFx u) {
        MenuItem itemMandelbrot = new MenuItem("Mandelbrot");
        itemMandelbrot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ff.setFunction(FractalFactory.MANDELBROT);
                u.updateImage(ff);
            }
        });

        MenuItem itemJulia1 = new MenuItem("Julia c=1−φ");
        itemJulia1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ff.setFunction(FractalFactory.JULIA1);
                u.updateImage(ff);
            }
        });

        MenuItem itemJulia2 = new MenuItem("Julia c=-0.4+0.6i");
        itemJulia2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ff.setFunction(FractalFactory.JULIA2);
                u.updateImage(ff);
            }
        });

        MenuItem itemJulia3 = new MenuItem("Julia c=0.285+0i");
        itemJulia3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ff.setFunction(FractalFactory.JULIA3);
                u.updateImage(ff);
            }
        });

        MenuItem itemJulia4 = new MenuItem("Julia c=0.285+0.01i");
        itemJulia4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ff.setFunction(FractalFactory.JULIA4);
                u.updateImage(ff);
            }
        });

        MenuItem itemJulia5 = new MenuItem("Julia c=-0.8+0.156i");
        itemJulia5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ff.setFunction(FractalFactory.JULIA5);
                u.updateImage(ff);
            }
        });

        cm.getItems().addAll(itemMandelbrot, itemJulia1, itemJulia2, itemJulia3, itemJulia4, itemJulia5);
    }
}
