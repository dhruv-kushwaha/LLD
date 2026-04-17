### **Problem Statement**
The goal is to design and build a **Low-Level Design (LLD) for a classic notification system**. While many applications simply integrate notification services as third-party tools, this task involves designing the internal structure and logic of the service itself. The system must be capable of sending various types of notifications (such as SMS, Email, or WhatsApp) to users or clients through an integrated service.

### **Requirements**

The requirements for this system include both functional and non-functional aspects aimed at making the service robust and flexible:

*   **Plug and Play Model:** The notification service must be easy to integrate into any application with **minimal code changes**. It should not require a complete overhaul of an application's architecture to be used.
*   **Highly Extendable Notification Types:** The system should currently support SMS, Email, and Pop-up notifications but must be designed to easily add new channels, such as **WhatsApp**, in the future without significant code modification. This follows SOLID design principles.
*   **Dynamically Extendable Content:** It must be possible to **dynamically add features** to a notification, such as headers, footers, or time stamps, using a decorator-style approach.
*   **Storage and History:** The system must **store all notifications** sent through the service to maintain a complete history.
*   **Logging Feature:** There is a requirement to **log all notifications**. While initially logged to the console, the design should allow for extension to log files.
*   **Multi-Channel Support:** The system should allow a user to receive the same notification through **multiple channels simultaneously** (e.g., receiving a notification via both Email and SMS).
*   **Single Instance Management:** To ensure a consistent history and centralized management, the notification service should be implemented as a **Singleton class**.
*   **Observer-Based Architecture:** The system should use an **Observer design pattern** where a notification engine or logger acts as an observer that waits for the "observable" system to push new notifications.