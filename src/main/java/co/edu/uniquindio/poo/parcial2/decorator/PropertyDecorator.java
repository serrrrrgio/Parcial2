package co.edu.uniquindio.poo.parcial2.decorator;

import co.edu.uniquindio.poo.parcial2.model.Property;

/**
 * Base decorator class for adding features to properties
 */
// Patrón Decorator
public abstract class PropertyDecorator extends Property {
    protected Property decoratedProperty;

    public PropertyDecorator(Property property) {
        super(property.getType(), property.getCity(), property.getNumberOfRooms(),
              property.getNumberOfFloors(), property.getPrice());
        this.decoratedProperty = property;
    }

    @Override
    public abstract String getType();

    @Override
    public abstract double getPrice();
}
