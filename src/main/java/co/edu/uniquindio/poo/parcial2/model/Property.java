package co.edu.uniquindio.poo.parcial2.model;

import javafx.beans.property.*;

/**
 * Abstract base class for all property types
 */
public abstract class Property {
    private final StringProperty type;
    private final StringProperty city;
    private final IntegerProperty numberOfRooms;
    private final IntegerProperty numberOfFloors;
    private final DoubleProperty price;

    protected Property(String type, String city, int numberOfRooms, int numberOfFloors, double price) {
        this.type = new SimpleStringProperty(type);
        this.city = new SimpleStringProperty(city);
        this.numberOfRooms = new SimpleIntegerProperty(numberOfRooms);
        this.numberOfFloors = new SimpleIntegerProperty(numberOfFloors);
        this.price = new SimpleDoubleProperty(price);
    }

    // Property getters for JavaFX binding
    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public IntegerProperty numberOfRoomsProperty() {
        return numberOfRooms;
    }

    public IntegerProperty numberOfFloorsProperty() {
        return numberOfFloors;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    // Standard getters
    public String getType() {
        return type.get();
    }

    public String getCity() {
        return city.get();
    }

    public int getNumberOfRooms() {
        return numberOfRooms.get();
    }

    public int getNumberOfFloors() {
        return numberOfFloors.get();
    }

    public double getPrice() {
        return price.get();
    }

    // Standard setters
    public void setType(String type) {
        this.type.set(type);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms.set(numberOfRooms);
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors.set(numberOfFloors);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    @Override
    public String toString() {
        return String.format("%s in %s - Rooms: %d, Floors: %d, Price: $%.2f",
                getType(), getCity(), getNumberOfRooms(), getNumberOfFloors(), getPrice());
    }

    // Patrón Builder - Abstract Builder with inheritance
    public abstract static class Builder<T extends Property, B extends Builder<T, B>> {
        protected String type;
        protected String city;
        protected int numberOfRooms;
        protected int numberOfFloors;
        protected double price;

        protected Builder(String type) {
            this.type = type;
        }

        @SuppressWarnings("unchecked")
        protected B self() {
            return (B) this;
        }

        public B setCity(String city) {
            this.city = city;
            return self();
        }

        public B setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
            return self();
        }

        public B setNumberOfFloors(int numberOfFloors) {
            this.numberOfFloors = numberOfFloors;
            return self();
        }

        public B setPrice(double price) {
            this.price = price;
            return self();
        }

        public abstract T build();
    }
}
