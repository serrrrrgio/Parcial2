package co.edu.uniquindio.poo.parcial2.decorator;

import co.edu.uniquindio.poo.parcial2.model.Property;

/**
 * Decorator that adds garage feature to a property
 */
// Patrón Decorator
public class GarageDecorator extends PropertyDecorator {
    private static final double GARAGE_PRICE = 15000.0;

    public GarageDecorator(Property property) {
        super(property);
    }

    @Override
    public String getType() {
        return decoratedProperty.getType() + " + Garage";
    }

    @Override
    public double getPrice() {
        return decoratedProperty.getPrice() + GARAGE_PRICE;
    }
}
