package ar.com.wolftei.ddi.DataClass

data class DatosUserState(
    val isLoading: Boolean = false,
    val user: DatosUser? = null,
    val error: String = ""
)
