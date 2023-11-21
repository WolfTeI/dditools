package ar.com.wolftei.ddi.data.Firebase

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.DataClass.DatosUser
import ar.com.wolftei.ddi.DataClass.UserActiveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserGetFirestoreViewModel
@Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    private val _state : MutableState<DatosUser> = mutableStateOf(DatosUser())
    val state: State<DatosUser> = _state


    fun getUser(email: String){
        userRepository.getUser(email).addOnSuccessListener {
            val email = it.getString("email")
            val names = it.getString("names")
            val lastName = it.getString("lastName")
            val photo = it.getString("photo")

            _state.value = DatosUser(email = email.toString(), names = names.toString(), lastName = lastName.toString(), photo = photo.toString())
        }



    }
}