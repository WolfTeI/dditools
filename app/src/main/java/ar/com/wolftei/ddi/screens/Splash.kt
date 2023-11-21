package ar.com.wolftei.ddi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.com.wolftei.ddi.R
import ar.com.wolftei.ddi.navigations.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashSreen(navController: NavController){
    LaunchedEffect(key1 = true ){
        delay(2000)
        navController.popBackStack()
        navController.navigate(AppScreens.Home.route)
    }
    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo_tools",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(250.dp)
        )
        Text(text = "Tools", color = MaterialTheme.colorScheme.onBackground)

    }
}
