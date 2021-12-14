package com.zetes.twodo.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import com.zetes.twodo.HomeActivity
import com.zetes.twodo.R

class TwodoWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { appWidgetId ->
            val pendingIntent =
                PendingIntent.getActivity(context, 0, Intent(context, HomeActivity::class.java), 0)

            // setup the widget layout
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.twodo_widget_layout
            ).apply {
                setOnClickPendingIntent(R.id.textViewWidgetTitle, pendingIntent)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)

        // TODO: The widget was placed or resized
    }
}