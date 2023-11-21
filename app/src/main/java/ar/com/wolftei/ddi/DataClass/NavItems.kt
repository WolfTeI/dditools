package ar.com.wolftei.ddi.DataClass

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import ar.com.wolftei.ddi.navigations.AppScreens

data class NavItems (
    var name: String,
    var principalIcon: ImageVector,
    var secondaryIcon: ImageVector,
    var route: String


)