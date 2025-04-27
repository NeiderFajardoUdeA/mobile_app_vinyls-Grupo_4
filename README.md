# **Vinyls Mobile App (Grupo 4)**
## Integrantes
- Daniel Andrade Suárez
- Daniel Oicatá
- Juan Camilo Mora
- Neider Alejandro Fajardo

## Pasos para ejecutar proyecto localmente:

Aplicación móvil Android creada en **Kotlin** usando **Jetpack Compose**, **ViewBinding**, **DataBinding** y **Navigation Components**.

Repositorio: https://github.com/NeiderFajardoUdeA/mobile_app_vinyls-Grupo_4.git

**Requisitos Previos**

Android Studio **Hedgehog** o superior (recomendado última versión estable).
JDK 17 instalado (Android Studio ya lo incluye).
Conexión a Internet para sincronizar dependencias.
SDK de Android 35 instalado.

**Instalación y Configuración del Proyecto**

**1. Clonar el Repositorio**

Abre la terminal de tu preferencia y ejecuta:
git clone [https://github.com/NeiderFajardoUdeA/mobile_app_vinyls-](https://github.com/NeiderFajardoUdeA/mobile_app_vinyls-Grupo_4.git) 
[Grupo_4.git](https://github.com/NeiderFajardoUdeA/mobile_app_vinyls-Grupo_4.git) cd mobile\_app\_vinyls-Grupo\_4

**2. Abrir en Android Studio**

Abre **Android Studio**.
Selecciona **"Open"** y navega a la carpeta del proyecto clonado. Espera que Android Studio indexe los archivos.

**3. Sincronizar y Actualizar Gradle**

El proyecto ya está configurado para usar:

**Gradle Plugin**: 8.9.1

**Kotlin**: 2.0.21

**SDK Compile**: 35

**Pasos para asegurarte que todo esté actualizado:**

Cuando abras el proyecto, Android Studio te sugerirá sincronizar el Gradle automáticamente. 

Si no lo hace, hazlo manualmente: **File → Sync Project with Gradle Files**.

Si te pide actualizar dependencias o plugins, acepta las sugerencias automáticas.

En caso de fallos: Ve a **File → Project Structure → Project**.

Verifica:

- Android Gradle Plugin Version: **8.9.1**

- Gradle Version: (Recomendado >= 8.9).

En el módulo app, confirma:

- Compile SDK: **35**

- Target SDK: **35**

- Minimum SDK: **22**

**4. Configurar el Emulador Android (SDK 35)**

Para ejecutar la aplicación necesitas un emulador Android con SDK 35:

**Pasos:**

1\. En Android Studio, abre **Tools → Device Manager**.

2\. Clic en **Create Device**.

3\. Elige un modelo de dispositivo (recomendado: Medium Phone API 35).

4\. En la lista de sistemas, descarga y selecciona:

a. **Android API 35** (Android 14).

5\. Finaliza la creación del emulador.

6\. Asegúrate que esté encendido antes de correr la app.

**Nota:** Si no ves el API 35, instala el SDK: Ve a **Tools → SDK Manager → SDK Platforms → Show Package Details**, y selecciona Android 14 (API 35).

**5. Ejecutar la Aplicación**

Con todo configurado: Selecciona el emulador creado en la barra superior de Android Studio. Haz clic en el botón **Run** .

Android Studio compilará y desplegará la app en el emulador.

**Tecnologías y Librerías Principales**

- **Kotlin**

- **Jetpack Compose** (UI moderna declarativa)

- **Navigation Component**

- **ViewBinding / DataBinding**

- **Picasso** (carga de imágenes)

- **Volley** (red)

- **JUnit** y **Espresso** (Testing)

**Consejos Ðtiles**

- Si ves errores de dependencias, limpia y reconstruye el proyecto: `./gradlew clean build`

- Si tienes problemas con el emulador, intenta con un **cold boot** o reinicia Android Studio.

- Recomendado usar el **Emulador ARM64** si tu máquina lo soporta, para mayor velocidad.
