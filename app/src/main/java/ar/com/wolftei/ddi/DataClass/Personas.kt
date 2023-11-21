package ar.com.wolftei.ddi.DataClass

data class Personas(
    val nombre: String,
    val nacionalidad: String,
    val edad: String,
    val estadoCivil: String,
    val sabeLeerYEscribir: Boolean,
    val ocupacion: String,
    val domicilio: String,
    val dni: String,
    val fechaNacimiento: String,
    val lugarNacimiento: String,
    val padre: String,
    val padreVive: Boolean,
    val madre: String,
    val madreVive: Boolean
){
    constructor(): this(
        "",
        "",
        "",
        "",
        false,
        "",
        "",
        "",
        "",
        "",
        "",
        false,
        "",
        false
    )
}
