package co.edu.uniquindio.poo.parcial2.decorator;

import co.edu.uniquindio.poo.parcial2.model.Property;

/**
 * Decorator that adds pool feature to a property
 */
// Patrón Decorator
public class PoolDecorator extends PropertyDecorator {
    private static final double POOL_PRICE = 25000.0;

    public PoolDecorator(Property property) {
        super(property);
    }

    @Override
    public String getType() {
        return decoratedProperty.getType() + " + Pool";
    }

    @Override
    public double getPrice() {
        return decoratedProperty.getPrice() + POOL_PRICE;
    }
}
