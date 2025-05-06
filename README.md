# URL Shortener Service

This project implements a simple URL shortening service with custom encoding/decoding logic and in-memory storage. It is built using Spring Boot for the backend and React for the frontend.

## How to Run the Backend (Spring Boot)

1. Clone the repository to your local machine:
    ```bash
    git clone <repository_url>
    ```

2. Navigate to the backend folder:
    ```bash
    cd backend
    ```

3. Install the required dependencies:
    ```bash
    ./mvnw install
    ```

4. Start the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

   The application will be available at `http://localhost:8080`.

## How to Run the Frontend (React)

1. Navigate to the frontend folder:
    ```bash
    cd frontend
    ```

2. Install the required dependencies:
    ```bash
    npm install
    ```

3. Start the React development server:
    ```bash
    npm start
    ```

   The frontend will be available at `http://localhost:3000`.
--------------------------------------------------------------------------------------------------------------------------------
## API Endpoints

### POST /encode
Shortens the provided URL.

**Request Body:**
```json
{
    "originalUrl": "https://indicina.co/"
}
**Response Body:**
```json
{
    "shortUrl": "http://short.est/c24c51"
}
------------------------------------------------------------------------------------------------------------------------------
## POST /decode
Decodes the provided shortened URL to the original URL.
**Request Body:**
```json
{
    "shortUrl": "http://short.est/c24c51"
}
**Response Body:**
```json
{
    "originalUrl": "https://indicina.co/"
}
-----------------------------------------------------------------------------------------------------------------------------
## GET /statistic
Returns stats for a particular short URL (like number of hits).
GET /statistics/c24c51
**Response Body:**
```json
{
    "originalUrl": "https://indicina.co/",
    "shortUrl": "http://short.est/c24c51",
    "urlPath": "c24c51",
    "createdAt": "2025-05-06T20:20:26.7614556",
    "visitCount": 0
}
-----------------------------------------------------------------------------------------------------------------------------
## GET /list
Returns all encoded URLs with their short versions.
**Response Body:**
```json
[
    {
        "originalUrl": "https://indicina.co/",
        "shortUrl": "http://short.est/c24c51",
        "urlPath": "c24c51",
        "createdAt": "2025-05-06T20:20:26.7614556",
        "visitCount": 0
    }
]
-----------------------------------------------------------------------------------------------------------------------------
**How to Run the Tests**
To run the tests for the backend (Spring Boot):

Navigate to the backend folder:
``bash
cd backend
Run the tests:
``bash
./mvnw test

## Test Descriptions

testEncodeAndDecodeFlow()
Tests a full cycle: encode a URL, then decode it back to the original. Validates successful encoding and decoding responses.

testDecodeFailsWhenUrlIsUnknown()
Tests the failure case where an invalid or unknown short URL is passed for decoding. Expects a 400 Bad Request error.

testListAllShortLinks()
Tests the /list endpoint to ensure all shortened URLs are correctly returned and structured.

testGetStatistics()
Tests the /statistics/{shortUrl} endpoint, confirming it returns the correct usage stats for a given short URL.	
