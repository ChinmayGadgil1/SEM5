import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Three extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create Nodes
        Label label = new Label("Hello JavaFX!");
        Button button = new Button("Click Me");
        
        // Set Node properties
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: blue;");
        button.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        
        // Add event handler to Node
        button.setOnAction(e -> label.setText("Button Clicked!"));
        
        // Create layout (also a Node)
        VBox root = new VBox(10); // 10 pixel spacing
        root.getChildren().addAll(label, button);
        
        // Create Scene with root Node
        Scene scene = new Scene(root, 400, 300);
        
        // Configure Stage
        primaryStage.setTitle("JavaFX Stage-Scene-Node Demo");
        primaryStage.setScene(scene); // Set Scene on Stage
        
        // Show Stage
        primaryStage.show();
        
        // Create another Stage (window)
        Stage secondStage = new Stage();
        secondStage.setTitle("Second Window");
        secondStage.setScene(new Scene(new Label("Another Stage"), 200, 150));
        secondStage.setX(500); // Position on screen
        secondStage.setY(200);
        secondStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}