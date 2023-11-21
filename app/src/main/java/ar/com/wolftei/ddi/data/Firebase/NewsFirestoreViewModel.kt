package ar.com.wolftei.ddi.data.Firebase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.DataClass.DatosCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsFirestoreViewModel
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(){
    private val _state: MutableState<List<DatosCard>> = mutableStateOf(listOf(DatosCard()))
    val state: State<List<DatosCard>> = _state


    fun getNews() = newsRepository.getNews().addOnSuccessListener{
        val list = mutableListOf<DatosCard>()
        for (document in it){
            val titulo = document.getString("titulo") ?: ""
            val subtitulo = document.getString("subtitulo") ?: ""
            val contenido = document.getString("contenido") ?: ""
            val comingsoon = document.getBoolean("comingsoon") ?: false
            list.add(DatosCard(titulo, subtitulo, contenido, comingsoon))
        }
        _state.value = list

    }
}