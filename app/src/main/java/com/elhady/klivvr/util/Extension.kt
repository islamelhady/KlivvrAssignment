package com.elhady.klivvr.util

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment

fun Fragment.openLocationInGoogleMaps(latitude: Double, longitude: Double) {
    val uri = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }
    try {
        startActivity(intent)
    } catch (e: Exception) {
        Log.e("LocationIntent", "Error opening location in Maps", e)
    }
}
