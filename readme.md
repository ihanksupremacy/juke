📘 Juke Employee Management API

REST API sederhana untuk mengelola data karyawan di perusahaan Juke, dibuat menggunakan Spring Boot, PostgreSQL, dan berjalan menggunakan Docker Compose.

API ini memiliki fitur CRUD lengkap, validasi input, global error handling, dokumentasi Swagger, serta response JSON yang konsisten melalui MessageModel.

🚀 Fitur Utama
🔹 1. CRUD Employee

Create karyawan

Get semua karyawan

Get karyawan berdasarkan ID (UUID)

Update karyawan

Delete karyawan

🔹 2. Validasi Input

Menggunakan @Valid dan Hibernate Validator:

Email wajib & harus format valid

Salary harus lebih dari 0

🔹 3. Global Exception Handling

Menangani error secara konsisten dalam format JSON:

Validation error

Duplicate email

Entity not found

Internal server error

🔹 4. Swagger (OpenAPI)

Dokumentasi otomatis dapat diakses di:

http://localhost:8080/swagger-ui.html

🔹 5. Docker Support

Tinggal jalankan:

docker compose up --build -d


Semua service (Spring Boot + PostgreSQL) akan otomatis berjalan.

🛠️ Teknologi yang Digunakan
Teknologi	Keterangan
Java 22	Bahasa utama
Spring Boot 3	Framework API
PostgreSQL 15	Database
Docker & Docker Compose	Containerization
Lombok	Reduksi boilerplate
Spring Validation	Validasi input
Spring Data JPA	ORM
Springdoc OpenAPI	Swagger Documentation
📂 Struktur Project
Juke/
│── src/
│── Dockerfile
│── docker-compose.yml
│── pom.xml
│── README.md

🧪 Cara Menjalankan Project
1️⃣ Clone Repository
git clone https://github.com/<username>/Juke.git
cd Juke

2️⃣ Build JAR (opsional jika tidak pakai Docker)
mvn clean package

3️⃣ Jalankan Dengan Docker
docker compose up --build -d

4️⃣ Cek service yang berjalan
docker compose ps

🌐 Endpoint API
📄 Employee
Method	Endpoint	Deskripsi
GET	/api/employees	List semua karyawan
GET	/api/employees/{id}	Get berdasarkan UUID
POST	/api/employees	Create karyawan
PUT	/api/employees/{id}	Update karyawan
DELETE	/api/employees/{id}	Hapus karyawan
📘 Contoh Request Body (Create Employee)
{
  "name": "Raihan",
  "email": "raihan@example.com",
  "position": "Developer",
  "salary": 5000000
}

⚠️ Contoh Error Response (Global Exception)
Email duplikat:
{
  "status": false,
  "message": "Email sudah digunakan",
  "data": null
}

Validasi gagal:
{
  "status": false,
  "message": "Salary must be greater than 0",
  "data": null
}

🐳 Docker Setup
Dockerfile (Ringkas)
FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

docker-compose.yml

Menjalankan API + PostgreSQL bersama-sama.

services:
  postgres:
    image: postgres:15
    container_name: postgres_db
    environment:
      POSTGRES_USER: raihan
      POSTGRES_PASSWORD: password
      POSTGRES_DB: juke
    ports:
      - "5432:5432"

  springboot:
    build: .
    container_name: springapp
    ports:
      - "8080:8080"
    depends_on:
      - postgres

👨‍💻 Dikembangkan Oleh

Raihan Muhaimin
Backend Developer – Spring Boot & Docker
