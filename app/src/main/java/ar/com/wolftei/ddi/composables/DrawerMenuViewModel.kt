package ar.com.wolftei.ddi.composables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.DataClass.DatosUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerMenuViewModel @Inject constructor(): ViewModel() {

    private val _nombrePerfil: MutableLiveData<String> = MutableLiveData()
    val nombrePerfil: LiveData<String> = _nombrePerfil

    private val _emailPerfil: MutableLiveData<String> = MutableLiveData()
    val emailPerfil: LiveData<String> = _emailPerfil

    private val _fotoPerfil: MutableLiveData<String> = MutableLiveData()
    val fotoPerfil: LiveData<String> = _fotoPerfil

    fun setPerfil(perfil: DatosUser){
        _nombrePerfil.value = "${perfil.names} ${perfil.lastName}"
        _emailPerfil.value = perfil.email
        _fotoPerfil.value = perfil.photo
    }

}