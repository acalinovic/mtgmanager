/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.utils;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author boris
 */
public class StageHelper {
    public static HashMap<String, Stage> STAGES;
    public static ResourcesHelper RESOURCES_MANAGER;

    public StageHelper() {
        STAGES = new HashMap<String, Stage>();
        RESOURCES_MANAGER = new ResourcesHelper();
    }
    
    public void registerStage(Stage stage) {
        STAGES.put(stage.getTitle(), stage);
    }
    
    public Stage getStage(Object title) {
        return (Stage) STAGES.get(title);
    }
    public void closeStage(Stage stage) {
        stage.close();
        STAGES.remove(stage.getTitle());
    }
    public void buildStage(String title, boolean show) {
        Stage stage = new Stage();
        stage.setTitle(title);
        registerStage(stage);
        if (show) {
            stage.show();
        }
    }
    
    public void setView(Stage stage, Parent view) {
        Stage chosenStage = this.getStage(stage.getTitle());
        chosenStage.setScene(new Scene(view));
    }
    public void renderView(String stage, String view) throws IOException {
        Parent targetView = (Parent) FXMLLoader.load(RESOURCES_MANAGER.getFxmlView(view));
        Stage targetStage = getStage(stage);
        setView(targetStage, targetView);
        targetStage.setTitle(view);
    }
    public void renderDefaultView(Stage defaultStage) throws IOException {
        defaultStage.setTitle("Default");
        registerStage(defaultStage);
        Parent root = FXMLLoader.load(RESOURCES_MANAGER.getFxmlView("Default"));
        Stage targetStage = getStage("Default");
        setView(targetStage, root);
        targetStage.show();
    }
}
