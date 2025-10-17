package co.edu.uniquindio.poo.parcial2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Repository for managing properties using Singleton pattern
 */
public class PropertyRepository {
    // Patrón Singleton
    private static PropertyRepository instance;
    private final ArrayList<Property> properties;
    private final ObservableList<Property> observableProperties;

    private PropertyRepository() {
        properties = new ArrayList<>();
        observableProperties = FXCollections.observableArrayList(properties);
    }

    public static PropertyRepository getInstance() {
        if (instance == null) {
            instance = new PropertyRepository();
        }
        return instance;
    }

    public void addProperty(Property property) {
        properties.add(property);
        observableProperties.add(property);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
        observableProperties.remove(property);
    }

    public ObservableList<Property> getObservableProperties() {
        return observableProperties;
    }

    public ArrayList<Property> getProperties() {
        return new ArrayList<>(properties);
    }

    public int getPropertyCount() {
        return properties.size();
    }

    public void clearAll() {
        properties.clear();
        observableProperties.clear();
    }
}
