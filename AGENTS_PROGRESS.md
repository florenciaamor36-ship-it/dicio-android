# Seguimiento del plan de AGENTS.md

Actualizar este archivo en cada tanda de trabajo. No marcar fase completa solo por haber escrito código.

## Estado al 19-11-2026

- **Fase 0 — línea base:** inspección inicial hecha; resultados en `docs/BASELINE_ES.md`.
- **Fase 1 — español:** interfaz con todos los IDs revisados; idioma inicial español para instalaciones nuevas y usuarios migrados sin idioma previo; frases actualizadas al habla rioplatense para hora, linterna, temporizadores, cálculos, chistes, apertura de apps, búsquedas, navegación, llamadas y clima. La compilación valida los archivos DSL; faltan pruebas del reconocimiento real y evaluación de ambigüedades.
- **Fase 2 — interfaz propia:** primera tarjeta de bienvenida, avatar vectorial dibujado con Compose y estado de voz añadido a la pantalla principal. CI de compilación debug y JVM terminó success en commit `b65aef923e4c23872bb0602d09cfbf355a3f6d8b`. Falta prueba visual en emulador/teléfono y diseño integral.
- **Fases 3–8:** pendientes: cápsula/overlay, acciones nuevas, memoria, IA local, mensajería segura, auditoría integral y release firmada.
- **Dependencia numérica:** se inició soporte experimental en la rama `feature/spanish-locale` del fork GPL `florenciaamor36-ship-it/dicio-numbers`, con tokenizer, parser, formatter y pruebas iniciales. La rama experimental todavía no se integra en `main`. CI pasó las pruebas JVM de la biblioteca y un build Android integrado (`assembleDebug`, `lintDebug`, `testDebugUnitTest`) para el mismo código de producción (run 44610728448). En la tanda de regresión posterior también pasaron las pruebas actuales de biblioteca; el job Android más nuevo (run 44614305250) quedó en curso en el build de la app, sin resultado final confirmado. Se corrigieron tokens duplicados, la colisión de “mañana” como hora/fecha y una unidad abreviada inválida. Se detectó además que “doscientos uno” se interpreta como 101; el caso se dejó fuera de las pruebas que pasan y sigue pendiente corregirlo. No promover hasta resolver ese y otros casos de gramática, y probar comandos hablados en emulador/dispositivo.

## Validaciones

- XML de recursos españoles parsea correctamente; en la auditoría había cero IDs de interfaz faltantes.
- Las frases españolas cubren los grupos de habilidades existentes; se añadieron variaciones argentinas para operaciones, chistes, escuchar, notificaciones y traducción.
- CI debug + pruebas JVM: commit `68dc1aba61bfb7c652f5a305819ab1559a6b1b8f` terminó success para strings/frases.
- CI debug + pruebas JVM: commit `091a4bbde2c86e0b65e148d5852b31d7ff649633` terminó success para la tanda de comandos rioplatenses (hora, linterna, timer, calculadora y chistes).
- CI debug + pruebas JVM: commit `1dbdd0309188cd91b416041b5edfbf460913edb2` terminó success para comandos de abrir apps, buscar, navegar, llamar y consultar el clima.
- CI debug + pruebas JVM: commit `b65aef923e4c23872bb0602d09cfbf355a3f6d8b` terminó success para la tarjeta/avatar de bienvenida.
- El intento intermedio `708f380486f4a80ded66e27ec56f6fd38c2e0e7e` falló por integración de parámetros/import preview; corregido en el commit posterior exitoso.
- No se probó la app en teléfono físico ni se validó la voz en uso real. El arte del avatar y el estado visual solo están confirmados por compilación.

## Limitación importante

Aunque las frases españolas existen, la dependencia numérica no admite español; interpretar números en palabras, fechas y duraciones todavía no está implementado. Mantenerlo visible y no anunciar esos casos como soportados.

## Próximas tareas

1. Añadir pruebas directas del matcher para frases argentinas y evitar ambigüedades, más allá de la compilación del DSL.
2. Instalar/correr el APK debug en emulador y revisar visualmente la tarjeta de bienvenida.
3. Corregir la interpretación de “doscientos uno” (actualmente 101), ampliar regresiones de números, ordinales, plurales, fechas y duraciones en `feature/spanish-locale`, confirmar el run 44614305250 y validar comandos hablados en emulador/dispositivo antes de decidir si se integra el PR experimental.
4. Completar la interfaz propia/avatar según `AGENTS.md`.
5. Continuar fases siguientes solo con compilación y pruebas, registrando claramente cada limitación.
