package com.pattiti.plos.desktop

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
        Text("Welcome to Personal Life OS", modifier = Modifier.fillMaxSize())
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "PLOS Client") {
        App()
    }
}
