# Seguimiento del plan de AGENTS.md

Actualizar este archivo en cada tanda de trabajo. No marcar fase completa solo por haber escrito código.

## Estado al 19-11-2026

- **Fase 0 — línea base:** inspección inicial hecha; resultados en `docs/BASELINE_ES.md`.
- **Fase 1 — español:** interfaz con todos los IDs revisados; idioma inicial español para instalaciones nuevas y usuarios migrados sin idioma previo; frases actualizadas al habla rioplatense para hora, linterna, temporizadores, cálculos, chistes, apertura de apps, búsquedas, navegación, llamadas y clima. La compilación valida los archivos DSL; faltan pruebas del reconocimiento real y evaluación de ambigüedades.
- **Fase 2 — interfaz propia:** primera tarjeta de bienvenida, avatar vectorial dibujado con Compose y estado de voz añadido a la pantalla principal. CI de compilación debug y JVM terminó success en commit `b65aef923e4c23872bb0602d09cfbf355a3f6d8b`. Falta prueba visual en emulador/teléfono y diseño integral.
- **Fases 3–8:** pendientes: cápsula/overlay, acciones nuevas, memoria, IA local, mensajería segura, auditoría integral y release firmada.
- **Dependencia numérica:** soporte inicial integrado en el fork `dicio-numbers` (PR #1, merge `e66999aed2a3deed69202d31b99970edab368ce5`) y app `main` fijada a esa revisión. Corrige centenas cardinales (“doscientos uno” = 201) y ordinales, con más de 200 regresiones flexionadas. La biblioteca y build Android integrado pasaron en run 44626354305. La primera CI de `main` falló porque la configuración aún apuntaba el checkout al upstream y no encontraba el commit del fork; se cambió la URL de `settings.gradle.kts` al fork. Runs 44630016117 y 44631100952 siguen marcados `in_progress` en Gradle, sin resultado final consultable. Se agregaron también variantes rioplatenses de voseo para temporizador y linterna en commit `d307ba5ff5342f8e4be28e8f852bdce153fedecc`; falta confirmar CI. No se ha validado reconocimiento por voz en un teléfono.

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
3. Confirmar la CI de `main` tras corregir la URL del fork; ampliar pruebas de fechas/duraciones y órdenes válidas, ambiguas y negativas; validar comandos hablados en emulador/dispositivo.
4. Completar la interfaz propia/avatar según `AGENTS.md`.
5. Continuar fases siguientes solo con compilación y pruebas, registrando claramente cada limitación.
