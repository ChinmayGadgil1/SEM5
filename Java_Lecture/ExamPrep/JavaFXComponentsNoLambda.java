import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JavaFXComponentsNoLambda extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create layout
        VBox root = new VBox(10);
        
        // 1. Button with anonymous class
        Button button = new Button("Click Me!");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button clicked!");
            }
        });
        
        // 2. Label
        Label label = new Label("This is a Label");
        
        // 3. TextField
        TextField textField = new TextField();
        textField.setPromptText("Enter text here...");
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TextField submitted: " + textField.getText());
            }
        });
        
        // 4. TextArea
        TextArea textArea = new TextArea();
        textArea.setPromptText("Enter multiline text...");
        textArea.setPrefRowCount(3);
        
        // 5. PasswordField
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        
        // 6. CheckBox
        CheckBox checkBox = new CheckBox("I agree to terms");
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkBox.isSelected()) {
                    System.out.println("CheckBox selected");
                } else {
                    System.out.println("CheckBox deselected");
                }
            }
        });
        
        // 7. RadioButton with ToggleGroup
        ToggleGroup group = new ToggleGroup();
        RadioButton radio1 = new RadioButton("Option 1");
        RadioButton radio2 = new RadioButton("Option 2");
        RadioButton radio3 = new RadioButton("Option 3");
        
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        radio1.setSelected(true);
        
        // Add listener to ToggleGroup
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, 
                              Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selected = (RadioButton) newValue;
                    System.out.println("Radio selected: " + selected.getText());
                }
            }
        });
        
        // 8. ToggleButton
        ToggleButton toggleButton = new ToggleButton("Toggle Me");
        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (toggleButton.isSelected()) {
                    System.out.println("ToggleButton ON");
                } else {
                    System.out.println("ToggleButton OFF");
                }
            }
        });
        
        // 9. ChoiceBox
        ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        ObservableList<String> fruits = FXCollections.observableArrayList(
            "Apple", "Banana", "Orange", "Mango"
        );
        choiceBox.setItems(fruits);
        choiceBox.setValue("Apple");
        
        // Add listener to ChoiceBox
        choiceBox.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, 
                                  String oldValue, String newValue) {
                    System.out.println("ChoiceBox selected: " + newValue);
                }
            }
        );
        
        // 10. ComboBox
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll("Red", "Green", "Blue", "Yellow");
        comboBox.setPromptText("Select a color");
        comboBox.setEditable(true);
        
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ComboBox selected: " + comboBox.getValue());
            }
        });
        
        // 11. ListView
        ListView<String> listView = new ListView<String>();
        listView.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
        listView.setPrefHeight(100);
        
        // Add selection listener to ListView
        listView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, 
                                  String oldValue, String newValue) {
                    System.out.println("ListView selected: " + newValue);
                }
            }
        );
        
        // Add all components to layout
        root.getChildren().addAll(
            button, label, textField, textArea, passwordField,
            checkBox, radio1, radio2, radio3, toggleButton,
            choiceBox, comboBox, listView
        );
        
        // Create scene and show stage
        Scene scene = new Scene(root, 400, 700);
        primaryStage.setTitle("JavaFX Components (No Lambda)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}