# Smart Office System

This project is a **Java-based Smart Office Application** that simulates room management with systems such as lighting, air conditioning, and occupancy handling. It demonstrates the use of **Command Pattern** and **Observer Pattern** in object-oriented programming.

## 📂 Project Structure

```
Exercise2/
 └── office/
      ├── ACSystem.class
      ├── BookRoomCommand.class
      ├── CancelBookingCommand.class
      ├── CancelRoomCommand.class
      ├── Command.class
      ├── LightSystem.class
      ├── OccupancyCommand.class
      ├── OfficeManager.class
      ├── Room.class
      ├── RoomObserver.class
      ├── SmartOfficeApp.java
      └── SmartOfficeApp.class
```

## 🚀 How to Compile and Run

1. Navigate to the `Exercise2/office/` directory.
2. Compile the source code:
   ```bash
   javac SmartOfficeApp.java
   ```
3. Run the application:
   ```bash
   java SmartOfficeApp
   ```

## 🛠 Features

- Book and cancel room reservations.  
- Control **light** and **AC** systems in the office.  
- Track room occupancy.  
- Demonstrates **Command Pattern** (encapsulates requests as objects, provides undo/redo flexibility).  
- Demonstrates **Observer Pattern** (rooms notify observers of state changes).  

## 📖 Design Patterns Used

- **Command Pattern** → Encapsulates operations like booking, canceling, and occupancy handling as command objects.  
- **Observer Pattern** → Keeps room states and observers in sync.  

## ✅ Requirements

- Java 8 or higher  
- Command line or IDE (Eclipse/IntelliJ/NetBeans)  
