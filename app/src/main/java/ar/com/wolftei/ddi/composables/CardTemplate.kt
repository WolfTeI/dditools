package ar.com.wolftei.ddi.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.com.wolftei.ddi.DataClass.DatosCard

@Composable
fun CardTemplate(contenido: DatosCard) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 200.dp, height = 210.dp)
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        border = BorderStroke(1.dp, Color.Gray),
        shape = CardDefaults.shape
    ) {
        if (contenido.titulo != "") {
            Text(
                text = contenido.titulo,
                modifier = Modifier
                    .padding(top = 10.dp, start = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (contenido.subtitulo != "") {
            Text(
                text = contenido.subtitulo,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Text(
            text = contenido.contenido,
            modifier = Modifier.run { padding(top = 10.dp, start = 5.dp, end = 5.dp) }
        )

        if (contenido.comingsoon) {
            Text(
                text = "Coming Soon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 7.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                textAlign = TextAlign.Right,
                fontStyle = FontStyle.Italic,

                )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    CardTemplate(
        DatosCard(
            contenido = "Aquí va todo el contenido extraído de una api que voy a generar"
        )
    )
}

