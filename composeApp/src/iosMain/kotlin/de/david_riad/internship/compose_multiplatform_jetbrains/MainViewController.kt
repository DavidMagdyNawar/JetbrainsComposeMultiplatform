package de.david_riad.internship.compose_multiplatform_jetbrains

import androidx.compose.ui.window.ComposeUIViewController
import de.david_riad.internship.compose_multiplatform_jetbrains.app.App
import de.david_riad.internship.compose_multiplatform_jetbrains.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }