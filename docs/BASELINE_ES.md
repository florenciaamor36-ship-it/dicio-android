# Estado inicial y avance — español

Revisado el 19 de noviembre de 2026 sobre el fork `florenciaamor36-ship-it/dicio-android`.

## Base técnica observada

- Aplicación Android escrita principalmente en Kotlin y Jetpack Compose.
- Licencia GPL-3.0.
- El idioma de la interfaz y las frases se resuelven por `LocaleManager`.
- La preferencia de idioma forma parte de `UserSettings` (protobuf/DataStore).
- Reconocimiento hablado local por Vosk; palabra de activación por OpenWakeWord.
- Las expresiones de órdenes están en `app/src/main/sentences/<idioma>/` y se compilan con el DSL de Dicio.
- Recursos de interfaz están en `app/src/main/res/values-<idioma>/strings.xml`.

## Cambios presentes en el fork

- Se completaron los cuatro IDs de interfaz españoles que faltaban en la base.
- Se añadieron grupos españoles de frases para linterna, chistes, control de escucha, notificaciones y traducción.
- Se ajustaron traducciones de interfaz al trato rioplatense.
- Instalaciones nuevas y migraciones sin preferencia previa ahora usan español como idioma inicial. El usuario puede elegir otro idioma en Ajustes.
- Se añadió una prueba JVM para verificar el idioma inicial y el ajuste de finalización automática de voz.
- Se amplió la cobertura de frases de voz argentinas para cálculos, chistes, escucha, notificaciones y traducción.

## Validaciones conocidas

- El XML español se parseó correctamente.
- En la auditoría de la base se encontraron 271 recursos de interfaz traducibles; la carpeta española tenía todos los IDs al momento de la comprobación.
- En la base inicial, todos los grupos de frases existentes en inglés tenían grupo correspondiente en español luego de las adiciones.
- La CI aceptó un build debug y pruebas JVM del cambio de idioma predeterminado en commit `bfca43673d781b7df21902a438c1e6a4134a6ffa`.
- Las últimas frases de órdenes se deben validar con un nuevo run de CI; que estén escritas no confirma que el parser las interprete como se espera.
- No se probó la aplicación en un teléfono físico ni se validó reconocimiento de voz real.

## Bloqueo de números en español

La dependencia `DicioTeam/dicio-numbers` que usa la app declara solo inglés (`en-us`) e italiano (`it-it`) como compatibles. El propio README de la dependencia indica que extracción de fechas y duraciones todavía tiene limitaciones. Por eso, aun con frases en español, consultas que dependan de extraer números escritos con palabras, fechas o duraciones pueden no funcionar en español. El aviso existente en ajustes se mantuvo honesto y se tradujo.

Para resolverlo hace falta implementar y probar español en esa dependencia GPL-3.0 (parser, extractor de números, fechas/duraciones, formatters, recursos y pruebas), luego fijar el commit probado en el catálogo Gradle del fork. No quitar el aviso ni anunciar compatibilidad hasta que pasen pruebas específicas.

## Aún no auditado o probado

- Traducción semántica de cada frase y ambigüedad entre habilidades.
- Vosk y OpenWakeWord en español argentino sobre dispositivos reales.
- Traducciones de dependencias/Android que no controlan los recursos de esta app.
- Accesibilidad, TalkBack y fuentes ampliadas.
- Calidad de APK release y pruebas de instalación.
- Seguridad y desempeño del servicio de escucha en segundo plano.
