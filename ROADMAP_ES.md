# Dicio en español — hoja de ruta

Objetivo: convertir este fork en un asistente Android gratuito, en español rioplatense, con privacidad local y funciones avanzadas. La base seguirá bajo GPL-3.0.

## Principios

- Sin suscripciones ni API keys obligatorias.
- Voz y comandos básicos sin internet.
- Modelos locales opcionales, descargables desde la app.
- Confirmación antes de enviar mensajes, llamar, borrar o ejecutar acciones sensibles.
- Permisos mínimos y explicación visible de cada permiso.
- Mantener avisos GPL-3.0 y revisar licencias de modelos, voces y dependencias.

## Fases

### 1. Español y estabilidad

- Revisar el trato rioplatense y corregir traducciones formales o defectuosas.
- Completar comandos y frases en español argentino.
- Agregar pruebas automáticas de reconocimiento.
- Compilar APK debug y release desde GitHub Actions.

### 2. Interfaz propia

- Identidad visual y nombre propios.
- Pantalla moderna con estados: esperando, escuchando, procesando, hablando y error.
- Tema oscuro/claro, colores configurables y avatar animado.
- Animaciones y sonidos opcionales para ahorrar batería.

### 3. Experiencia Android

- Cápsula o botón flotante opcional.
- Modo descanso que detenga completamente la escucha.
- Indicador visible cuando el micrófono esté activo.
- Guía de batería por fabricante y degradación segura en Android 8+.

### 4. Acciones útiles

- Alarmas, temporizadores, recordatorios y calendario.
- Volumen, brillo, linterna, aplicaciones y búsquedas.
- Música: reproducir, pausar, siguiente y anterior.
- Estado de batería, almacenamiento y dispositivo.
- Capturas con permiso; ventana flotante o pantalla dividida cuando Android lo permita.

### 5. Personalidad y memoria

- Personalidad configurable: directa, amable, divertida o seria.
- Memoria local opt-in, visible y borrable.
- Contexto local de conversación.
- Respuestas variadas y voz interrumpible.
- Exportar/importar configuración y memoria localmente.

### 6. IA local opcional

- Motor local según el dispositivo.
- Selector de modelos pequeños y descarga reanudable.
- Modo sin IA para teléfonos modestos.
- Qwen, Gemma o Phi solo respetando sus licencias.
- RAG local para notas y documentos.
- No enviar conversaciones a servidores por defecto.

### 7. Contactos y mensajería segura

- Buscar contactos y preparar llamadas.
- Redactar SMS o WhatsApp por voz.
- Confirmación obligatoria antes de enviar o llamar.
- Leer notificaciones solo de apps elegidas.
- No leer ni guardar chats completos.

### 8. Calidad y publicación

- Auditoría de permisos y tráfico de red.
- Pruebas en teléfonos reales y emuladores.
- Builds reproducibles, hashes y firma de releases.
- Política de privacidad actualizada.
- Publicar APK y código fuente bajo GPL-3.0.

## Costos y límites

El núcleo puede mantenerse gratis. Clima, mapas, búsquedas, Spotify, WhatsApp y modelos grandes pueden requerir internet o sus propias aplicaciones. No se incluirán claves privadas ni servicios pagos obligatorios dentro del APK.
