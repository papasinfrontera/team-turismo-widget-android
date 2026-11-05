package com.aimindset.teamturismo.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.action.clickable

/**
 * Widget estático, deliberadamente offline. El QR se genera al empaquetar la app
 * y contiene la misma URL de contacto confirmada en Team Turismo V2.8.
 * Pulsar la tarjeta abre la web en el navegador mediante WidgetOpenActivity.
 */
class TeamTurismoWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent { WidgetFace(Intent(context, WidgetOpenActivity::class.java)) }
    }

    @Composable
    private fun WidgetFace(openIntent: Intent) {
        Image(
            provider = ImageProvider(R.drawable.team_widget_horizontal),
            contentDescription = "Team Turismo: QR de contacto de Tulio Ontiveros. Toca para abrir la tarjeta.",
            contentScale = ContentScale.Fit,
            modifier = GlanceModifier.fillMaxSize()
                .clickable(onClick = actionStartActivity(openIntent))
        )
    }
}

class TeamTurismoWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TeamTurismoWidget()
}
