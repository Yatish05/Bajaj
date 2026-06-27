# Bajaj Finserv REST API (BFHL Endpoint)

A production-ready REST API built with **Java (Spring Boot)** to classify numbers, alphabets, and special characters from an array, parse values safely using arbitrary-precision arithmetic, and construct a reversed alternating-case concatenated alphabetical string.

Developed as part of the **Bajaj Finserv Qualifier 1 API assignment**.

---

## Technical Stack
* **Language**: Java 17
* **Framework**: Spring Boot 3.2.5
* **Build Tool**: Maven
* **Testing**: JUnit 5, Spring MockMvc

---

## Endpoint Details

### 1. POST `/bfhl`
Processes the input array and partitions elements.

* **Request Headers**: `Content-Type: application/json`
* **Request Payload**:
  ```json
  {
    "data": ["a", "1", "334", "4", "R", "$"]
  }
  ```

* **Response Payload (HTTP 200)**:
  ```json
  {
    "is_success": true,
    "user_id": "yatish_bansal_06032005",
    "email": "yatishbansal05@gmail.com",
    "roll_number": "2310991439",
    "odd_numbers": ["1"],
    "even_numbers": ["334", "4"],
    "alphabets": ["A", "R"],
    "special_characters": ["$"],
    "sum": "339",
    "concat_string": "Ra"
  }
  ```

### 2. GET `/bfhl`
A fallback endpoint returning a static operation code.

* **Response Payload (HTTP 200)**:
  ```json
  {
    "operation_code": 1
  }
  ```

---

## Core Features & Logic

* **Arbitrary Precision Arithmetic**: Uses `BigInteger` for numeric parsing instead of `long` or `double`. This supports summing and classifying (even/odd) infinitely large integers without overflow exceptions.
* **String Preservation**: All numeric values in the arrays and the sum are returned as string formats.
* **Reversed Alternating Caps (`concat_string`)**: 
  Extracts all letters from the input array, concatenates them, reverses the final string, and converts characters to alternating caps starting with an uppercase character.
  * *Example*: `["A", "ABCD", "DOE"]` $\rightarrow$ Combined: `"AABCDDOE"` $\rightarrow$ Reversed: `"EODDCBAA"` $\rightarrow$ Output: `"EoDdCbAa"`.
* **CORS Support**: Cross-Origin Resource Sharing is enabled natively (`@CrossOrigin(origins = "*")`) to allow immediate connection to frontend clients hosted on Vercel, Netlify, or localhost.
* **Global Exception Handling**: Returns clean JSON error responses for invalid inputs or malformed JSON payloads.

---

## Running Locally

### Prerequisites
* Java 17 or higher
* Maven 3.x

### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Yatish05/Bajaj.git
   cd Bajaj
   ```

2. **Run Tests**:
   ```bash
   mvn clean test
   ```

3. **Start the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The local service will start on port `8080` (`http://localhost:8080`).

---

## API Testing Examples

### GET Endpoint Check
```bash
curl http://localhost:8080/bfhl
```

### POST Input Array Check
```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]}'
```

---

## Deploying on Railway
1. Sign up on [Railway](https://railway.app).
2. Select **New Project** $\rightarrow$ **Deploy from GitHub repository**.
3. Choose your `Bajaj` repository.
4. Railway will auto-detect the configuration, compile the project using Maven, and provide you with a secure public endpoint (e.g. `https://your-service.up.railway.app/bfhl`).
