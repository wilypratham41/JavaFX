
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Jarvis extends Application {
@Override
public void start(Stage primaryStage) {
Button jrv_btn = new Button();
jrv_btn.setText("Say 'Hello Jarvis'");
jrv_btn.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent event) {
System.out.println("Hello Jarvis!");
}
});
StackPane jrv_root = new StackPane();

jrv_root.getChildren().add(jrv_btn);
Scene jrv_scene = new Scene(jrv_root, 300, 250);
primaryStage.setTitle("Hello Jarvis!");
primaryStage.setScene(jrv_scene);
primaryStage.show();
}
public static void main(String[] args) {
launch(args);
}
}
