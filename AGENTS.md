# AGENTS.md — Plan técnico para convertir Dicio en un asistente Android profesional

## 1. Objetivo del proyecto

Transformar este fork de Dicio en un asistente Android gratuito, completo y en español rioplatense, manteniendo el funcionamiento offline siempre que sea posible.

La aplicación debe aspirar a ofrecer:

- Interfaz moderna y propia.
- Avatar animado con estados emocionales.
- Cápsula o botón flotante opcional.
- Activación por voz y botón manual.
- Voz de entrada y salida sin conexión.
- Personalidad configurable.
- Memoria local opcional y borrable.
- Contexto de conversación local.
- Acciones del teléfono con confirmación.
- Recordatorios, alarmas, temporizadores y calendario.
- Control de música y aplicaciones.
- Mensajería segura con confirmación antes de enviar.
- IA local opcional para conversación y comprensión.
- Compilación automática y releases reproducibles.

El núcleo debe seguir siendo gratuito. No agregar suscripciones, API keys obligatorias ni servicios pagos imprescindibles.

## 2. Reglas obligatorias para cualquier agente

1. Leer primero `README.md`, `ROADMAP_ES.md`, la licencia GPL-3.0 y la documentación actual.
2. Trabajar siempre sobre una rama descriptiva; no editar directamente `main` si la tarea es grande.
3. Hacer cambios pequeños y comprobables. Un commit debe representar una mejora coherente.
4. No reemplazar archivos completos si basta una modificación puntual.
5. No borrar funciones existentes sin documentar el motivo y verificar equivalentes.
6. No cambiar el motor de reconocimiento de voz sin probar modelos y permisos en Android real.
7. No incorporar claves, tokens, contraseñas, datos personales ni endpoints privados al código.
8. No descargar modelos o binarios de fuentes no verificadas.
9. Revisar las licencias de cada dependencia, modelo, voz, icono, imagen y fuente.
10. Mantener GPL-3.0, avisos de copyright y código fuente distribuible.
11. Antes de afirmar que algo está listo, compilar y probar la función; HTTP 200 o código escrito no equivalen a validación.
12. Si una prueba no pudo ejecutarse en un teléfono real, decirlo claramente.
13. Mantener compatibilidad con Android 8+ salvo decisión documentada.
14. Toda acción sensible debe pedir confirmación: llamadas, mensajes, borrado, cambios de configuración, accesibilidad y acciones irreversibles.
15. No activar micrófono permanente, accesibilidad u overlays sin explicación visible y consentimiento del usuario.

## 3. Estado inicial conocido

- Base: fork de `DicioTeam/dicio-android`.
- Repo de trabajo: `florenciaamor36-ship-it/dicio-android`.
- Rama principal: `main`.
- Licencia: GPL-3.0.
- Lenguaje principal: Kotlin.
- Reconocimiento de voz: Vosk.
- Palabra de activación: OpenWakeWord, con modelo predeterminado de Dicio.
- Ya existe una traducción amplia al español.
- Ya se agregaron frases en español para linterna, chistes, escucha, notificaciones y traducción.
- Ya se corrigieron varias frases al trato rioplatense.
- Existe CI de GitHub Actions para compilar APK y ejecutar pruebas JVM.
- La primera validación de la traducción compiló correctamente.

No asumir que el código está completamente traducido, que el wake word funciona en español ni que una función de Android quedó probada solo porque compila.

## 4. Arquitectura que se debe conservar

Antes de modificar una función, ubicar sus tres capas:

1. **Recursos y textos**
   - `app/src/main/res/values-es/strings.xml`
   - `app/src/main/sentences/es/`
   - Recursos de temas, iconos y configuraciones.

2. **Evaluación de órdenes**
   - `app/src/main/kotlin/org/stypox/dicio/eval/`
   - `SkillEvaluator`, ranker, capturas y tipos de argumentos.
   - No introducir patrones ambiguos que hagan que una orden existente se interprete como otra habilidad.

