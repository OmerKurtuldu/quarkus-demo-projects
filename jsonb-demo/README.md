# JSON-B Demo Projesi

Bu proje, Quarkus framework'ü kullanılarak geliştirilmiş bir JSON-B (JSON Binding) demo uygulamasıdır.

## 🚀 Teknolojiler

- **Quarkus 3.5.0**: Kubernetes Native Java framework
- **Java 21**: JDK sürümü
- **RESTEasy Reactive**: Reactive REST endpoints için
- **Hibernate Reactive with Panache**: Reactive veritabanı işlemleri
- **PostgreSQL Reactive Client**: Reactive PostgreSQL bağlantısı
- **Mutiny**: Reactive programlama kütüphanesi
- **SmallRye OpenAPI**: API dokümantasyonu için

## 🛠️ Gereksinimler

- JDK 21
- Maven 3.8.1+
- Docker (opsiyonel)
- PostgreSQL

## 🏃‍♂️ Projeyi Çalıştırma

1. Projeyi klonlayın:
```bash
git clone [proje-url]
cd jsonb-demo
```

2. Geliştirme modunda çalıştırın:
```bash
./mvnw compile quarkus:dev
```

## 🐳 Docker ile Çalıştırma

1. Docker imajı oluşturun:
```bash
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t jsonb-demo .
```

2. Container'ı çalıştırın:
```bash
docker run -i --rm -p 8080:8080 jsonb-demo
```

## 📦 Native Executable Oluşturma

GraalVM ile native executable oluşturmak için:

```bash
./mvnw package -Pnative
```

## 📚 API Dokümantasyonu

Swagger UI'a erişmek için:
```
http://localhost:8080/q/swagger-ui
```

## 🧪 Test

Testleri çalıştırmak için:
```bash
./mvnw test
```

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır.
