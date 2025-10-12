# Hotel Management System | 酒店管理系统

## Overview
This project is a **Java-based desktop application** developed using **Swing GUI** and **JDBC**, designed to manage hotel operations such as guest registration, room booking, dining records, and billing.  
The system supports **CRUD operations** (Create, Read, Update, Delete) for various management modules and ensures data consistency through a relational database (Access/MySQL).

---

## Core Functional Modules

### 1. Guest Information Management
- Manage guest personal details (name, gender, ID, contact, work unit, etc.)
- Record guest accommodation and dining history  
- Admin can **add, edit, delete, or search** guest information  

### 2. Room Management
- Manage room types, phone numbers, and prices  
- Track room availability and occupancy status  

### 3. Guest Accommodation Management
- Record guest check-in and check-out details  
- Support reservation and real-time room assignment  
- Guests can only query their own records; admins have full control  

### 4. Dining Management
- Record customer meal orders and dining history  
- Track dining expenses per guest  

### 5. Billing and Payment Management
- Maintain check-in and check-out times, deposit, and total payment  
- Automatically calculate settlement amount  
- Store transaction history for reference  

---

## 🗃️ Database Design

| Table Name | Description | Key Fields |
|-------------|--------------|-------------|
| 管理员表 (Admin) | System administrators | 编号, 姓名, 密码 |
| 顾客表 (Customer) | Guest personal information | 身份证号, 姓名, 性别, 联系方式, 预定房间 |
| 客房标准信息表 (RoomInfo) | Room details | 客房编号, 类型, 电话, 价格 |
| 订房表 (Booking) | Room reservation records | 客房编号, 客户类型, 客户姓名, 押金, 入住时间 |
| 退房表 (Checkout) | Guest checkout and payment info | 客房编号, 入住人数, 退房时间, 结算金额 |

---

## 💻 System Architecture

**Frontend:** Java Swing GUI  
**Backend:** Java (Socket + JDBC)  
**Database:** Microsoft Access / MySQL  
**IDE:** Eclipse  
**Programming Paradigm:** Object-Oriented Design (OOP)

---

## 🔐 Login System
The system provides secure login verification for administrators.

**Default credentials (for testing):**
- Username: `jsj161`
- Password: `123456`

---

## 🖼️ Main Interfaces

### 🔹 Login Window
User authentication via username and password.  
Window is draggable, frameless, and centered on screen.

### 🔹 Guest Management Interface
Supports CRUD operations, real-time table updates, and search functions.

### 🔹 Main Menu
Contains navigation for File (Exit) and Help (About) options.

---

## ⚙️ Key Technical Features
- Custom Swing UI layout (JFrame + JPanel + BorderLayout)
- Background image rendering with `paintComponent()`
- Mouse events for draggable window
- JDBC connection for database operations
- Modular MVC-style panel structure
- Error handling and user feedback with `JOptionPane`

---

## 📂 Project Structure
