# **Vinyls Mobile App (Grupo 4)**
<img src="app/src/main/res/mipmap-hdpi/vinilo.png" alt="Descripción de la imagen" width="200"/>
## Integrantes
- **Daniel Andrade Suárez**
- **Daniel Oicatá Hernández**
- **Juan Camilo Mora**
- **Neider Alejandro Fajardo**

## Pasos para ejecutar proyecto localmente:
Repositorio: https://github.com/NeiderFajardoUdeA/mobile_app_vinyls-Grupo_4.git

**Requisitos Previos**
- Git instalado para manejo de repositorios.
- Android Studio `2024.3.1` o superior (recomendado última versión estable).
- JDK 17 instalado (Android Studio ya lo incluye).
- SDK de Android 35 instalado.

**Instalación y Configuración del Proyecto**

**1. Clonar el Repositorio**

Desde una terminal de PowerShell ejecutar:
`git clone https://github.com/NeiderFajardoUdeA/mobile_app_vinyls-Grupo_4.git`

**2. Sincronizar y Actualizar Gradle**

Al abrir el proyecto, Android Studio sugieré sincronizar el Gradle automáticamente dando clic en `Sync now`. 

Si no lo hace, se puede hacer manualmente desde: **File → Sync Project with Gradle Files**.

Es recomendable actualizar dependencias o plugins aceptando las sugerencias automáticas.

En caso de fallos: Ir a **File → Project Structure → Project** para verificar la configuración del proyecto:

- Android Gradle Plugin Version: **8.9.1**

- Gradle Version: (Recomendado >= 8.9).

En el módulo app, confirmar:

- Compile SDK: **35**

- Target SDK: **35**

- Minimum SDK: **22**

**4. Configurar el Emulador Android (SDK 35)**

Para ejecutar la aplicación se requiere un emulador Android con SDK 35:

**Pasos:**

**4.1\.** En Android Studio, abrir **Tools → Device Manager**.

**4.2\.** Clic en **Create Device**.

**4.3\.** Elegir un modelo de dispositivo (recomendado: Medium Phone API 35).

**4.4\.** En la lista de sistemas, descargar y seleccionar:

- a. **Android API 35** (Android 14).

**4.5\.** Finalizar la creación del emulador.

**4.6\.** Asegurarse que esté encendido antes de correr la app.

**5. Ejecutar la Aplicación**

- Con todo configurado: Seleccionar el emulador creado en la barra superior de Android Studio. Hacer clic en el botón **Run**. Android Studio compilará y desplegará la app en el emulador.

- Para las pruebas automatizadas con Espresso, el emulador configurado es suficiente pero en este caso se debe ir al directorio `com.moviles.vinilos(androidTest)`, dar clic derecho en `AlbumTest` y luego `Run 'AlbumTest'`.

**Tecnologías y Librerías Principales**

- **Kotlin**

- **Jetpack Compose**

- **Navigation Component**

- **ViewBinding / DataBinding**

- **Picasso** (carga de imágenes)

- **Volley** (BackEnd)

- **JUnit** y **Espresso** (Testing)

**Consejos Útiles**

- Si hay errores de dependencias, limpiar y reconstruir el proyecto: `./gradlew clean build`
- Si hay problemas con el emulador, intentar con un **cold boot** o reiniciar Android Studio.
