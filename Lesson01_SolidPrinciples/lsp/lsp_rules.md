# Liskov Substitution Principle Rules

**Broader Class** -> The parent class of a sub class.

**Narrow Class** -> The child class of a parent class. 

## Signature Rule

### 1. Method Arguement Rule
    The method parameter of a child class should be exactly same type or broader than the parent class method parameter

### 2. Return Type Rule
    The return type of a method of a child class should be exact or narrower than the parent class.

### 3. Exception Rule
    The exception thrown by a child class method should be exactly same as or narrower than the parent class.

## Property Rule

### 1. Class Invariant Rule
    The invariant of a child class should be same as or stronger than the parent class.

### 2. History Constraint Rule
    The history constraint of a child class should be same as or stronger than the parent class.

## Method Rule
### 1. Post Condition Rule
    The post condition of a child class method should be same as or stronger than the parent class method.

### 2. Pre Condition Rule
    The pre condition of a child class method should be same as or weaker than the parent class