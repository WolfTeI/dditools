package ar.com.wolftei.ddi.navigations

sealed class FragmentSreens(val route: String) {

    object Inicio: FragmentSreens("inicio")
    object Extracto: FragmentSreens("extracto")
    object CcarSalida: FragmentSreens("ccarSalida")
    object CcarHecho: FragmentSreens("ccarHecho")
    object CcarParadero: FragmentSreens("ccarParadero")
    object CcarProcedimiento: FragmentSreens("ccarProcedimiento")
    object CcarIdentificado: FragmentSreens("ccarIdentificado")
}