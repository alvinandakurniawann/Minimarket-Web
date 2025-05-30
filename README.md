# Minimarket Management System

A comprehensive web-based minimarket management application built with Spring Boot and Thymeleaf. This system helps store owners manage their inventory, sales, and business operations efficiently.

## 🚀 Features

### User Management
- 🔐 Secure authentication and role-based authorization
- 👥 User roles: Admin, and buyer
- 👤 User profile management

### Product Management
- 📦 Add, edit, and delete products
- 📊 Product categories and variants
- 📸 Product image support
  
### Inventory Management
- 📦 Real-time stock tracking
- 🔄 Stock movement history
- 📦 Supplier management

### Reporting
- 📋 Transaction history

## 🛠️ Technologies Used

### Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Frontend
- Thymeleaf
- Bootstrap 5
- JavaScript
- jQuery

### Database
- MySQL 8.0

### Tools
- IntelliJ IDEA / vscode
- Git
- Postman (for API testing)

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.8+
- MySQL 8.0 or higher
- Node.js (for frontend dependencies)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/minimarket-management.git
   cd minimarket-management
   ```

2. **Configure Database**
   - Create a new MySQL database
   - Update `application.properties` with your database credentials

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**
   Open your browser and visit: `http://localhost:8080`
   - Admin credentials: admin@minimarket.com / admin123
   - Cashier credentials: cashier@minimarket.com / cashier123

## 📂 Project Structure

```
src/main/java/
├── com/minimarket/
│   ├── config/           # Configuration classes
│   ├── controller/       # MVC Controllers
│   ├── model/            # Entity classes
│   ├── repository/       # Data access layer
│   ├── service/          # Business logic
│   ├── dto/              # Data Transfer Objects
│   ├── exception/        # Custom exceptions
│   └── security/         # Security configurations
src/main/resources/
├── static/              # Static files (CSS, JS, images)
├── templates/           # Thymeleaf templates
└── application.properties # Application configuration
```

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Contact

For any inquiries, please contact [your-email@example.com](mailto:your-email@example.com)

---

<div align="center">
  Made with ❤️ by Your Name
</div>
- Antarmuka pengguna responsif

## Teknologi

- **Backend**: Spring Boot 3.4.1
- **Frontend**: Thymeleaf, Bootstrap
- **Database**: MySQL 8.0+
- **Build Tool**: Maven
- **Java Version**: 17

## Persyaratan Sistem

- Java 17 atau lebih tinggi
- Maven 3.6.3 atau lebih tinggi
- MySQL 8.0 atau lebih tinggi

## Instalasi

1. Clone repository ini:
   ```bash
   git clone [url-repository]
   cd web
   ```

2. Konfigurasi database di `application.properties`

3. Build project:
   ```bash
   mvn clean install
   ```

4. Jalankan aplikasi:
   ```bash
   mvn spring-boot:run
   ```

5. Buka browser dan akses:
   ```
   http://localhost:8080
   ```

## Struktur Proyek

```
src/
├── main/
│   ├── java/
│   │   └── com/minimarket/
│   │       ├── config/       # Konfigurasi Spring
│   │       ├── controller/   # Controller
│   │       ├── model/        # Entity dan DTO
│   │       ├── repository/   # Repository JPA
│   │       ├── security/     # Konfigurasi keamanan
│   │       ├── service/      # Business logic
│   │       └── WebApplication.java
│   └── resources/
│       ├── static/          # Aset statis (CSS, JS, gambar)
│       ├── templates/       # File template Thymeleaf
│       └── application.properties
└── test/                     # Test cases
```

## Kontribusi

1. Fork repository ini
2. Buat branch fitur baru (`git checkout -b fitur/namafitur`)
3. Commit perubahan (`git commit -m 'Menambahkan fitur baru'`)
4. Push ke branch (`git push origin fitur/namafitur`)
5. Buat Pull Request

## Lisensi

[MIT License](LICENSE)
