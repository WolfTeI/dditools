package ar.com.wolftei.ddi.screens.home.fragmentsHome

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.ddi.DataClass.DatosCard
import ar.com.wolftei.ddi.composables.CardTemplate
import ar.com.wolftei.ddi.data.Firebase.NewsFirestoreViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun InicioScreen(
    dbNewsViewModel: NewsFirestoreViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Novedades",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 5.dp, top = 2.dp)
        )
        /*dbNewsViewModel.getNews()
        val news: List<DatosCard> = dbNewsViewModel.state.value
        news.forEach {
            Log.d("CuerpoInicio", it.toString())
            CardTemplate(it)
        }*/
    }
}
