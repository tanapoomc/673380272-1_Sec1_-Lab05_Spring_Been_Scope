# Coffee Menu Service (Spring Boot REST API)

> **รายวิชา:** CP353002 — Principles of Software Design and Development (Lab 05)  
> **เทคโนโลยี:** Java 17+, Spring Boot 3.2.5, Maven  

---

## วิธีการรันโปรเจกต์

### 1. รันผ่าน Maven
เปิด Terminal ในโฟลเดอร์หลักของโปรเจกต์ แล้วพิมพ์คำสั่ง:
```bash
mvn spring-boot:run
```

### 2. Build และรันผ่าน JAR
```bash
mvn clean package
java -jar target/coffeemenu-0.0.1-SNAPSHOT.jar
```

เมื่อแอปพลิเคชันเริ่มรัน ระบบจะเปิด Server ที่ `http://localhost:8080`

---

## รายการ API Endpoints & ตัวอย่างการทดสอบด้วย curl

| Method | Endpoint | รายละเอียด | HTTP Status |
|---|---|---|---|
| `GET` | `/coffees` | ดูเมนูกาแฟทั้งหมด | `200 OK` |
| `GET` | `/coffees/{id}` | ดูเมนูกาแฟตาม ID | `200 OK` / `404 Not Found` |
| `POST` | `/coffees` | เพิ่มเมนูกาแฟใหม่ | `201 Created` |
| `PUT` | `/coffees/{id}` | แก้ไขเมนูกาแฟตาม ID | `200 OK` / `404 Not Found` |
| `DELETE` | `/coffees/{id}` | ลบเมนูกาแฟตาม ID | `200 OK` / `404 Not Found` |
| `GET` | `/coffees/search?name=...` | ค้นหาเมนูกาแฟตามชื่อ (Bonus) | `200 OK` |

---

### ตัวอย่างคำสั่ง curl สำหรับทดสอบ

#### 1. ดูเมนูทั้งหมด (`GET /coffees`)
```bash
curl http://localhost:8080/coffees
```

#### 2. ดูเมนูตาม ID (`GET /coffees/{id}`)
```bash
curl http://localhost:8080/coffees/1
```

#### 3. เพิ่มเมนูใหม่ (`POST /coffees`)
```bash
curl -X POST http://localhost:8080/coffees \
     -H "Content-Type: application/json" \
     -d '{"name": "Cappuccino", "price": 60.0}'
```

#### 4. แก้ไขเมนูเดิม (`PUT /coffees/{id}`)
```bash
curl -X PUT http://localhost:8080/coffees/2 \
     -H "Content-Type: application/json" \
     -d '{"name": "Latte", "price": 50.0}'
```

#### 5. ลบเมนู (`DELETE /coffees/{id}`)
```bash
curl -X DELETE http://localhost:8080/coffees/3
```

#### 6. ค้นหาเมนูกาแฟตามชื่อ (`GET /coffees/search?name=...`) [Bonus]
```bash
curl "http://localhost:8080/coffees/search?name=latte"
```
