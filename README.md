# Aplikasi Minimarket

Aplikasi manajemen minimarket berbasis web yang dibangun dengan Spring Boot dan Thymeleaf.

## Fitur

- Autentikasi dan otorisasi pengguna
- Manajemen produk
- Manajemen stok
- Manajemen transaksi
- Laporan penjualan
- Antarmuka pengguna responsif

## Teknologi

- **Backend**: Spring Boot 3.4.1
- **Frontend**: Thymeleaf, Bootstrap
- **Database**: H2 (development), MySQL (production)
- **Build Tool**: Maven
- **Java Version**: 17

## Persyaratan Sistem

- Java 17 atau lebih tinggi
- Maven 3.6.3 atau lebih tinggi
- MySQL 8.0 atau lebih tinggi (untuk production)

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
