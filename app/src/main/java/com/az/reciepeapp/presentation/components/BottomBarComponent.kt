package com.az.reciepeapp.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun myBottomBar() {

    BottomNavigation(elevation = 12.dp) {
        BottomNavigationItem(selected = true, onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = "") })

        BottomNavigationItem(selected = false, onClick = { },
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "") })

        BottomNavigationItem(selected = false, onClick = { },
            icon = { Icon(Icons.Default.Search, contentDescription = "") })

        BottomNavigationItem(selected = false, onClick = { },
            icon = { Icon(Icons.Default.Settings, contentDescription = "") })
    }
}