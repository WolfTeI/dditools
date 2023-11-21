package ar.com.wolftei.ddi.screens.userConfig

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.ddi.screens.auth.AuthenticationViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserConfigScreen(
    authViewModel: AuthenticationViewModel = hiltViewModel(),
    ucViewModel: UserConfigViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    ucViewModel.getEmailInitial(authViewModel.userEmail())
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        val userName  = ucViewModel.state.value.names
        val userLastName  = ucViewModel.state.value.lastName
        val userEmail = ucViewModel.state.value.email
        val userPhoto = ucViewModel.state.value.photo
        val userPhone by ucViewModel.userPhone.observeAsState(initial = "")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(userPhoto)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = "Logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(80.dp)
                    .clip(RoundedCornerShape(50))
                    .padding(top = 10.dp, bottom = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .border(1.dp, color = Color.White, RoundedCornerShape(5.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            OutlinedTextField(value = userName, onValueChange = {ucViewModel.onUserNameChange(it)}, label = { Text(text = "Nombres") })
            Text(text = userName)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            OutlinedTextField(value = userLastName, onValueChange = {ucViewModel.onUserLastNameChange(it)}, label = { Text(text = "Apellidos") })
            Text(text = userLastName)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            OutlinedTextField(value = userEmail, onValueChange = {ucViewModel.onUserEmailChange(it)}, label = { Text(text = "E-mail") })
            Text(text = userEmail, modifier = Modifier.border(1.dp, color = Color.White, RoundedCornerShape(5.dp)).padding(vertical= 5.dp, horizontal = 30.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = userPhone, onValueChange = {ucViewModel.onUserPhoneChange(it)}, label = { Text(text = "Teléfono") })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Demo_ExposedDropdownMenuBox()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            coffeeDrinks.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
