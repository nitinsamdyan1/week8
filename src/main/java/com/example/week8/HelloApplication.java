package com.example.week8;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS", 25));
        txtNote.setFill(Color.GREEN);

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Add Field");

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
        Button btnRemove = new Button("Remove");
        btnRemove.setMinWidth(85);

        // ListView and Observable List
        ListView<String> listList = new ListView<>();
        ObservableList<String> names = FXCollections.observableArrayList(
                "Adrak", "Lehsun", "Mirch", "Tamatar");
        listList.setItems(names);
        listList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listList.setMaxSize(300, 300);

        // Button Add
        btnAdd.setOnAction(e -> {
            String newName = fldAdd.getText().trim();
            if (isValidName(newName)) {
                names.add(newName);
                txtNote.setText("Name added successfully");
                fldAdd.clear();
            } else {
                txtNote.setText("Invalid name: Please check the rules");
            }
        });

        // Button Remove
        btnRemove.setOnAction(e -> {
            String selectedName = listList.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                names.remove(selectedName);
                txtNote.setText("Name removed successfully");
            } else {
                txtNote.setText("Select an item to remove");
            }
        });

        // Pane Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnRemove);

        // Root Node
        BorderPane root = new BorderPane();
        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("JavaFX List Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Validation method for name
    private boolean isValidName(String name) {
        return !name.isEmpty() && name.length() >= 5 && Character.isUpperCase(name.charAt(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
