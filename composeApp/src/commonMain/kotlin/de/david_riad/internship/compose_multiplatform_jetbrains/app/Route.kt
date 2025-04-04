package de.david_riad.internship.compose_multiplatform_jetbrains.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object ProductGraph : Route

    @Serializable
    data object ProductList : Route

}