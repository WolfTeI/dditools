package ar.com.wolftei.ddi.config

import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ar.com.wolftei.ddi.DataClass.DatosUser
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject constructor (
    private var context: Context? = null,
    private val dataStore: DataStore<Preferences>
) {
    val email = stringPreferencesKey("email")

    val getEmail = dataStore.data.map{ preferences ->
        preferences[email] ?: ""
    }

   suspend fun LogOut() = dataStore.edit { preferences ->
       preferences[email] = ""
    }

    suspend fun saveSession(account: GoogleSignInAccount){
        dataStore.edit { preferences ->
            preferences[email] = account.email.toString()
        }
    }
}