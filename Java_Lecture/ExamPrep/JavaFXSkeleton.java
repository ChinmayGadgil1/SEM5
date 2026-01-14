import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXSkeleton extends Application {
    
    // @Override
    public void start(Stage primaryStage) {
        // 1. Create UI Controls (Nodes)
        Label label = new Label("Welcome to JavaFX!");
        Button button = new Button("Click Me");
        
        // 2. Create Layout (Container)
        VBox root = new VBox(10); // Vertical box with 10px spacing
        root.getChildren().addAll(label, button);
        
        // 3. Create Scene with Layout
        Scene scene = new Scene(root);
        
        // 4. Configure Stage (Window)
        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        
        // 5. Show the Stage
        primaryStage.show();
        
        // Event Handling
        button.setOnAction(e -> {
            label.setText("Button was clicked!");
        });
    }
    
    // Optional: init() and stop() methods
    // @Override
    public void init() {
        System.out.println("Initializing application...");
    }
    
    // @Override
    public void stop() {
        System.out.println("Application is closing...");
    }
    
    // Main method to launch application
    public static void main(String[] args) {
        launch(args);
    }
}