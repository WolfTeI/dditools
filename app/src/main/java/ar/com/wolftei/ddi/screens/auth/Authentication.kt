package ar.com.wolftei.ddi.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel
@Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

//    private val auth: FirebaseAuth = Firebase.auth
    fun signInWitheGoogleCredential(credential: AuthCredential, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("TASK", task.result.user?.email.toString())
                            home()
                        }
                    }
                    .addOnFailureListener {
                        Log.d("TASK", "Login fail")
                    }
            } catch (ex: Exception) {
                Log.d("msjLogin", ex.toString())
            }
        }

    fun isUserLogged() = auth.currentUser != null
    fun userEmail() = auth.currentUser?.email.toString()
    fun signOut() = auth.signOut()

}