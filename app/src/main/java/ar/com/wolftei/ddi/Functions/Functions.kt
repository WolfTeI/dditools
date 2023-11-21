package ar.com.wolftei.ddi.Functions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.icu.text.SimpleDateFormat
import java.util.Date

fun clipBoard(context: Context, text: String){
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip: ClipData = ClipData.newPlainText("copy", text)
    clipboard.setPrimaryClip(clip)
}

fun fechaActual(): String? {
    val sdf = SimpleDateFormat("dd/M/yyyy")
    return sdf.format(Date())
}
fun horaActual(): String? {
    val sdf = SimpleDateFormat("HH:mm")
    return sdf.format(Date())
}