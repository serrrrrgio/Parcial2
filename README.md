# Property Management System

A JavaFX desktop application for managing real estate properties, implementing multiple design patterns.

## Features

- Add properties (House, Apartment, Farm, Commercial)
- Real-time search and filtering
- Delete properties with confirmation
- Modern, responsive UI
- Statistics dashboard

## Design Patterns Implemented

### 1. **Singleton Pattern** (`PropertyRepository.java`)
Located in `model/` package. Manages the centralized ArrayList of properties.

```java
// Patrón Singleton
private static PropertyRepository instance;
public static PropertyRepository getInstance() { ... }
```

### 2. **Factory Pattern** (`PropertyFactory.java`)
Located in `model/` package. Creates property instances using a switch statement.

```java
// Patrón Factory
public static Property createProperty(String type, ...) {
    switch (type.toLowerCase()) {
        case "house": return new House.Builder()...
        // ...
    }
}
```

### 3. **Builder Pattern**
Implemented using inheritance from Property.Builder abstract class. Each concrete property class (House, Apartment, Farm, Commercial) extends the abstract Builder.

```java
// Patrón Builder - Abstract Builder in Property class
public abstract static class Builder<T extends Property, B extends Builder<T, B>> {
    protected B self() { return (B) this; }
    public B setCity(String city) { ... }
    public abstract T build();
}

// Patrón Builder - Hereda de Property.Builder
public static class Builder extends Property.Builder<House, Builder> {
    public Builder() { super("House"); }
    @Override
    public House build() { return new House(this); }
}
```

### 4. **Decorator Pattern**
Located in `decorator/` package. Adds features to properties (Garage, Pool, Garden).

```java
// Patrón Decorator
public class GarageDecorator extends PropertyDecorator { ... }
```

### 5. **Facade Pattern** (`PropertyFacade.java`)
Located in `facade/` package. Simplifies CRUD operations.

```java
// Patrón Facade
public void createProperty(String type, ...) { ... }
public void deleteProperty(Property property) { ... }
```

## Project Structure

```
src/
├── main/
│   ├── java/co/edu/uniquindio/poo/parcial2/
│   │   ├── HelloApplication.java          # Main entry point
│   │   ├── model/
│   │   │   ├── Property.java              # Abstract base class
│   │   │   ├── House.java                 # Concrete implementation
│   │   │   ├── Apartment.java             # Concrete implementation
│   │   │   ├── Farm.java                  # Concrete implementation
│   │   │   ├── Commercial.java            # Concrete implementation
│   │   │   ├── PropertyRepository.java    # Singleton pattern
│   │   │   └── PropertyFactory.java       # Factory pattern
│   │   ├── controller/
│   │   │   ├── DashboardController.java   # Main dashboard
│   │   │   ├── PropertyFormController.java
│   │   │   └── PropertyListController.java # With filtering
│   │   ├── facade/
│   │   │   └── PropertyFacade.java        # Facade pattern
│   │   └── decorator/
│   │       ├── PropertyDecorator.java     # Base decorator
│   │       ├── GarageDecorator.java
│   │       ├── PoolDecorator.java
│   │       └── GardenDecorator.java
│   └── resources/co/edu/uniquindio/poo/parcial2/
│       ├── view/
│       │   ├── DashboardView.fxml         # Main layout
│       │   ├── PropertyFormView.fxml      # Input form
│       │   └── PropertyListView.fxml      # Table with search
│       └── Style.css                      # All styling
└── module-info.java                       # Java module configuration
```

## Technology Stack

- **Java 21**
- **JavaFX 21** (controls, fxml)
- **Maven** for build management
- **JUnit 5** for testing

## Build and Run

### Using Maven Wrapper (Recommended)

**Compile:**
```bash
./mvnw clean compile      # Unix/Linux/Mac
mvnw.cmd clean compile    # Windows
```

**Run:**
```bash
./mvnw clean javafx:run   # Unix/Linux/Mac
mvnw.cmd clean javafx:run # Windows
```

### Using Maven

**Compile:**
```bash
mvn clean compile
```

**Run:**
```bash
mvn clean javafx:run
```

## Key Features

### Real-Time Filtering
Follows the reference project's `ManageUserController` pattern:
- Uses JavaFX `FilteredList` for in-memory filtering
- Case-insensitive search by property type or city
- Instant results as you type
- No database queries needed

### CRUD Operations
- **Create:** Form with validation, uses Factory pattern
- **Read:** TableView with sorted/filtered data
- **Delete:** Button per row with confirmation dialog
- **Facade:** All operations go through PropertyFacade

### UI/UX
- Modern, clean design with CSS styling
- Responsive layout that adapts to window size
- Hover effects and smooth transitions
- Statistics dashboard showing total properties

## Code Style

- All code in English
- Comments in Spanish ONLY for design patterns (e.g., `// Patrón Singleton`)
- JavaFX Properties for reactive UI binding
- No inline styles in FXML - all styling in Style.css

## Module System

The project uses Java Platform Module System (JPMS):

```java
module co.edu.uniquindio.poo.parcial2 {
    requires javafx.controls;
    requires javafx.fxml;

    exports co.edu.uniquindio.poo.parcial2;
    exports co.edu.uniquindio.poo.parcial2.model;
    exports co.edu.uniquindio.poo.parcial2.controller;

    opens co.edu.uniquindio.poo.parcial2.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial2.model to javafx.fxml;
}
```

## Architecture Highlights

1. **Separation of Concerns:** Clear separation between model, view, and controller
2. **Dependency Injection:** Controllers receive references to parent controllers
3. **Observable Collections:** JavaFX ObservableList for automatic UI updates
4. **Pattern Comments:** Every design pattern usage is marked with Spanish comments
5. **No Repository Folder:** Repository stays in model/ as per specifications

## License

Educational project for Universidad del Quindío - Programación Orientada a Objetos
