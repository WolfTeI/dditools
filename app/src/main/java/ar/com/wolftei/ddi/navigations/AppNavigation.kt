package ar.com.wolftei.ddi.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.com.wolftei.ddi.screens.CameraScreen
import ar.com.wolftei.ddi.screens.home.HomeScreen
import ar.com.wolftei.ddi.screens.auth.AuthScreen
import ar.com.wolftei.ddi.screens.SplashSreen
import ar.com.wolftei.ddi.screens.userConfig.UserConfigurationScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Splash.route){
        composable(route = AppScreens.Splash.route){
            SplashSreen(navController)
        }
        composable(route = AppScreens.Auth.route){
            AuthScreen(navController)
        }
        composable(route = AppScreens.Home.route){
            HomeScreen(navController)
        }
        composable(route = AppScreens.Camara.route){
            CameraScreen(navController)
        }
//        Configuraciones
        composable(route = AppScreens.Config.route){
            UserConfigurationScreen(navController)
        }
    }
}