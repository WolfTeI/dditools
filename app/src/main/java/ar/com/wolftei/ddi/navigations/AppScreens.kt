package ar.com.wolftei.ddi.navigations

sealed class AppScreens(val route: String){
    object Splash: AppScreens("splash")
    object Auth: AppScreens("auth")
    object Home: AppScreens("home")
    object Camara: AppScreens("camara")

//    Configuraciones
    object Config: AppScreens("config")


}
