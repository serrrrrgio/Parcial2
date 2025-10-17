package co.edu.uniquindio.poo.parcial2.controller;

import co.edu.uniquindio.poo.parcial2.facade.PropertyFacade;
import co.edu.uniquindio.poo.parcial2.model.Property;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for property list view with filtering
 */
public class PropertyListController {

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Property> tableProperties;

    @FXML
    private TableColumn<Property, String> colType;

    @FXML
    private TableColumn<Property, String> colCity;

    @FXML
    private TableColumn<Property, Integer> colRooms;

    @FXML
    private TableColumn<Property, Integer> colFloors;

    @FXML
    private TableColumn<Property, Double> colPrice;

    private PropertyFacade propertyFacade;
    private DashboardController dashboardController;
    private FilteredList<Property> filteredData;

    @FXML
    public void initialize() {
        propertyFacade = PropertyFacade.getInstance();

        // Setup table columns
        setupTableColumns();

        // Load properties
        loadProperties();

        // Setup search filter (following ManageUserController pattern)
        setupSearchFilter();

        // Setup delete column
        setupDeleteColumn();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    /**
     * Setup table columns with property bindings
     */
    private void setupTableColumns() {
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colRooms.setCellValueFactory(new PropertyValueFactory<>("numberOfRooms"));
        colFloors.setCellValueFactory(new PropertyValueFactory<>("numberOfFloors"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Format price column
        colPrice.setCellFactory(column -> new TableCell<Property, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", price));
                }
            }
        });
    }

    /**
     * Load properties from repository
     */
    private void loadProperties() {
        // Get data from repository
        ObservableList<Property> propertyList = propertyFacade.getAllProperties();

        // Wrap in FilteredList (following ManageUserController pattern)
        filteredData = new FilteredList<>(propertyList, p -> true);

        // Wrap FilteredList in SortedList
        SortedList<Property> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProperties.comparatorProperty());

        // Add sorted data to table
        tableProperties.setItems(sortedData);
    }

    /**
     * Setup search filter (EXACT copy of ManageUserController style)
     */
    private void setupSearchFilter() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(property -> {
                // If filter is empty, display all properties
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convert filter to lowercase for case-insensitive comparison
                String lowerCaseFilter = newValue.toLowerCase();

                // Check if property type matches filter
                if (property.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                // Check if city matches filter
                else if (property.getCity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false; // Does not match
            });
        });
    }

    /**
     * Setup delete column with button
     */
    private void setupDeleteColumn() {
        TableColumn<Property, Void> deleteCol = new TableColumn<>("Actions");
        deleteCol.setPrefWidth(100.0);

        deleteCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.getStyleClass().add("btn-danger");
                deleteButton.setMaxWidth(Double.MAX_VALUE);
                deleteButton.setOnAction(event -> {
                    Property property = getTableView().getItems().get(getIndex());
                    handleDelete(property);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        tableProperties.getColumns().add(deleteCol);
    }

    /**
     * Handle delete property
     */
    private void handleDelete(Property property) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Property");
        alert.setHeaderText("Delete " + property.getType() + " in " + property.getCity());
        alert.setContentText("Are you sure you want to delete this property?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Patrón Facade
                propertyFacade.deleteProperty(property);
                tableProperties.refresh();
            }
        });
    }

    /**
     * Refresh table view
     */
    public void refreshTable() {
        tableProperties.refresh();
    }
}
