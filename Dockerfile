# Imagen de Java como base
FROM openjdk:17

# Copia el archivo JAR de la aplicación al contenedor
COPY mi-aplicacion.jar /app/mi-aplicacion.jar

# Establece el directorio de trabajo
WORKDIR /app

# Expone el puerto 9001 para que la aplicación sea accesible
EXPOSE 9001

# Define el comando predeterminado para ejecutar la aplicación
CMD ["java", "-jar", "dice_game.jar"]