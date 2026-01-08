# 1. IMAGEN BASE: Usamos una versión ligera de Linux (Alpine) que ya tiene Java 17 y Maven instalados.
FROM maven:3.9.5-eclipse-temurin-17-alpine

# 2. DIRECTORIO DE TRABAJO: Creamos una carpeta '/app' dentro del contenedor para trabajar ordenados.
WORKDIR /app

# 3. COPIAR ARCHIVOS:
# Primero copiamos solo el pom.xml. Esto es un truco de Docker para aprovechar la caché.
COPY pom.xml .

# 4. DESCARGAR DEPENDENCIAS (Pre-cache):
# Descargamos las librerías antes de copiar el código fuente.
# Así, si cambias tu código pero no el pom.xml, Docker no necesita volver a bajar internet entero.
RUN mvn dependency:resolve
RUN mvn dependency:go-offline

# 5. COPIAR CÓDIGO FUENTE:
# Ahora sí, copiamos to do tu código (carpeta src) al contenedor.
COPY src ./src

# 6. COMANDO DE EJECUCIÓN:
# ¿Qué hace el contenedor cuando le das "Start"? Corre los tests.
# Usamos "clean test" y el reporte de Allure.
CMD ["mvn", "test", "allure:report"]