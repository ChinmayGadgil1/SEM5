import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXEffects extends Application {
    
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        
        // 1. DropShadow effect
        Text text1 = new Text("Drop Shadow Effect");
        text1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        text1.setEffect(dropShadow);
        
        // 2. Glow effect
        Text text2 = new Text("Glow Effect");
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        text2.setFill(Color.BLUE);
        Glow glow = new Glow();
        glow.setLevel(0.8);
        text2.setEffect(glow);
        
        // 3. Reflection effect
        Text text3 = new Text("Reflection Effect");
        text3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        text3.setEffect(reflection);
        
        // 4. Bloom effect
        Text text4 = new Text("Bloom Effect");
        text4.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        text4.setFill(Color.RED);
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.3);
        text4.setEffect(bloom);
        
        root.getChildren().addAll(text1, text2, text3, text4);
        
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("JavaFX Effects Demo");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}