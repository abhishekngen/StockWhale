import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_Display extends Application

{
    static Main_Controller gui; //Static reference to FXML Controller class

    public static void main(String[] args) {
        System.out.println("Launching");
        launch(args); //JavaFX call to trigger the start() method implemented below
    }

    @Override
    public void start(Stage primaryStage) throws Exception{ //Exceptions that occur are thrown as they are only associated with deprecated warnings
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Display.fxml")); //Producing FXML GUI File with hard-coded GUI elements and positions
        gui = new Main_Controller() ; //Creating an instance of the FXML Controller Class
        fxmlLoader.setController(gui); //Assigning the FXML Controller Class
        Parent root = fxmlLoader.load();


        Scene mainscene = new Scene(root, 700, 550); //Initiating and producing JavaFX GUI Scene to be displayed in GUI window

        primaryStage.setTitle("Chess Engine"); //Setting title of GUI window
        primaryStage.setScene(mainscene); //Assigning scene to GUI window
        primaryStage.setResizable(false);
        primaryStage.show(); //Displaying GUI window

    }
}
