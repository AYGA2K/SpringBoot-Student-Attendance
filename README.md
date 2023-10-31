# Student Attendance App

This project is a simple Student Attendance Management System built using Java and Spring Boot. It allows you to manage the attendance records of students in a school.

## Features

- Create, update, and delete student records.
- Record student absences.
- View a list of absence students .

## Technologies Used

- Spring Boot
- Spring Data JPA
- Thymeleaf for the user interface
- MySQL for the database
- HTMX for dynamic updates

## Class Diagram

```plaintext
+---------------------------------------- +     +-------------------------------+
|                Student                  |     |             Absence           |
+-----------------------------------------+     +-------------------------------+
| - id: Long                              |     | - id: Long                    |
| - firstName: String                     |     | - date: String                |
| - lastName: String                      |     | - students: List            |
| - absences: List                        |     |                               |
+-----------------------------------------+     +-------------------------------+
| + getId(): Long                         |     | + getId(): Long               |
| + getFirstName(): String                |     | + getDate(): String           |
| + getLastName(): String                 |     | + getStudents(): List         |
| + getAbsences(): List                   |     |                               |
| + setId(id: Long)                       |     | + setDate(date: String)       |
| + setFirstName(firstName: String)       |     | + setStudent(student: List)   |
| + setLastName(lastName: String)         |     |                               |
| + setAbsences(absences: List)           |     |                               |
+-----------------------------------------+     +-------------------------------+

```
