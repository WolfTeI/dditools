package ar.com.wolftei.ddi.composables.fomularios

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.Functions.clipBoard
import ar.com.wolftei.ddi.Functions.fechaActual
import ar.com.wolftei.ddi.Functions.horaActual
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormularioSalidaViewModel @Inject constructor(): ViewModel() {



    private val _personal = MutableLiveData<String>()
    val personal : LiveData<String> = _personal


    private val _checkPersonal: MutableLiveData<Boolean> = MutableLiveData()
    val checkPersonal : LiveData<Boolean> = _checkPersonal


    private val _movil: MutableLiveData<String> = MutableLiveData()
    val movil : LiveData<String> = _movil


    private val _jurisdiccion: MutableLiveData<String> = MutableLiveData()
    val jurisdiccion : LiveData<String> = _jurisdiccion


    private val _motivo: MutableLiveData<String> = MutableLiveData()
    val motivo : LiveData<String> = _motivo


    private val _firma: MutableLiveData<String> = MutableLiveData()
    val firma : LiveData<String> = _firma

    fun onValueChange(personal: String, checkPersonal: Boolean, movil: String, jurisdiccion: String, motivo: String, firma: String) {
        _personal.value = personal
        _checkPersonal.value = checkPersonal
        _movil.value = movil
        _jurisdiccion.value = jurisdiccion
        _motivo.value = motivo
        _firma.value = firma
    }

    fun salida(context: Context){
        var personalACargo = ","
        if (checkPersonal.value == true) {
            personalACargo = " con personal a cargo,"
        }
        val extracto = "*${fechaActual()}*" +
                "\n*DDI MORON CCA.* Sale ${horaActual()} Hs., ${personal.value?.trim()}$personalACargo en el móvil ${movil.value?.trim()} a jurisdicción de ${jurisdiccion.value?.trim()}, fines ${motivo.value?.trim()}." +
                "\n*FDO ${firma.value?.uppercase()?.trim()}*"


        clipBoard(context, extracto)
    }



}