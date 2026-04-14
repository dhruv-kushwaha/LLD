These notes summarize the system design of a food delivery application (referred to as **"Tomato"**) based on the provided video transcript.

### **1. Problem Statement**
The goal is to design a low-level design (LLD) for a food delivery application similar to Zomato or Swiggy. The session is structured as a mock interview where requirements are gathered, a happy flow is discussed, a UML diagram is drawn, and clean code is implemented.

### **2. Requirements Gathering**

#### **Functional Requirements**
These define the core business logic and features of the application:
*   **Search:** Users can search for restaurants based on their location.
*   **Cart Management:** Users can add food items from a specific restaurant to a cart.
*   **Checkout/Payment:** Users can checkout and make payments via a third-party service.
*   **Order Tracking/Notification:** Once an order is placed, the user receives a success notification.
*   **Order Types:** Support for both **Delivery** (to home) and **Pickup** (from the restaurant).

#### **Non-Functional Requirements**
These focus on the technical qualities of the system:
*   **Scalability:** The system should be able to grow and handle more users/restaurants.
*   **Clean Code:** Use of OOP principles, design patterns (Strategy, Factory, Singleton), and clear object relationships.
*   **Modularization:** Ensuring classes are loosely coupled to allow for future extensions.

---

### **3. Happy Flow Discussion**
The "Happy Flow" represents the standard user journey through the app:
1.  **Search:** User enters the platform and searches for restaurants by location.
2.  **View Menu:** User opens a restaurant to view its **Menu Items**.
3.  **Cart:** User adds selected items to their **Cart**.
4.  **Order Placement:** User chooses an order type (Delivery or Pickup) and places the order.
5.  **Payment:** User completes the transaction using a payment strategy (UPI, Card, etc.).
6.  **Confirmation:** The system triggers a **Notification Service** to inform the user.

---

### **4. Step-by-Step UML and Class Relationships**
The designer uses a **Bottom-Up Approach**, starting with smaller, independent objects before building larger manager classes.

#### **Step 1: Basic Models (MenuItem & Restaurant)**
*   **MenuItem:** Stores basic dish details like Code, Name, and Price.
*   **Restaurant:** Contains an ID, Name, Address, and a list of MenuItems.
*   **Relationship:** **Composition** (Strict). A `MenuItem` is generally considered dependent on a `Restaurant`; if the restaurant is deleted, the menu items cease to exist in that context.

#### **Step 2: Restaurant Manager**
*   **RestaurantManager:** Manages the list of all restaurants and handles "Search by Location" logic.
*   **Relationship:** **Aggregation**. The manager "has-a" list of restaurants, but restaurants can exist independently of the manager. It is also a **Singleton** to ensure a single source of truth for restaurant data.

#### **Step 3: User & Cart**
*   **User:** Contains ID, Name, Address, and a Cart.
*   **Cart:** Stores a reference to one Restaurant and a list of selected MenuItems.
*   **Relationship:** **Composition**. Every user has exactly one cart, and a cart cannot exist without a user.

#### **Step 4: Payment (Strategy Pattern)**
*   **PaymentStrategy (Interface):** Defines a `pay()` method.
*   **Concrete Strategies:** Classes like `UPIPayment` and `CreditCardPayment` implement the interface.
*   **Relationship:** **Association**. The `Order` class uses a strategy to process payments without needing to know the implementation details.

#### **Step 5: Order & Factory (Factory Method Pattern)**
*   **Order (Abstract):** Base class for orders with attributes like User, Restaurant, and Items.
*   **Subclasses:** `DeliveryOrder` (requires user address) and `PickUpOrder` (requires restaurant address).
*   **OrderFactory:** An interface to create orders.
*   **Concrete Factories:** `NowOrderFactory` (immediate orders) and `ScheduleOrderFactory` (future orders).
*   **Relationship:** **Inheritance** for the order types and **Dependency** for the factory creating the product.

#### **Step 6: Orchestrator Class (The "Tomato" App)**
*   **Tomato Class:** Acts as an **Orchestrator** or a single point of contact for the client (Front-end).
*   **Responsibility:** It delegates requests to the `RestaurantManager`, `OrderFactory`, and `NotificationService`.
*   **Relationship:** **Association/Dependency**. It knows about almost every other class to coordinate the flow, which is a trade-off that violates the Single Responsibility Principle for the sake of a simplified client interface.

