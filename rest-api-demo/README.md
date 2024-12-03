
# Quarkus REST API Projesi

Bu proje, **Quarkus** kullanılarak geliştirilmiş basit bir REST API uygulamasıdır. Proje, aşağıdaki iki ana işlevi sağlamaktadır:
1. **Hello Endpoint**: Basit bir "Hello" mesajı döner.
2. **Square Calculation Endpoint**: Verilen sayıya kadar olan sayıların karelerini hesaplar ve bunların toplamını döner.

## 📚 Kullanılan Teknolojiler
- **Quarkus**: Java tabanlı, hızlı ve bulut yerel bir framework.
- **Jakarta REST API (JAX-RS)**: RESTful servisler için standart bir API.
- **JSON**: Veri iletiminde kullanılan format.


## 🚀 API Endpoints

### 1. `/hello`
- **Method**: `GET`
- **Açıklama**: "Hello from Quarkus REST" mesajını döner.

### 2. `/square`
- **Method**: `POST`
- **Açıklama**: Gönderilen sayıya kadar olan sayıların karelerini ve bunların toplamını döner.
- **Request Body**:
  ```json
  {
    "number": 5
  }

- **Response Body**:
    ```json
    {
        "results": [
            {"number": 1, "square": 1},
            {"number": 2, "square": 4},
            {"number": 3, "square": 9},
            {"number": 4, "square": 16},
            {"number": 5, "square": 25}
        ],
        "total": 55
    }

