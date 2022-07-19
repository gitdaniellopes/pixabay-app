package br.com.pixabayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.pixabayapp.ui.navigation.Navigation
import br.com.pixabayapp.ui.theme.PixabayAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabayAppTheme {
                Navigation()
            }
        }
    }
}