---

### **5. Summary of Relationships in UML**
*   **Composition (Filled Diamond):** Used when the child object cannot exist without the parent (e.g., `Restaurant` $\rightarrow$ `MenuItem`, `User` $\rightarrow$ `Cart`).
*   **Aggregation (Empty Diamond):** Used when the parent "has" children but they can exist independently (e.g., `RestaurantManager` $\rightarrow$ `Restaurant`, `OrderManager` $\rightarrow$ `Order`).
*   **Association (Simple Arrow):** A general "uses" or "knows-about" relationship where objects remain loosely coupled (e.g., `Cart` $\rightarrow$ `Restaurant`, `Order` $\rightarrow$ `PaymentStrategy`).
*   **Inheritance:** Used to define a hierarchy for varying types of the same object (e.g., `Order` $\rightarrow$ `DeliveryOrder`).


## FLAWS
The current design for the "Tomato" application, while functional for a mock interview, contains several structural flaws related to object-oriented design principles. The sources identify these flaws and suggest specific extensions to make the system more scalable and robust.

### **Flaws in the Current Design**

1.  **Violation of the Single Responsibility Principle (SRP):**
    *   The **`Tomato` orchestrator class** is the biggest design flaw. It is responsible for searching restaurants, managing carts, handling checkout, processing payments, and triggering notifications.
    *   Because it handles multiple business concerns, any change to one part of the system (like changing how carts work) requires modifying this central class.

2.  **Violation of the Principle of Least Knowledge (Law of Demeter):**
    *   The `Tomato` class and the `OrderFactory` are **tightly coupled** to the internal structures of other objects.
    *   For example, the orchestrator frequently "reaches through" objects to get data, such as extracting a `Cart` from a `User`, then a `Restaurant` from that `Cart`, and finally `MenuItems` from that `Restaurant`. Ideally, an object should only talk to its immediate "friends".

3.  **Manual Strategy Selection:**
    *   In the current implementation, the logic for choosing a specific **Payment Strategy** (e.g., UPI vs. Credit Card) resides in the `main` method or the orchestrator. This logic should be encapsulated rather than exposed to the client.

4.  **Rigid Notification System:**
    *   The **`NotificationService`** is currently a simple, concrete class that just logs output. It lacks the flexibility to handle different channels like SMS, Email, or WhatsApp without modifying its existing code.

---

### **Possible Extensions to Overcome Flaws**

To evolve the design into a "perfect" or production-ready system, the following extensions are suggested:

**1. Decentralized API and Service Layers**
*   Instead of one `Tomato` orchestrator, move toward a **modern framework architecture** (like Spring Boot or Django).
*   **Separation of Concerns:** Create a dedicated **API/Controller layer** to handle incoming requests and a **Service layer** for business logic.
*   Each service would handle a specific domain (e.g., a `SearchService`, a `CartService`, etc.), and requests would be routed to specific endpoints like `/searchRestaurant` instead of passing through a single class.

**2. Implementing a Strategy Factory**
*   To fix the manual selection of payment methods, you can combine the **Factory and Strategy patterns**.
*   Create a **`PaymentStrategyFactory`** that contains the logic to decide which strategy (UPI, Net Banking, or Card) to pick based on user input, removing this responsibility from the `main` method.

**3. Enhancing the Notification Service**
*   Convert the `NotificationService` into an **interface or abstract class**.
*   Create concrete subclasses for different notification types (e.g., `PushNotification`, `EmailNotification`, `WhatsAppNotification`) that override the `notify()` method. This allows the system to send multiple types of notifications without changing the caller's code.

**4. Scaling Restaurant Management**
*   Currently, the `RestaurantManager` handles both the management and the creation of restaurants.
*   A possible extension is to separate these concerns by introducing a **`RestaurantFactory`**, mirroring the scalable design used for the `Order` system.

**5. Refining the Factory Method**
*   The `OrderFactory` currently takes many individual arguments (User, Cart, Restaurant, etc.) to avoid breaking the Principle of Least Knowledge. 
*   An extension would be to pass a **Total Cost** or a simplified **Order Data Object** to the factory to ensure it remains a "dumb" object that only knows how to build an order, not how to extract data from a User or Cart.