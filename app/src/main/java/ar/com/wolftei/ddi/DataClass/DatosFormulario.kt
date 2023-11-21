package ar.com.wolftei.ddi.DataClass

data class DatosFormulario(
    val name: String,
    val onChange: (String)->Unit,
    var value: String
)

data class DatosFormularioCheck(
    val name: String,
    val onChange: (Boolean)->Unit,
    val value: Boolean
)
