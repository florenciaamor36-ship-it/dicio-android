# Dicio — fork de trabajo en español

Este repositorio es un fork de [DicioTeam/dicio-android](https://github.com/DicioTeam/dicio-android), un asistente de voz libre para Android. Conservamos el código, la autoría, la licencia GPL-3.0 y el historial del proyecto original. Este fork está en desarrollo: no es una versión oficial de Dicio.

## Objetivo de este fork

Preparar una versión gratuita, con interfaz y órdenes en español rioplatense, privacidad local y funciones ampliadas. No se requieren suscripciones ni claves de API pagas para el funcionamiento básico. Las funciones que consultan clima, buscadores, traducción u otros servicios externos pueden necesitar internet y se rigen también por las políticas de esos servicios.

## Funciones heredadas de Dicio

- Asistente de voz y entrada por texto.
- Reconocimiento de voz local mediante Vosk, después de descargar el modelo correspondiente.
- Palabra de activación mediante OpenWakeWord, si se configura y se inicia el servicio.
- Habilidades para buscar, consultar el clima, abrir apps, calcular, llamar a contactos, poner temporizadores, consultar la hora, navegar, contar chistes, controlar medios, traducir, gestionar la escucha, leer notificaciones y usar la linterna.
- Idiomas seleccionables desde Ajustes.

La disponibilidad real de cada función depende del idioma, permisos, servicios externos, versión de Android y dispositivo.

## Español y limitaciones conocidas

La interfaz tiene traducción española y el español es el idioma inicial para instalaciones nuevas de este fork. Se puede cambiar desde Ajustes. Se están ampliando las frases de voz argentinas y hace falta probarlas con el reconocedor en teléfonos reales.

La rama principal integra el fork `dicio-numbers` con soporte inicial para números, duraciones y fechas en español. Las pruebas automatizadas cubren cardinales, ordinales, algunas duraciones y fechas; esto no confirma el reconocimiento por voz ni todas las variantes del idioma. Los comandos hablados todavía requieren validación en un dispositivo Android real.

## Compilar

1. Instalá Android Studio y su JDK/Android SDK compatibles con el proyecto.
2. Cloná el fork y abrilo en Android Studio.
3. Esperá la sincronización de Gradle y ejecutá la configuración `app` en un emulador o teléfono.
4. La compilación automática y las pruebas están en la pestaña **Actions**.

También se puede generar el APK debug con el wrapper Gradle del repo usando la tarea `assembleDebug`. Consultá los workflows de GitHub Actions para las versiones exactas de Java y tareas ejecutadas.

## Licencia

La aplicación y los cambios derivados se distribuyen bajo **GNU GPL-3.0**. Al distribuir una versión modificada hay que cumplir la GPL, mantener los avisos correspondientes y ofrecer su código fuente bajo la licencia. Modelos de voz, modelos de IA, bibliotecas, imágenes y otros recursos pueden tener licencias distintas; revisalas individualmente.

## Créditos

El proyecto original Dicio es desarrollado por voluntarios y se publica en [DicioTeam/dicio-android](https://github.com/DicioTeam/dicio-android). Esta copia conserva su atribución; los cambios de este fork se documentan en el historial de commits.
