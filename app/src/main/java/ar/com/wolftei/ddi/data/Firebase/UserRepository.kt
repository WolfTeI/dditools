package ar.com.wolftei.ddi.data.Firebase

import android.util.Log
import ar.com.wolftei.ddi.DataClass.DatosUser
import ar.com.wolftei.ddi.di.AppModule.provideFirestoreInstance
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val users: FirebaseFirestore
){

    fun addUser(user: DatosUser){
        try {
            val userAdd = hashMapOf(
                "email" to user.email,
                "names" to user.names,
                "lastName" to user.lastName,
                "photo" to user.photo
            )
            users.collection("users").document(user.email).set(userAdd)
            Log.d("UserRepository", "El usuario se agrego correctamente")
        } catch (e: Exception){
            Log.d("UserRepository", "El error $e")
        }
    }
    //Funcion para tarer los datos del usuario en el repositorio para pasarlo al viewmodel
    fun getUser(email: String) = users.collection("users").document(email).get()

}