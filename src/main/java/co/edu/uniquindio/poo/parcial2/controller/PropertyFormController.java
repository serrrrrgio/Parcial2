package co.edu.uniquindio.poo.parcial2.controller;

import co.edu.uniquindio.poo.parcial2.decorator.GarageDecorator;
import co.edu.uniquindio.poo.parcial2.decorator.GardenDecorator;
import co.edu.uniquindio.poo.parcial2.decorator.PoolDecorator;
import co.edu.uniquindio.poo.parcial2.facade.PropertyFacade;
import co.edu.uniquindio.poo.parcial2.model.Property;
import co.edu.uniquindio.poo.parcial2.model.PropertyFactory;
import co.edu.uniquindio.poo.parcial2.model.PropertyRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.util.Locale;

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
    private CheckBox chkGarage;

    @FXML
    private CheckBox chkPool;

    @FXML
    private CheckBox chkGarden;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private Button btnAdd;

    private PropertyFacade propertyFacade;
    private DashboardController dashboardController;
    private NumberFormat currencyFormatter;

    @FXML
    public void initialize() {
        propertyFacade = PropertyFacade.getInstance();
        currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        // Initialize ComboBox with property types
        cmbPropertyType.getItems().addAll("House", "Apartment", "Farm", "Commercial");

        // Initialize spinners with proper value factories
        SpinnerValueFactory<Integer> roomsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 3);
        spinRooms.setValueFactory(roomsValueFactory);

        SpinnerValueFactory<Integer> floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        spinFloors.setValueFactory(floorsValueFactory);

        // Add listeners to update total price in real-time
        txtPrice.textProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
        chkGarage.selectedProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
        chkPool.selectedProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
        chkGarden.selectedProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    /**
     * Update total price display based on base price and decorators
     */
    private void updateTotalPrice() {
        try {
            if (txtPrice.getText() == null || txtPrice.getText().trim().isEmpty()) {
                lblTotalPrice.setText("$0.00");
                return;
            }

            double basePrice = Double.parseDouble(txtPrice.getText().trim());
            double totalPrice = basePrice;

            // Add decorator prices
            if (chkGarage.isSelected()) {
                totalPrice += 15000.0;
            }
            if (chkPool.isSelected()) {
                totalPrice += 25000.0;
            }
            if (chkGarden.isSelected()) {
                totalPrice += 10000.0;
            }

            lblTotalPrice.setText(currencyFormatter.format(totalPrice));
        } catch (NumberFormatException e) {
            lblTotalPrice.setText("$0.00");
        }
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

            // Create base property using Factory pattern
            Property property = PropertyFactory.createProperty(type, city, numberOfRooms, numberOfFloors, price);

            // Apply decorators based on checkbox selections - Patrón Decorator
            if (chkGarage.isSelected()) {
                property = new GarageDecorator(property);
            }
            if (chkPool.isSelected()) {
                property = new PoolDecorator(property);
            }
            if (chkGarden.isSelected()) {
                property = new GardenDecorator(property);
            }

            // Add property to repository using Singleton pattern
            PropertyRepository.getInstance().addProperty(property);

            // Show success message
            showAlert("Success", "Property added successfully!\nFinal Price: " + currencyFormatter.format(property.getPrice()), Alert.AlertType.INFORMATION);

            // Clear form
            clearForm();

            // Switch to list view to see the new property
            if (dashboardController != null) {
                dashboardController.switchToListView();
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
        chkGarage.setSelected(false);
        chkPool.setSelected(false);
        chkGarden.setSelected(false);
        lblTotalPrice.setText("$0.00");
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