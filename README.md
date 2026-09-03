# Sal Whisky – Tracking & Inventory System

A desktop application developed as part of a 2nd semester project on the Computer Science programme at Erhvervsakademi Aarhus.

The system was developed for Sal Whisky, a small Danish whisky producer, with the purpose of supporting the management and tracking of whisky production and warehouse inventory.

## About the Project

The application provides a graphical user interface for managing different parts of the whisky production process.

The system allows employees to create and manage raw materials, distillations, distillates, barrels and finished products, while also keeping track of where barrels are located in the warehouse.

The project was developed as a group project based on a real-world business case.

## Main Features

- Manage raw materials
- Manage suppliers and origins
- Manage distillations
- Manage distillates
- Manage whisky barrels
- Manage finished products
- Track barrel locations
- Manage warehouses, racks, rows and shelves
- Input validation
- Persistent data storage
- Unit testing

## Production Flow

The system models the different stages of the whisky production process:

```text
Raw Material
     ↓
Distillation
     ↓
Distillate
     ↓
Barrel
     ↓
Finished Product

This makes it possible to follow the different entities throughout the production process.

Warehouse Management

The warehouse is represented using a hierarchical structure:

Warehouse
    ↓
Rack
    ↓
Row
    ↓
Shelf
    ↓
Barrel

This allows individual barrels to be associated with a specific location in the warehouse.

Technologies
Java
JavaFX
JUnit
Maven
Git
Object Serialization
Project Structure
src/
├── Controller/
├── Storage/
├── app/
├── gui/
└── model/

test/
├── Controller/
└── model/
Model

Contains the main domain classes used to represent the whisky production and warehouse system.

Examples include:

Råvare
Destillering
Destillat
Fad
FærdigVare
Lager
Reol
Række
Hylde
Leverandør
Medarbejder
GUI

The graphical user interface is built using JavaFX and provides different views for managing the system.

Controller

The controller handles operations within the application and connects the GUI with the underlying model and storage.

Storage

The application uses object serialization for persistent storage of application data.

Testing

The project includes JUnit tests covering important parts of the application.

The tests include validation of:

Object creation
Invalid input
Relationships between objects
Barrel and shelf assignments
Exception handling
Running the Project
Requirements
Java
Maven
IntelliJ IDEA or another Java IDE
Run
Clone the repository.
Open the project in IntelliJ IDEA.
Configure the required Java version.
Build the project using Maven.
Run App.java.
Project Context

This project was developed as part of the 2nd semester of the Computer Science programme at Erhvervsakademi Aarhus.

The project focused on applying object-oriented programming, software architecture, GUI development, data persistence and testing to a real-world business case.
