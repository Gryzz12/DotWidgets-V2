package com.gryzz.dotwidgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

/** Base de todos los widgets: cualquier evento repinta todo (comparten tema y datos). */
abstract class BaseWidget : AppWidgetProvider() {
    override fun onUpdate(ctx: Context, mgr: AppWidgetManager, ids: IntArray) {
        Updater.async(this, ctx) {
            Weather.maybeFetch(it, false)
            Updater.updateAll(it)
        }
    }

    override fun onAppWidgetOptionsChanged(ctx: Context, mgr: AppWidgetManager, id: Int, opts: Bundle) {
        Updater.async(this, ctx) { Updater.updateAll(it) }
    }

    override fun onEnabled(ctx: Context) {
        Updater.scheduleNext(ctx)
    }

    override fun onDisabled(ctx: Context) {
        // Si ya no queda ningún widget de la app, updateAll cancela la alarma
        Updater.async(this, ctx) { Updater.updateAll(it) }
    }
}

/** Principal: todo en un solo widget (ideal 5×3). */
class DotWidget : BaseWidget()
class ClockWidget : BaseWidget()
class ProgressWidget : BaseWidget()
class CalendarWidget : BaseWidget()
class ClockProgressWidget : BaseWidget()
class ButtonsWidget : BaseWidget()
/** Todo + música con portada y controles. */
class MusicWidget : BaseWidget()
/** Música cuadrado 2×2: portada, toques a los lados para cambiar de canción. */
class MusicSquareWidget : BaseWidget()

class ActionReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        Updater.async(this, ctx) { c ->
            when (intent.action) {
                Updater.ACTION_TICK -> {
                    if (Updater.hasWidgets(c)) Weather.maybeFetch(c, false)
                    Updater.updateAll(c)
                }
                Updater.ACTION_CYCLE -> {
                    Prefs.cycleTheme(c)
                    Updater.updateAll(c)
                }
                Updater.ACTION_TOGGLE_LOC -> {
                    Toggles.toggleLocation(c)
                    Updater.updateAll(c)
                    Thread.sleep(1500)
                    Updater.updateAll(c)
                }
                Updater.ACTION_TOGGLE_SAVER -> {
                    Toggles.toggleSaver(c)
                    Updater.updateAll(c)
                    Thread.sleep(1500)
                    Updater.updateAll(c)
                }
                Updater.ACTION_REFRESH -> {
                    Weather.maybeFetch(c, true)
                    Updater.updateAll(c)
                }
                Updater.ACTION_PREV -> media(c) { Music.previous(c) }
                Updater.ACTION_PLAY -> media(c) { Music.togglePlay(c) }
                Updater.ACTION_NEXT -> media(c) { Music.next(c) }
                else -> Updater.updateAll(c)
            }
        }
    }

    private fun media(c: Context, action: () -> Unit) {
        action()
        Thread.sleep(600)
        Updater.updateAll(c, musicOnly = true)
    }
}
