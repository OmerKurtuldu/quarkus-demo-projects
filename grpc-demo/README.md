# gRPC ve REST API Projesi

Bu proje, Quarkus kullanarak basit bir gRPC ve REST API örneği sunmaktadır. İki farklı servis yer almaktadır: biri REST API ile çalışırken diğeri gRPC protokollerini kullanır. Bu projede, `GreetingService` ve `SquareService` adında iki basit servis bulunmaktadır. 

## Proje Yapısı

### REST API Servisi
Bu servis, `GreetingService` adında basit bir REST API sunmaktadır ve HTTP üzerinden gelen istekleri işler.

**Endpoint:**
- `GET /hello` - "Hello from Quarkus REST" mesajını döndürür.

### gRPC Servisi
Bu servis, `GreetingService` ve `SquareService` adı altında iki farklı gRPC servisi sunmaktadır.

#### GreetingService
`GreetingService` servisi, "Hello from gRPC" mesajını döndürür.

#### SquareService
`SquareService` servisi, verilen bir sayıya kadar olan sayıların karelerini hesaplar ve toplamını döndürür.

**gRPC Methodları:**
- `calculateSquares(SquareRequest)` - Sayıya kadar olan kareleri hesaplar.
- `hello(Empty)` - Basit bir "Hello from gRPC" mesajı döner.

## Kurulum

### Gereksinimler
- Java 17 veya daha yüksek bir sürüm
- Maven 3.8.1 veya daha yüksek bir sürüm
- Quarkus 2.x
- Protobuf dosyasını kullanabilmek için gerekli araçlar

### Projeyi Çalıştırmak
1. Bu projeyi bir dizine klonlayın:
   ```bash
   git clone https://github.com/username/grpc-rest-project.git
   cd grpc-rest-project
2. Maven bağımlılıklarını yükleyin:
   ```bash
   mvn clean install
3. Quarkus uygulamasını çalıştırın:
   ```bash
   mvn clean install

Uygulama aşağıdaki portlarda çalışacaktır:
  - REST API: http://localhost:8080
  - gRPC: localhost:9000

### Postman ile gRPC İstek Gönderme

1. **Postman Uygulamasını Açın** ve **New** butonuna tıklayın, ardından **Request** seçeneğini seçin.

2. **gRPC İstek Türünü Seçin**:
   - **URL** kısmına `localhost:9000` yazın.
   - Üstteki menüden **gRPC** seçeneğini seçin.

3. **Proto Dosyasını Yükleyin**:
   - Sağ üst köşede, **Body** sekmesinin altında **gRPC** sekmesine tıklayın.
   - **Choose Proto File** butonuna tıklayarak `greeting.proto` veya `square.proto` dosyasını seçin.

4. **Servis ve Metod Seçin**:
   - **GreetingService** servisini seçin ve `hello` metodunu çağırın.
   - **SquareService** için, `calculateSquares` metodunu seçin ve gerekli parametreyi (örneğin, `{ "number": 5 }`) girin.

5. **İstek Gönderin**:
   - **Send** butonuna tıklayın ve yanıtı inceleyin.


