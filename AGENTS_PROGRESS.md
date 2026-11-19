# Seguimiento del plan de AGENTS.md

Actualizar este archivo en cada tanda de trabajo. No marcar fase completa solo por haber escrito código.

## Estado al 19-11-2026

- **Fase 0 — línea base:** inspección inicial hecha; resultados en `docs/BASELINE_ES.md`.
- **Fase 1 — español:** cobertura de IDs revisada, idioma inicial puesto en español, textos mejorados, grupos de frases añadidos y pruebas de preferencia inicial creadas. Falta verificar CI de las frases nuevas y revisar manualmente ambigüedades con órdenes de voz reales.
- **Fases 2–8:** pendientes. No se inició todavía rediseño, avatar, overlay, acciones nuevas, memoria ni IA local.
- **Bloqueo conocido:** `dicio-numbers` no incluye español; números hablados, fechas y duraciones requieren trabajo separado y pruebas antes de prometer soporte.

## Última validación positiva

CI de Android debug + pruebas JVM en commit `bfca43673d781b7df21902a438c1e6a4134a6ffa` terminó `success`.

## Run pendiente de validar

Después de añadir frases argentinas de cálculo, chistes, escucha, notificaciones y traducción, disparar CI sobre el último commit y revisar tanto resultado de Gradle como pruebas. Si falla, corregir la causa antes de continuar.

## Próximas tareas

1. Verificar CI de las frases nuevas.
2. Revisar y corregir patrones de todos los grupos españoles; incorporar pruebas de reconocimiento para frases naturales del Río de la Plata.
3. Completar y probar la dependencia de números en español o mantener claramente la limitación.
4. Recién después comenzar el diseño de la pantalla principal y avatar, con captura/emulador para validación.
