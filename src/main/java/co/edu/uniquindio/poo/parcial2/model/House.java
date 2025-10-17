package co.edu.uniquindio.poo.parcial2.model;

/**
 * House property type with Builder pattern inheriting from Property
 */
public class House extends Property {

    private House(Builder builder) {
        super(builder.type, builder.city, builder.numberOfRooms, builder.numberOfFloors, builder.price);
    }

    // Patrón Builder - Hereda de Property.Builder
    public static class Builder extends Property.Builder<House, Builder> {

        public Builder() {
            super("House");
        }

        @Override
        public House build() {
            return new House(this);
        }
    }
}