3. **Habilidades y salida**
   - `app/src/main/kotlin/org/stypox/dicio/skills/`
   - `Info`, `Skill`, `Output`, permisos y servicios asociados.
   - Las respuestas visuales y habladas deben usar recursos traducibles, no textos hardcodeados.

Respetar la separación existente entre entrada, evaluación, habilidad, permisos y presentación. Si una modificación necesita atravesar varias capas, documentarlo en el commit.

## 5. Fase 0 — Inventario y línea base

Antes de agregar funciones:

- Enumerar módulos Gradle, paquetes, permisos y servicios Android.
- Registrar versión de Kotlin, Gradle, Android Gradle Plugin, compile SDK y min SDK.
- Ejecutar CI sobre el commit actual.
- Ejecutar pruebas JVM existentes.
- Generar una lista de todos los recursos de `values/strings.xml` sin traducción española.
- Comparar las carpetas `sentences/en` y `sentences/es`.
- Detectar textos hardcodeados en Kotlin y Compose.
- Detectar permisos declarados pero no explicados al usuario.
- Detectar accesos de red y dominios externos.
- Documentar qué se probó en emulador y qué requiere teléfono real.

Entregable: `docs/BASELINE_ES.md` con resultados, limitaciones y comandos de reproducción.

## 6. Fase 1 — Español completo y consistente

### Recursos

- Completar todos los IDs faltantes en `values-es/strings.xml`.
- Usar español rioplatense informal de forma consistente: `vos`, `querés`, `podés`, `elegí`, `configurá`, `probá`.
- Evitar mezclar `usted`, `tú` y `vos`.
- Corregir ortografía, tildes y concordancia.
- Mantener intactos los placeholders `%1$s`, `%2$d`, formatos numéricos y etiquetas Android.
- No traducir nombres propios, marcas, paquetes, URLs ni nombres técnicos cuando deban permanecer iguales.
- Revisar accesibilidad: `contentDescription`, títulos, mensajes de error y botones.

### Órdenes de voz

- Completar todas las habilidades existentes en `sentences/es`.
- Agregar formas naturales argentinas sin hacer patrones demasiado amplios.
- Cubrir variantes con y sin tildes cuando el motor lo requiera.
- Probar órdenes como:
  - `Techi, prendé la linterna`.
  - `apagá la luz`.
  - `contame un chiste`.
  - `dejá de escuchar`.
  - `leeme las notificaciones`.
  - `poné un temporizador de cinco minutos`.
  - `llamá a mamá`.
  - `abrí WhatsApp`.
  - `buscá información sobre Android`.
  - `¿qué tiempo hace mañana?`.
- Agregar pruebas de frases válidas, ambiguas y negativas.
- No agregar una habilidad nueva hasta verificar su prioridad frente a las existentes.

## 7. Fase 2 — Identidad visual y experiencia principal

Crear una interfaz propia sin destruir la accesibilidad ni la navegación de Dicio.

### Pantalla principal

- Estado inicial claro y amigable.
- Botón grande para hablar.
- Entrada de texto opcional.
- Historial local de conversación configurable.
- Indicador de estado: listo, escuchando, procesando, hablando, error y sin permisos.
- Avatar centrado o lateral según el tamaño de pantalla.
- Tema claro y oscuro.
- Paleta configurable.
- Compatibilidad con fuentes grandes y TalkBack.

### Avatar

- Usar recursos originales o recursos con licencia compatible.
- No incorporar imágenes generadas o copiadas sin licencia.
- Estados mínimos: neutral, escuchando, pensando, hablando, feliz, confundido, error y descansando.
- Animaciones reproducibles a bajo consumo.
- Respetar `prefers-reduced-motion` o una opción de reducir animaciones.
- No mantener animaciones pesadas cuando la app está en segundo plano.

### Marca

- Elegir nombre, icono y paleta antes de cambiar identificadores de aplicación.
- No eliminar la atribución a Dicio ni ocultar la licencia.
- Si se cambia el application ID, planificar migración y pruebas de actualización.

