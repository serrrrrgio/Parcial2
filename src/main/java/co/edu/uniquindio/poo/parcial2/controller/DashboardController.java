package co.edu.uniquindio.poo.parcial2.controller;

import co.edu.uniquindio.poo.parcial2.facade.PropertyFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the main dashboard view
 */
public class DashboardController {

    @FXML
    private Label lblTotalProperties;

    @FXML
    private PropertyFormController propertyFormController;

    @FXML
    private PropertyListController propertyListController;

    private PropertyFacade propertyFacade;

    @FXML
    public void initialize() {
        propertyFacade = PropertyFacade.getInstance();
        updateStatistics();

        // Set up communication between form and list controllers
        if (propertyFormController != null) {
            propertyFormController.setDashboardController(this);
        }
        if (propertyListController != null) {
            propertyListController.setDashboardController(this);
        }
    }

    /**
     * Update statistics displayed on dashboard
     */
    public void updateStatistics() {
        int total = propertyFacade.getPropertyCount();
        lblTotalProperties.setText(String.valueOf(total));
    }

    /**
     * Refresh the property list
     */
    public void refreshPropertyList() {
        if (propertyListController != null) {
            propertyListController.refreshTable();
        }
        updateStatistics();
    }
}
