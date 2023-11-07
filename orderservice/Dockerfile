#docker imagenin konfigürü
#sıfır kurulumu olan bir makinaya bu uygulamaya kurmak gibi düşün

FROM openjdk:17-slim as build
#java kur

#build dosyalarını kopyala
COPY target/orderservice-0.0.1-SNAPSHOT.jar orderservice-0.0.1-SNAPSHOT.jar

#build dosyalarını ayağa kaldır
ENTRYPOINT ["java","-jar","orderservice-0.0.1-SNAPSHOT.jar"]
