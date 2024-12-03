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


Hayır, şu an yazdığınızda formatlama hataları oluşmuş. Mesela Response Body kısmı ve diğer komutlar düzgün şekilde görünmüyor. Bunun sebebi, doğru markdown formatında yapıştırılmadığı için JSON ve kod blokları düzgün şekilde ayrılmıyor.

İstediğiniz şeyi doğru bir şekilde kopyalayabileceğiniz formatta aşağıda paylaşıyorum. Bu metni kopyaladığınızda her şey doğru şekilde düzenlenecek:

markdown
Kodu kopyala
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
Response Body:
json
Kodu kopyala
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
💻 Kurulum ve Çalıştırma
1. Projeyi Klonlayın
bash
Kodu kopyala
git clone https://github.com/<OmerKurtuldu>/quarkus-rest-api.git
cd quarkus-rest-api
2. Bağımlılıkları Yükleyin
bash
Kodu kopyala
./mvnw compile quarkus:dev
3. Uygulamayı Çalıştırın
bash
Kodu kopyala
./mvnw quarkus:dev
API'yi Test Edin
Hello Endpoint: http://localhost:8080/hello — "Hello" mesajını döner.
Square Endpoint: http://localhost:8080/square — JSON formatında sayı göndererek kare hesaplaması yapabilirsiniz.
markdown
Kodu kopyala

