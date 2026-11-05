# Team Turismo — Widget Android V2.9.1 (compilación)

**Estado:** proyecto Kotlin/Jetpack Glance listo para intentar compilación. No incluye APK ya compilado. **La web Team Turismo V2.8 y su miniatura de WhatsApp no se modifican.**

## Lo que hace

- Widget horizontal 4×2: QR grande y datos de Tulio Ontiveros.
- QR sin conexión (imagen local incluida dentro de la aplicación).
- Al tocar el widget abre la tarjeta pública `/compartir-v28/`.
- La app ofrece abrir la tarjeta, contactar por WhatsApp y compartir el enlace.
- No usa permisos de contactos ni accede a datos privados.

## Paso 1 — Probar desde hoy sin instalar APK (Samsung)

Guarda en tu celular `preview/Team_Turismo_Widget_V2_9_Horizontal.png`. En Samsung Galaxy:

1. Pulsa prolongadamente un espacio libre de la pantalla de inicio > **Widgets**.
2. Busca **Galería** > **Imágenes e historias** > **Añadir**.
3. Mantén pulsado el widget > **Ajustes** > **Imágenes para mostrar** y selecciona nuestra imagen.
4. Ajusta el recuadro hasta que se vea el QR completo (sin recortarlo).
5. Escanea con **otro teléfono** y confirma que abra Team Turismo/WhatsApp.

Esta prueba usa el widget de Galería, NO abre la tarjeta al tocar el widget.

## Paso 2 — Compilar el APK automáticamente con GitHub Actions

1. Crea un repositorio **público** en GitHub, por ejemplo `team-turismo-widget-android`.
2. Sube **todos los archivos y carpetas de este proyecto**, incluida `.github/workflows/android-apk.yml`, en la **raíz** del repositorio. No subas únicamente el ZIP: extráelo primero.
3. Abre la pestaña **Actions** > `Compilar APK Team Turismo Android` > **Run workflow**.
4. Espera a que termine la ejecución y confirma que el estado sea verde.
5. Abre el resultado de la ejecución y descarga **Artifacts > Team-Turismo-Widget-Android-V2_9_1-debug**.
6. Descomprime ese artefacto para obtener `app-debug.apk`.

La compilación usa Java 17, SDK Android 36, Gradle 8.13, Android Gradle Plugin 8.13.2, Kotlin 2.3.21 y Jetpack Glance 1.2.0. Para esta ruta **no se necesita Gradle Wrapper** porque GitHub Actions instala Gradle directamente.

## Paso 3 — Instalar en el Samsung/Android

1. Transfiere el APK al teléfono y ábrelo desde Archivos.
2. Si Android pregunta, autoriza **solo la fuente que realmente estés usando** para instalar este APK propio, y desactiva esa autorización después.
3. Instala `Team Turismo Widget` y ábrela una vez.
4. Mantén pulsado un espacio vacío en inicio > **Widgets** > **Team Turismo Widget** > **Añadir**.
5. Elige una zona libre de aproximadamente 4×2 celdas.
6. Desde otro teléfono, escanea el QR; desde tu teléfono, toca el widget para abrir la tarjeta web.

**Nota:** los APK debug de GitHub Actions están firmados con una clave de depuración que puede cambiar en futuras compilaciones. Para actualizaciones estables entre versiones necesitarás una firma de distribución permanente.

## Paso 4 — Compilación en Android Studio (alternativa)

Abre la carpeta del proyecto, configura JDK 17, Android SDK 36 y Gradle 8.13, y ejecuta la tarea `:app:assembleDebug`. Si Android Studio no puede abrir el proyecto por falta de `gradlew`/`gradle-wrapper.jar`, usa la compilación automática de GitHub anterior o genera un wrapper con una instalación de Gradle 8.13.

## Validación efectuada

- `python test/test_qr.py`: el QR funciona en imagen completa, 65% y 50% de tamaño.
- `python test/static_audit.py`: comprueba estructura, recursos, enlaces y XML sin dependencias.
- La combinación Kotlin 2.3.21 / AGP 8.13.2 está alineada con las tablas de compatibilidad oficiales (ver Kotlin Gradle Plugin).
- **No se ha compilado un APK en este entorno**, porque no dispone de Android SDK ni Gradle y su conexión de descargas está restringida.
- **No se ha instalado ni probado** en un Android físico.
