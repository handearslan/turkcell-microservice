#docker imagenin konfigürü
#sıfır kurulumu olan bir makinaya bu uygulamaya kurmak gibi düşün

FROM openjdk:17-slim as build
#java kur

#build dosyalarını kopyala
COPY target/productservice-0.0.1-SNAPSHOT.jar productservice-0.0.1-SNAPSHOT.jar

#build dosyalarını ayağa kaldır
ENTRYPOINT ["java","-jar","productservice-0.0.1-SNAPSHOT.jar"]
