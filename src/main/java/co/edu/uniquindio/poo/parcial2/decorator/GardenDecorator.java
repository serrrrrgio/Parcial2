package co.edu.uniquindio.poo.parcial2.decorator;

import co.edu.uniquindio.poo.parcial2.model.Property;

/**
 * Decorator that adds garden feature to a property
 */
// Patrón Decorator
public class GardenDecorator extends PropertyDecorator {
    private static final double GARDEN_PRICE = 10000.0;

    public GardenDecorator(Property property) {
        super(property);
    }

    @Override
    public String getType() {
        return decoratedProperty.getType() + " + Garden";
    }

    @Override
    public double getPrice() {
        return decoratedProperty.getPrice() + GARDEN_PRICE;
    }
}