## 8. Fase 3 — Cápsula flotante y servicios

- Implementar overlay solo como función opt-in.
- Explicar el permiso `SYSTEM_ALERT_WINDOW` antes de solicitarlo.
- Mostrar un indicador visual inequívoco cuando el micrófono esté activo.
- Agregar botón de descanso que detenga el servicio y libere el micrófono.
- Respetar restricciones de Android para foreground services.
- Crear una notificación persistente clara y accionable.
- Manejar correctamente Android 10, 11, 12, 13 y versiones posteriores.
- Probar rotación, pantalla bloqueada, ahorro de batería y reinicio del teléfono.
- No ocultarse de forma engañosa ni evadir indicadores del sistema.

## 9. Fase 4 — Acciones del teléfono

Implementar en orden de menor a mayor riesgo:

1. Hora, fecha, batería y estado del dispositivo.
2. Linterna, volumen y brillo mediante APIs permitidas.
3. Abrir aplicaciones y configuraciones.
4. Temporizadores, alarmas y recordatorios.
5. Calendario con confirmación de título, fecha y hora.
6. Música mediante controles oficiales de Android.
7. Capturas de pantalla con permiso y explicación.
8. Ventana flotante o pantalla dividida cuando la API y el fabricante lo permitan.
9. Contactos y llamadas con confirmación antes de marcar.
10. SMS y WhatsApp con previsualización y confirmación antes de enviar.

Para cada acción documentar:

- Permiso requerido.
- Qué datos lee.
- Qué datos guarda.
- Qué ocurre si el usuario rechaza el permiso.
- Qué ocurre sin conexión.
- Qué confirmación ve el usuario.
- Pruebas realizadas y versiones de Android probadas.

## 10. Fase 5 — Personalidad y memoria local

### Personalidad

- Crear perfiles configurables: directa, amable, divertida y seria.
- Guardar preferencias localmente.
- No convertir la personalidad en instrucciones ocultas que puedan ejecutar acciones sin permiso.
- Mantener respuestas cortas cuando la pantalla esté bloqueada.

### Memoria

- Activación explícita y desactivada por defecto si implica datos personales.
- Mostrar qué se guardó y permitir borrar elementos individuales.
- Botón para borrar toda la memoria.
- Guardar localmente en almacenamiento privado.
- No guardar audio crudo por defecto.
- No guardar contactos completos ni chats completos.
- Separar historial de conversación de memoria permanente.
- Probar exportación e importación local sin enviar datos a internet.

## 11. Fase 6 — IA local opcional

- Mantener Dicio funcional sin IA local.
- Usar un motor con licencia compatible y mantenimiento razonable.
- Evaluar `llama.cpp`, `MLC LLM` u otra alternativa Android antes de integrar.
- Descargar modelos solo desde URLs verificadas y mostrar tamaño, licencia y espacio requerido.
- Permitir cancelar, pausar y reanudar la descarga.
- Verificar hash del modelo.
- Elegir modelo según RAM y arquitectura del teléfono.
- No incluir modelos grandes dentro del APK si aumenta innecesariamente el tamaño.
- Aclarar que modelos Qwen, Gemma, Phi y otros tienen licencias propias.
- No mandar conversaciones al modelo remoto por defecto.
- Agregar un modo de baja memoria y un modo sin IA.
- Para RAG local, indexar solo archivos seleccionados por el usuario.

## 12. Fase 7 — Servicios online opcionales

- Clima, mapas, búsqueda, letras y traducción deben funcionar como habilidades separadas.
- Mostrar cuando una orden requiere internet.
- No ocultar el destino de las consultas.
- No incluir claves API personales en el APK.
- Preferir servicios sin registro y con límites razonables.
- Revisar términos y licencias de cada servicio.
- Permitir desactivar cada habilidad online.
- No enviar nombre, contactos, historial ni audio si no es estrictamente necesario.

