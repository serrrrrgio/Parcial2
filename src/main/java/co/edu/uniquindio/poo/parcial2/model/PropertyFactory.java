package co.edu.uniquindio.poo.parcial2.model;

/**
 * Factory for creating different property types
 */
public class PropertyFactory {

    // Patrón Factory
    public static Property createProperty(String type, String city, int numberOfRooms, int numberOfFloors, double price) {
        switch (type.toLowerCase()) {
            case "house":
                return new House.Builder()
                        .setCity(city)
                        .setNumberOfRooms(numberOfRooms)
                        .setNumberOfFloors(numberOfFloors)
                        .setPrice(price)
                        .build();
            case "apartment":
                return new Apartment.Builder()
                        .setCity(city)
                        .setNumberOfRooms(numberOfRooms)
                        .setNumberOfFloors(numberOfFloors)
                        .setPrice(price)
                        .build();
            case "farm":
                return new Farm.Builder()
                        .setCity(city)
                        .setNumberOfRooms(numberOfRooms)
                        .setNumberOfFloors(numberOfFloors)
                        .setPrice(price)
                        .build();
            case "commercial":
                return new Commercial.Builder()
                        .setCity(city)
                        .setNumberOfRooms(numberOfRooms)
                        .setNumberOfFloors(numberOfFloors)
                        .setPrice(price)
                        .build();
            default:
                throw new IllegalArgumentException("Unknown property type: " + type);
        }
    }
}
