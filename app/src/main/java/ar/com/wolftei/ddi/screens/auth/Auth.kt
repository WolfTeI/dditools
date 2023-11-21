package ar.com.wolftei.ddi.screens.auth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.com.wolftei.ddi.DataClass.DatosUser
import ar.com.wolftei.ddi.R
import ar.com.wolftei.ddi.data.Firebase.UserAddFirestoreViewModel
import ar.com.wolftei.ddi.navigations.AppScreens
import ar.com.wolftei.ddi.navigations.FragmentSreens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun AuthScreen (
    navController: NavController,
    viewModel: AuthenticationViewModel = hiltViewModel(),
    dbUserViewModel: UserAddFirestoreViewModel = hiltViewModel()
) {

    val token = "1072430614059-qtjipfkb8m4gh9hm5ij3t6ja6ht98mal.apps.googleusercontent.com"
    val context = LocalContext.current


    val laucherForm = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInWitheGoogleCredential(credential) {
                val user = DatosUser(
                    email = account.email.toString(),
                    names = account.givenName.toString(),
                    lastName = account.familyName.toString(),
                    photo = account.photoUrl.toString()
                )
                dbUserViewModel.addNewUser(user)
                navController.navigate(AppScreens.Home.route)
            }
        } catch (ex: Exception) {
            Log.d("Catch", ex.toString())
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    val opciones = GoogleSignInOptions
                        .Builder(
                            GoogleSignInOptions.DEFAULT_SIGN_IN
                        )
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val googleSignInCliente = GoogleSignIn.getClient(context, opciones)
                    laucherForm.launch(googleSignInCliente.signInIntent)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_google_xhdpi),
                contentDescription = "Logo Google",
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
            )
            Text(
                text = "Login con Google",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
