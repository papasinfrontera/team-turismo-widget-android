package com.aimindset.teamturismo.widget

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24.dp(), 28.dp(), 24.dp(), 24.dp())
            setBackgroundColor(Color.rgb(176, 227, 245))
            gravity = Gravity.CENTER_HORIZONTAL
        }
        container.addView(TextView(this).apply {
            text = "Team Turismo"
            textSize = 25f
            setTextColor(Color.rgb(5, 31, 46))
            gravity = Gravity.CENTER
        })
        container.addView(TextView(this).apply {
            text = "Tulio Ontiveros · Widget Android V2.9.2"
            textSize = 15f
            gravity = Gravity.CENTER
            setPadding(0, 6.dp(), 0, 20.dp())
        })
        container.addView(ImageView(this).apply {
            setImageResource(R.drawable.team_widget_horizontal)
            adjustViewBounds = true
            contentDescription = "Vista previa del widget con QR"
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        container.addView(Button(this).apply {
            text = "Abrir tarjeta digital"
            setOnClickListener { open(LINK_CARD) }
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        container.addView(Button(this).apply {
            text = "Abrir WhatsApp de Tulio"
            setOnClickListener { open(LINK_CONTACT) }
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        container.addView(Button(this).apply {
            text = "Compartir tarjeta"
            setOnClickListener {
                val share = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, LINK_SHARE_CARD)
                }
                startActivity(Intent.createChooser(share, "Compartir Team Turismo"))
            }
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        container.addView(TextView(this).apply {
            text = "Para agregar el widget: mantén pulsada la pantalla de inicio → Widgets → Team Turismo → arrástralo a la pantalla. El QR permanece visible sin conexión."
            textSize = 14f
            setTextColor(Color.rgb(23, 64, 85))
            setPadding(0, 22.dp(), 0, 0)
        })
        setContentView(container)
    }

    private fun open(link: String) {
        try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link))) } catch (_: Exception) {
            android.widget.Toast.makeText(this, "No hay navegador disponible", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    private fun Int.dp(): Int = (this * resources.displayMetrics.density).toInt()
}

/** Enlace exclusivo para compartir desde Android: mismo HTML y miniatura OG, URL nueva para la caché de WhatsApp. */
const val LINK_SHARE_CARD = "https://team-turismo-tulio-ontiveros.vercel.app/compartir-v28/?origen=widget-android-v292"
