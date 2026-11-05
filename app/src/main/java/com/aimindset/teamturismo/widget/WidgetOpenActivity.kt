package com.aimindset.teamturismo.widget

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

/** Redirige el toque del widget a la tarjeta oficial, sin pedir permisos. */
class WidgetOpenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(LINK_CARD)).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
        }
        try { startActivity(intent) } catch (_: Exception) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}

const val LINK_CARD = "https://team-turismo-tulio-ontiveros.vercel.app/compartir-v28/"
const val LINK_CONTACT = "https://team-turismo-tulio-ontiveros.vercel.app/contacto"
