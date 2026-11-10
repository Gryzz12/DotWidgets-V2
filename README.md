# Dot Widgets (Nothing Phone 3)

8 widgets de estilo Nothing (Android 12+). Todos se pueden redimensionar y se reorganizan solos.

| Widget | Tamaño ideal | Qué muestra |
|---|---|---|
| Dot · Todo | 5×3 (completo hasta 5×4) | Reloj + arco del sol, tiempo, progreso, botones y calendario |
| Dot · Reloj y tiempo | 3×2 | Día, hora, arco del sol y temperatura |
| Dot · Progreso | 4×2 | Año, mes, día y hora en puntos |
| Dot · Calendario | 3×3 | Mes con el día de hoy (abre Google Calendar) |
| Dot · Reloj y progreso | 4×3 | Reloj encima del progreso, o al lado si es ancho |
| Dot · Botones | 3×1 | Ubicación, ahorro de batería y tema |
| Dot · Música | 2×2 | Solo la portada, sin texto ni botones visibles; toque izquierda = anterior, derecha = siguiente, centro = pausa o abrir la última app de música |
| Dot · Todo + música | 5×4 | Todo + canción que suena con portada, anterior, play/pausa y siguiente |

Para la música, abre la app y activa "Acceso a notificaciones". Si Android lo bloquea como ajuste restringido: Info de la app → ⋮ → Permitir ajustes restringidos.

## Conseguir el APK sin instalar nada (GitHub)

1. Crea un repositorio nuevo en github.com.
2. Sube el contenido de esta carpeta, incluida `.github/workflows/build.yml`.
3. Ve a la pestaña **Actions**, abre la última ejecución de "Compilar APK" y descarga el artefacto **DotWidgets-apk**.
4. Descomprímelo en el móvil e instala `app-debug.apk`. Te pedirá permitir apps de origen desconocido.

## Con Android Studio

Abre la carpeta y pulsa *Run*. Si te pide el Gradle wrapper, acepta: usa Gradle 8.14.3.

## Botones que activan y desactivan directamente (opcional)

Si no haces nada más, los botones abren el ajuste correspondiente de Android. Para que cambien con un solo toque, ejecuta esto una vez desde un PC con la depuración USB activada:

```
adb shell pm grant com.gryzz.dotwidgets android.permission.WRITE_SECURE_SETTINGS
```

## Ubicación del tiempo

Por defecto es Lorca. Puedes cambiarla desde la app, buscando una ciudad o con "Usar mi ubicación actual".
