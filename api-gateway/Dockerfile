#docker imagenin konfigürü
#sıfır kurulumu olan bir makinaya bu uygulamaya kurmak gibi düşün

FROM openjdk:17-slim as build
#java kur

#build dosyalarını kopyala
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway-0.0.1-SNAPSHOT.jar

#build dosyalarını ayağa kaldır
ENTRYPOINT ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]