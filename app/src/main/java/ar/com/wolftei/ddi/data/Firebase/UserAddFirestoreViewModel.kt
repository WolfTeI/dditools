package ar.com.wolftei.ddi.data.Firebase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.com.wolftei.ddi.DataClass.DatosUser
import ar.com.wolftei.ddi.DataClass.DatosUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserAddFirestoreViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _state: MutableState<DatosUserState> = mutableStateOf(DatosUserState())
    val state : State<DatosUserState>
        get() = _state

    private val _user: MutableState<DatosUser> = mutableStateOf(DatosUser())
    val user: State<DatosUser>
        get() = _user

    fun addNewUser(user: DatosUser){
        userRepository.addUser(user)
    }

}