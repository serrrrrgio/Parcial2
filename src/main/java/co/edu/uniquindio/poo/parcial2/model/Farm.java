package co.edu.uniquindio.poo.parcial2.model;

/**
 * Farm property type with Builder pattern inheriting from Property
 */
public class Farm extends Property {

    private Farm(Builder builder) {
        super(builder.type, builder.city, builder.numberOfRooms, builder.numberOfFloors, builder.price);
    }

    // Patrón Builder - Hereda de Property.Builder
    public static class Builder extends Property.Builder<Farm, Builder> {

        public Builder() {
            super("Farm");
        }

        @Override
        public Farm build() {
            return new Farm(this);
        }
    }
}
