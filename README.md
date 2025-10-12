# Hotel Management System | 酒店管理系统

## Overview

A **Java-based hotel management system** built using **Swing GUI** and **SQL Server** database.  
This project allows administrators to manage guest information, room data, supplier and purchase records, and user access with role-based control.  
It integrates Java’s GUI frontend with JDBC backend logic to create a complete management platform.
The system supports **CRUD operations** (Create, Read, Update, Delete) for various management modules and ensures data consistency through a relational database (Access/MySQL).
The system is built using a **modular, object-oriented architecture**, divided into two components:  
- `hotelAuthServer`: authentication server  
- `hotel`: main client application  


## Core Functional Modules

### 1. Guest Information Management
- Add, edit, delete, and search guest information.  
- Fields include: name, gender, ID number, contact info, work unit, reserved room number, etc.  
- Guests can view their own data; administrators can manage all guests.
  
### 2. Room Management
- Manage hotel room information: room ID, type, phone, price, and status (available / occupied).  
- Supports adding, updating, searching, and deleting rooms.

### 3. Check-in / Check-out Management
- Handles guest check-in and check-out records.  
- Includes deposit handling and billing calculations.  
- Supports querying by guest name, ID number, or room number.

### 4. Dining Management
- Records guest dining and consumption information.  
- Tracks dishes, quantity, unit price, and total cost.  
- Provides bill summaries for guests and administrators.

### 5. Billing Management
- Manages deposits, total expenses, and settlement details.  
- Automatically calculates room and dining costs.  
- Generates final bills at checkout.

### 6. System Maintenance
- Administrator login authentication.  
- Database maintenance and reset options.  
- Operation logs stored in `hotel_log.ldf`.



## Database Design
The database is located in the `shujuk/` folder.

**Files:**
- `hotel.mdf`: Main database file  
- `hotel_log.ldf`: Log file

**Main Tables:**

| Table Name | Description | Key Fields |
|-------------|--------------|-------------|
| 管理员表 (Admin) | System administrators | 编号, 姓名, 密码 |
| 顾客表 (Customer) | Guest personal information | 身份证号, 姓名, 性别, 联系方式, 预定房间 |
| 客房标准信息表 (RoomInfo) | Room details | 客房编号, 类型, 电话, 价格 |
| 订房表 (Booking) | Room reservation records | 客房编号, 客户类型, 客户姓名, 押金, 入住时间 |
| 退房表 (Checkout) | Guest checkout and payment info | 客房编号, 入住人数, 退房时间, 结算金额 |



## Project Structure
```
HotelManagementSystem/
│
├── 项目源文件（src）/
│ ├── hotelAuthServer/ # 登录认证服务端
│ │ ├── .settings/
│ │ ├── bin/
│ │ └── src/
│ │ └── common/
│ │
│ └── hotel/ # 主程序客户端
│ ├── .settings/
│ ├── bin/
│ └── src/
│ ├── common/ # 公共功能模块
│ ├── purchase/ # 采购模块
│ ├── seller/ # 餐饮或销售模块
│ ├── supplier/ # 供应商管理模块
│ └── user/ # 用户与登录模块
│
├── shujuk(database)/ # 数据库文件夹
│ ├── hotel.mdf
│ └── hotel_log.ldf
│
└── README.md
```



## System Environment

| Component | Description |
|------------|-------------|
| Language | Java |
| IDE | Eclipse |
| Database | Microsoft SQL Server |
| Paradigm | Object-Oriented Programming (OOP) |
| GUI Framework | Swing |
| Database Access | JDBC |



## Default Login 
- **Username:** `jsj161`  
- **Password:** `123456`



## Technical Highlights
- Implemented GUI using **Swing + BorderLayout + JPanel**.  
- Database connection via **JDBC** for CRUD operations.  
- Movable, centred login window with drag functionality.  
- Background rendering via `paintComponent()`.  
- User prompts through `JOptionPane`.  
- Clear separation between admin and user privileges.  



## Interface Screenshots

```markdown
![Login](../screenshots/login.png)
![MainMenu](../screenshots/main.png)
![GuestInfo](../screenshots/guest.png)

