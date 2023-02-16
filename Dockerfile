# Définit l'image de base pour le conteneur
FROM openjdk:11-jdk-slim

# Définit le répertoire de travail
WORKDIR /meetingsapp

# Copie le jar de l'application dans le conteneur
COPY out/artifacts/MeetingsApp_jar/MeetingsApp.jar meetingsapp.jar

# Expose le port sur lequel l'application écoute
EXPOSE 8084

# Définit les variables d'environnement pour se connecter à la base de données PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/db_meetingsapp_intershop
ENV SPRING_DATASOURCE_USERNAME=cedric
ENV SPRING_DATASOURCE_PASSWORD=Gomo.2025

# Lance l'application au démarrage du conteneur
ENTRYPOINT ["java","-jar","meetingsapp.jar"]
