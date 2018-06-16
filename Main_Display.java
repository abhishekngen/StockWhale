import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;

public class Main_Display extends Application

{
    static Main_Controller gui;
    public static void main(String[] args) {
        System.out.println("Launching");
        launch(args);
    }





    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Display.fxml"));
        gui = new Main_Controller() ;
        fxmlLoader.setController(gui);
        Parent root = fxmlLoader.load();


        Scene mainscene = new Scene(root, 700, 550);

        primaryStage.setTitle("Chess Engine");
        primaryStage.setScene(mainscene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }



}
