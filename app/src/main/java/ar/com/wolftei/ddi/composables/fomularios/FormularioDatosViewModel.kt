package ar.com.wolftei.ddi.composables.fomularios

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.Functions.clipBoard
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class FormularioDatosViewModel @Inject constructor(): ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre : LiveData<String> = _nombre

    private val _nacionalidad = MutableLiveData<String>()
    val nacionalidad : LiveData<String> = _nacionalidad

    private val _edad = MutableLiveData<String>()
    val edad : LiveData<String> = _edad

    private val _estadoCivil = MutableLiveData<String>()
    val estadoCivil : LiveData<String> = _estadoCivil

    private val _profesion = MutableLiveData<String>()
    val profesion : LiveData<String> = _profesion

    private val _leeYEscribe = MutableLiveData<Boolean>()
    val leeYEscribe : LiveData<Boolean> = _leeYEscribe

    private val _domicilio = MutableLiveData<String>()
    val domicilio : LiveData<String> = _domicilio

    private val _dni = MutableLiveData<String>()
    val dni : LiveData<String> = _dni

    private val _fechaNacimiento = MutableLiveData<String>()
    val fechaNacimiento : LiveData<String> = _fechaNacimiento

    private val _lugarNacimiento = MutableLiveData<String>()
    val lugarNacimiento : LiveData<String> = _lugarNacimiento

    private val _nombrePadre = MutableLiveData<String>()
    val nombrePadre : LiveData<String> = _nombrePadre

    private val _padreVive = MutableLiveData<Boolean>()
    val padreVive : LiveData<Boolean> = _padreVive

    private val _nombreMadre = MutableLiveData<String>()
    val nombreMadre : LiveData<String> = _nombreMadre

    private val _madreVive = MutableLiveData<Boolean>()
    val madreVive : LiveData<Boolean> = _madreVive

    fun datePicker(context: Context): DatePickerDialog {
        val year: Int
        val month: Int
        val day: Int
        val mCalendar = Calendar.getInstance()
        year = mCalendar.get(Calendar.YEAR)
        month = mCalendar.get(Calendar.MONTH)
        day = mCalendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            context,
            { _: DatePicker?, year: Int, month: Int, day: Int ->
                _fechaNacimiento.value = "$day/${month + 1}/$year"
            }, year, month, day

        )

        return datePicker
    }

    fun onChangeValue(nombre: String, nacionalidad: String, edad: String, estadoCivil: String, profesion: String, leeYEscribe: Boolean, domicilio: String, dni: String, fechaNacimiento: String, lugarNacimiento: String, nombrePadre: String, padreVive: Boolean, nombreMadre: String, madreVive: Boolean){
        _nombre.value = nombre
        _nacionalidad.value = nacionalidad
        _edad.value = edad
        _estadoCivil.value = estadoCivil
        _profesion.value = profesion
        _leeYEscribe.value = leeYEscribe
        _domicilio.value = domicilio
        _dni.value = dni
        _lugarNacimiento.value = lugarNacimiento
        _nombrePadre.value = nombrePadre
        _padreVive.value = padreVive
        _nombreMadre.value = nombreMadre
        _madreVive.value = madreVive

    }

    fun onChangeFechaNacimiento(fechaNacimiento: String){
        _fechaNacimiento.value = fechaNacimiento
    }

    fun identificar(context: Context) {

        Log.d("EstadoCivil", estadoCivil.value.toString())
        var leerYEscribir = "no sabe leer ni escribir"
        var padreViveStr = "(F)"
        var madreViveStr = "(F)"

        if (leeYEscribe.value!!) {
            leerYEscribir = "instruido"
        }
        if (padreVive.value!!) {
            padreViveStr = "(V)"
        }
        if (madreVive.value!!) {
            madreViveStr = "(V)"
        }

        val datos =
            "${nombre.value}, de nacionalidad ${nacionalidad.value}, de ${edad.value} años de edad, ${leerYEscribir}, estado civil ${estadoCivil.value}, de ocupación ${profesion.value}, domiciliado en la calle ${domicilio.value}, DNI Nro. ${dni.value}, nacido el ${fechaNacimiento.value}, en ${lugarNacimiento.value}, hijo de ${nombrePadre.value} $padreViveStr y de ${nombreMadre.value} ${madreViveStr}."
        clipBoard(context, datos)
    }




}