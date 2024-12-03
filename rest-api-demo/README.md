# Quarkus REST API Project  
Bu proje, **Quarkus** kullanılarak geliştirilmiş basit bir REST API uygulamasıdır. Proje, aşağıdaki iki ana işlevi sağlamaktadır: 1. **Hello Endpoint**: Basit bir "Hello" mesajı döner. 2. **Square Calculation Endpoint**: Verilen sayıya kadar olan sayıların karelerini hesaplar ve toplamlarını döner.  

## 📚 Kullanılan Teknolojiler  
- **Quarkus**: Java tabanlı, hızlı ve bulut yerel bir framework. - **Jakarta REST API (JAX-RS)**: RESTful servisler için standart bir API. - **JSON**: Veri iletiminde kullanılan format.  

## 🚀 API Endpoints  
### 1. `/hello`  
- **Method**: `GET`  
- **Açıklama**: "Hello from Quarkus REST" mesajını döner.   

### 2. `/square`  
- **Method**: `POST`  
- **Açıklama**: Gönderilen sayıya kadar olan sayıların karelerini ve toplamlarını döner.  
- **Request Body**:  
```json  
{  
  "number": 5  
}  

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

## 💻 Kurulum ve Çalıştırma

### Projeyi Klonlayın:
git clone https://github.com/<OmerKurtuldu>/quarkus-rest-api.git  
cd quarkus-rest-api  

###Bağımlılıkları Yükleyin

./mvnw compile quarkus:dev  


###Uygulamayı Çalıştırın

./mvnw quarkus:dev  

## API'yi Test Edin:
http://localhost:8080/hello - "Hello" mesajını döner.
http://localhost:8080/square - JSON formatında sayı göndererek kare hesaplaması yapabilirsiniz.



