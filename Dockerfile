# Imagen de Java como base
FROM openjdk:17

# Copia el archivo JAR de la aplicación al contenedor
COPY Dice-Game-Backend-JWT-Security/out/artifacts/dice_quest_jar/dice_game.jar /app/dice_game.jar

# Establece el directorio de trabajo
WORKDIR /app

# Expone el puerto 9001 para que la aplicación sea accesible
EXPOSE 9001

# Define el comando predeterminado para ejecutar la aplicación
CMD ["java", "-jar", "dice_game.jar"]