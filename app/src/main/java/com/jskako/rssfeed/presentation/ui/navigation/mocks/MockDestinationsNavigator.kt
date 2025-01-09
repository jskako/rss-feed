package com.jskako.rssfeed.presentation.ui.navigation.mocks

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.ramcosta.composedestinations.navigation.DestinationsNavOptionsBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.RouteOrDirection

val mockNavigator = object : DestinationsNavigator {
    override fun navigate(direction: Direction, builder: DestinationsNavOptionsBuilder.() -> Unit) {
        // mocked behavior
    }

    override fun clearBackStack(route: RouteOrDirection): Boolean {
        return true
    }

    override fun getBackStackEntry(route: RouteOrDirection): NavBackStackEntry? {
        return null
    }

    override fun navigate(
        direction: Direction,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) {
        // mocked behavior
    }

    override fun navigateUp(): Boolean {
        return true
    }

    override fun popBackStack(): Boolean {
        return true
    }

    override fun popBackStack(
        route: RouteOrDirection,
        inclusive: Boolean,
        saveState: Boolean
    ): Boolean {
        return true
    }
}