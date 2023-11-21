package ar.com.wolftei.ddi.screens.userConfig

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.DataClass.DatosUser
import ar.com.wolftei.ddi.data.Firebase.UserGetFirestoreViewModel
import ar.com.wolftei.ddi.data.Firebase.UserRepository
import ar.com.wolftei.ddi.screens.auth.AuthenticationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserConfigViewModel
@Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state: MutableState<DatosUser> = mutableStateOf(DatosUser())
    val state: State<DatosUser> = _state

    private val _userPhone = MutableLiveData<String>()
    val userPhone: LiveData<String> = _userPhone

    fun getEmailInitial(email: String) {
        getUser(email)
    }


    fun getUser(email: String) {
        userRepository.getUser(email).addOnSuccessListener {
            val email = it.getString("email")
            val names = it.getString("names")
            val lastName = it.getString("lastName")
            val photo = it.getString("photo")

            _state.value = DatosUser(
                email = email.toString(),
                names = names.toString(),
                lastName = lastName.toString(),
                photo = photo.toString()
            )
        }
    }


    fun onUserPhoneChange(userPhone: String) {
        _userPhone.value = userPhone
    }
}