## 13. Fase 8 — Seguridad y privacidad

- Auditar `AndroidManifest.xml` y reducir permisos.
- Revisar servicios de accesibilidad con especial cuidado.
- No leer pantalla ni chats fuera de la acción concreta autorizada.
- No enviar mensajes sin confirmación explícita.
- Proteger preferencias sensibles con almacenamiento privado.
- No usar WebView para acciones que puedan realizarse con APIs Android.
- Verificar certificados HTTPS cuando corresponda.
- Registrar errores sin incluir audio, textos privados, teléfonos, contactos o tokens.
- Actualizar la política de privacidad junto con cada permiso nuevo.
- Añadir una pantalla de permisos con explicación sencilla.

## 14. Fase 9 — Pruebas y calidad

### Automatizadas

- Compilación debug.
- Compilación release.
- Pruebas JVM.
- Pruebas de parser de frases.
- Pruebas de permisos y estados de error.
- Lint y análisis estático.
- Verificación de recursos traducidos.
- Verificación de placeholders XML.
- Verificación de hashes y artefactos.

### Manuales

Probar al menos:

- Android 8, 10, 12, 13 y una versión reciente.
- Teléfono con 2–3 GB de RAM y otro con 6–8 GB.
- Español de Argentina y reconocimiento con ruido moderado.
- Micrófono ocupado por otra app.
- Sin internet.
- Sin permiso de micrófono.
- Pantalla bloqueada.
- Ahorro de batería.
- Rotación y modo oscuro.
- TalkBack y tamaño de fuente grande.
- Cancelación de una acción sensible.
- Rechazo de un mensaje antes de enviarlo.

No declarar una función lista si solo pasó la compilación.

## 15. CI, releases y distribución

- Mantener workflows separados para CI, APK debug, release y capturas.
- Generar APK firmado solo con secretos de GitHub Actions, nunca en el código.
- No imprimir keystores, contraseñas ni tokens.
- Adjuntar hashes SHA-256 de cada APK.
- Publicar notas de versión con cambios, permisos y limitaciones.
- Publicar el código fuente correspondiente a todo binario distribuido.
- Mantener instrucciones reproducibles para compilar.
- Probar que el APK instalado coincide con el commit indicado.
- No decir que una release está lista hasta descargarla y verificarla.

## 16. Criterio de aceptación para cada cambio

Un cambio solo se considera terminado cuando:

- El código está publicado en el fork.
- Existe un commit descriptivo.
- La compilación correspondiente terminó correctamente.
- Las pruebas relacionadas pasan.
- La interfaz está traducida.
- Los permisos y la privacidad están documentados.
- La función fue probada manualmente si depende de Android, voz, overlay o accesibilidad.
- Se registraron limitaciones conocidas.
- No se agregaron servicios pagos obligatorios.
- Se revisaron licencias nuevas.

## 17. Orden recomendado de trabajo

1. Terminar auditoría y español rioplatense.
2. Corregir y ampliar pruebas de frases.
3. Crear la nueva pantalla principal.
4. Agregar estados visuales y avatar.
5. Implementar cápsula flotante opt-in.
6. Agregar recordatorios, alarmas y calendario.
7. Agregar controles del teléfono y música.
8. Agregar personalidad y memoria local.
9. Integrar IA local opcional.
10. Agregar mensajería con confirmación.
11. Auditar permisos, red y privacidad.
12. Probar en teléfonos reales.
13. Crear release gratuita con código, APK y hashes.

## 18. Formato de reporte de cada agente

Cada agente debe informar:

- Qué archivos modificó.
- Qué comportamiento agregó o corrigió.
- Qué comandos y dispositivos probó.
- Qué build ejecutó y cuál fue el resultado.
- Qué no pudo probar.
- Qué permisos, dependencias o licencias nuevas introdujo.
- Qué queda pendiente.

Nunca informar “listo” basándose únicamente en que el código fue escrito o que GitHub aceptó el commit.
