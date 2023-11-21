package ar.com.wolftei.ddi.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.ExtractoScreen
import ar.com.wolftei.ddi.screens.home.fragmentsHome.InicioScreen
import ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.CcarIdentificadoScreen
import ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.CcarParaderoScreen
import ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.hechos.CcarHechoScreen
import ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.CcarSalidaScreen
import ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.procedimientos.CcarProcedimientoScreen

@Composable
fun FragmentNavigation(navControllerFrag: NavHostController){

    NavHost(navController = navControllerFrag, startDestination = FragmentSreens.Inicio.route){
        composable(route = FragmentSreens.Inicio.route){
            InicioScreen()
        }
        composable(route = FragmentSreens.Extracto.route){
            ExtractoScreen(navControllerFrag)
        }
        composable(route = FragmentSreens.CcarSalida.route){
            CcarSalidaScreen(navControllerFrag)
        }
        composable(route = FragmentSreens.CcarHecho.route){
            CcarHechoScreen(navControllerFrag)
        }
        composable(route = FragmentSreens.CcarProcedimiento.route){
            CcarProcedimientoScreen(navControllerFrag)
        }
        composable(route = FragmentSreens.CcarParadero.route){
            CcarParaderoScreen(navControllerFrag)
        }
        composable(route = FragmentSreens.CcarIdentificado.route){
            CcarIdentificadoScreen(navControllerFrag)
        }
    }
}