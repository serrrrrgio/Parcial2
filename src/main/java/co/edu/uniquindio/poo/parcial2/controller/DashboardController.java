package co.edu.uniquindio.poo.parcial2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Controller for the main dashboard view with navigation
 */
public class DashboardController {

    @FXML
    private StackPane contentArea;

    private PropertyFormController propertyFormController;
    private PropertyListController propertyListController;

    @FXML
    public void initialize() {
        // Load the form view by default
        showFormView();
    }

    /**
     * Show the property form view
     */
    @FXML
    public void showFormView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial2/view/PropertyFormView.fxml"));
            Parent formView = loader.load();
            propertyFormController = loader.getController();
            propertyFormController.setDashboardController(this);

            contentArea.getChildren().clear();
            contentArea.getChildren().add(formView);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading PropertyFormView: " + e.getMessage());
        }
    }

    /**
     * Show the property list view
     */
    @FXML
    public void showListView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial2/view/PropertyListView.fxml"));
            Parent listView = loader.load();
            propertyListController = loader.getController();
            propertyListController.setDashboardController(this);

            contentArea.getChildren().clear();
            contentArea.getChildren().add(listView);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading PropertyListView: " + e.getMessage());
        }
    }

    /**
     * Refresh the property list (called after adding a property)
     */
    public void refreshPropertyList() {
        if (propertyListController != null) {
            propertyListController.refreshTable();
        }
    }

    /**
     * Switch to list view after adding a property
     */
    public void switchToListView() {
        showListView();
    }
}