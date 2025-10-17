package co.edu.uniquindio.poo.parcial2.model;

/**
 * Commercial property type with Builder pattern inheriting from Property
 */
public class Commercial extends Property {

    private Commercial(Builder builder) {
        super(builder.type, builder.city, builder.numberOfRooms, builder.numberOfFloors, builder.price);
    }

    // Patrón Builder - Hereda de Property.Builder
    public static class Builder extends Property.Builder<Commercial, Builder> {

        public Builder() {
            super("Commercial");
        }

        @Override
        public Commercial build() {
            return new Commercial(this);
        }
    }
}
