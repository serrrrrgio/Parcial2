package co.edu.uniquindio.poo.parcial2.facade;

import co.edu.uniquindio.poo.parcial2.model.Property;
import co.edu.uniquindio.poo.parcial2.model.PropertyFactory;
import co.edu.uniquindio.poo.parcial2.model.PropertyRepository;
import javafx.collections.ObservableList;

/**
 * Facade to simplify property CRUD operations
 */
// Patrón Facade
public class PropertyFacade {
    private static PropertyFacade instance;
    private final PropertyRepository repository;

    private PropertyFacade() {
        repository = PropertyRepository.getInstance();
    }

    public static PropertyFacade getInstance() {
        if (instance == null) {
            instance = new PropertyFacade();
        }
        return instance;
    }

    /**
     * Create and add a new property
     */
    public void createProperty(String type, String city, int numberOfRooms, int numberOfFloors, double price) {
        Property property = PropertyFactory.createProperty(type, city, numberOfRooms, numberOfFloors, price);
        repository.addProperty(property);
    }

    /**
     * Delete a property
     */
    public void deleteProperty(Property property) {
        repository.removeProperty(property);
    }

    /**
     * Get all properties
     */
    public ObservableList<Property> getAllProperties() {
        return repository.getObservableProperties();
    }

    /**
     * Get property count
     */
    public int getPropertyCount() {
        return repository.getPropertyCount();
    }

    /**
     * Clear all properties
     */
    public void clearAllProperties() {
        repository.clearAll();
    }
}
