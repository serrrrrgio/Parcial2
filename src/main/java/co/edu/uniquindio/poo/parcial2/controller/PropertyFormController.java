package co.edu.uniquindio.poo.parcial2.controller;

import co.edu.uniquindio.poo.parcial2.facade.PropertyFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller for property form view
 */
public class PropertyFormController {

    @FXML
    private ComboBox<String> cmbPropertyType;

    @FXML
    private TextField txtCity;

    @FXML
    private Spinner<Integer> spinRooms;

    @FXML
    private Spinner<Integer> spinFloors;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnAdd;

    private PropertyFacade propertyFacade;
    private DashboardController dashboardController;

    @FXML
    public void initialize() {
        propertyFacade = PropertyFacade.getInstance();

        // Initialize ComboBox with property types
        cmbPropertyType.getItems().addAll("House", "Apartment", "Farm", "Commercial");

        // Initialize spinners with proper value factories
        SpinnerValueFactory<Integer> roomsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 3);
        spinRooms.setValueFactory(roomsValueFactory);

        SpinnerValueFactory<Integer> floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        spinFloors.setValueFactory(floorsValueFactory);
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void handleAddProperty() {
        // Validate inputs
        if (!validateInputs()) {
            showAlert("Validation Error", "Please fill all fields correctly.", Alert.AlertType.WARNING);
            return;
        }

        try {
            // Get values
            String type = cmbPropertyType.getValue();
            String city = txtCity.getText().trim();
            int numberOfRooms = spinRooms.getValue();
            int numberOfFloors = spinFloors.getValue();
            double price = Double.parseDouble(txtPrice.getText().trim());

            // Patrón Facade
            propertyFacade.createProperty(type, city, numberOfRooms, numberOfFloors, price);

            // Show success message
            showAlert("Success", "Property added successfully!", Alert.AlertType.INFORMATION);

            // Clear form
            clearForm();

            // Refresh dashboard
            if (dashboardController != null) {
                dashboardController.refreshPropertyList();
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid price.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to add property: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Validate form inputs
     */
    private boolean validateInputs() {
        if (cmbPropertyType.getValue() == null || cmbPropertyType.getValue().isEmpty()) {
            return false;
        }
        if (txtCity.getText() == null || txtCity.getText().trim().isEmpty()) {
            return false;
        }
        if (txtPrice.getText() == null || txtPrice.getText().trim().isEmpty()) {
            return false;
        }
        try {
            double price = Double.parseDouble(txtPrice.getText().trim());
            if (price <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Clear form fields
     */
    private void clearForm() {
        cmbPropertyType.setValue(null);
        txtCity.clear();
        spinRooms.getValueFactory().setValue(3);
        spinFloors.getValueFactory().setValue(1);
        txtPrice.clear();
    }

    /**
     * Show alert dialog
     */
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
