package co.edu.uniquindio.poo.parcial2.model;

/**
 * Apartment property type with Builder pattern inheriting from Property
 */
public class Apartment extends Property {

    private Apartment(Builder builder) {
        super(builder.type, builder.city, builder.numberOfRooms, builder.numberOfFloors, builder.price);
    }

    // Patrón Builder - Hereda de Property.Builder
    public static class Builder extends Property.Builder<Apartment, Builder> {

        public Builder() {
            super("Apartment");
        }

        @Override
        public Apartment build() {
            return new Apartment(this);
        }
    }
}
