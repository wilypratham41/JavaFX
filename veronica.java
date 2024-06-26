

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.canvas.Canvas;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class veronica extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Canvas c2w_canvas;

    private volatile boolean c2w_running;

    private c2w_Runner c2w_runner;
    private Button c2w_startButton;

    // a button that is used to start and stop the animation

    public void start(Stage stage) {
        c2w_canvas = new Canvas(640, 480);
        c2w_redraw();
        c2w_startButton = new Button("Start!");
        c2w_startButton.setOnAction(e -> c2w_doStartOrStop());
        HBox c2w_bottom = new HBox(c2w_startButton);
        c2w_bottom.setStyle("-fx-padding:6px; -fx-border-color:black; -fx-border-width:3px 0 0 0");
        c2w_bottom.setAlignment(Pos.CENTER);
        BorderPane c2wRoot = new BorderPane(c2w_canvas);
        c2wRoot.setBottom(c2w_bottom);
        Scene c2w_scene = new Scene(c2wRoot);
        stage.setScene(c2w_scene);
        stage.setTitle("CLICK START TO MAKE RANDOM ART!");
        stage.setResizable(false);
        stage.show();
    }

    private class c2w_Runner extends Thread {
        public void run() {
            while (c2w_running) {
                Platform.runLater(() -> c2w_redraw());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void c2w_redraw() {
        GraphicsContext c2w_g = c2w_canvas.getGraphicsContext2D();
        double c2w_width = c2w_canvas.getWidth();
        double c2w_height = c2w_canvas.getHeight();

        if (!c2w_running) {
            c2w_g.setFill(Color.WHITE);
            c2w_g.fillRect(0, 0, c2w_width, c2w_height);
            return;
        }
        Color c2w_randomGray = Color.hsb(1, 0, Math.random());
        c2w_g.setFill(c2w_randomGray);
        c2w_g.fillRect(0, 0, c2w_width, c2w_height);
        int c2w_artType = (int) (3 * Math.random());

        switch (c2w_artType) {
            case 0:
                c2w_g.setLineWidth(2);
                for (int i = 0; i < 500; i++) {
                    int x1 = (int) (c2w_width * Math.random());
                    int y1 = (int) (c2w_height * Math.random());
                    int x2 = (int) (c2w_width * Math.random());
                    int y2 = (int) (c2w_height * Math.random());
                    Color randomHue = Color.hsb(360 * Math.random(), 1, 1);
                    c2w_g.setStroke(randomHue);
                    c2w_g.strokeLine(x1, y1, x2, y2);
                }
                break;
            case 1:
                for (int i = 0; i < 200; i++) {
                    int c2w_centerX = (int) (c2w_width * Math.random());
                    int c2w_centerY = (int) (c2w_height * Math.random());
                    Color c2w_randomHue = Color.hsb(360 * Math.random(), 1, 1);
                    c2w_g.setStroke(c2w_randomHue);
                    c2w_g.strokeLine(c2w_centerX - 50, c2w_centerY - 50, 100, 100);
                }
                break;
            default:
                c2w_g.setStroke(Color.BLACK);
                c2w_g.setLineWidth(4);
                for (int i = 0; i < 25; i++) {
                    int centerX = (int) (c2w_width * Math.random());
                    int centerY = (int) (c2w_height * Math.random());
                    int size = 30 + (int) (170 * Math.random());
                    Color randomColor = Color.hsb(360 * Math.random(), Math.random(), Math.random());
                    c2w_g.setFill(randomColor);
                    c2w_g.fillRect(centerX - size / 2, centerY - size / 2, size, size);
                    c2w_g.strokeRect(centerX - size / 2, centerY - size / 2, c2w_artType, c2w_height);
                }
                break;

        }
    }

    private void c2w_doStartOrStop() {
        if (c2w_running == false) {
            c2w_startButton.setText("Stop!");
            c2w_runner = new c2w_Runner();
            c2w_running = true;
            c2w_runner.start();
        } else {
            c2w_startButton.setDisable(true);
            c2w_running = false;
            c2w_redraw();
            c2w_runner.interrupt();

            try {
                c2w_runner.join(1000);
            } catch (InterruptedException e) {

            }
            c2w_runner = null;
            c2w_startButton.setText("Start!");
            c2w_startButton.setDisable(false);

        }
    }

}