package com.example.sankofa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sankofa.Splash.SplashScreen
import com.example.sankofa.navigation.Routes
import com.example.sankofa.ui.theme.SankofaTheme
import com.example.sankofa.view.CrudF
import com.example.sankofa.view.CrudM
import com.example.sankofa.view.Futebol
import com.example.sankofa.view.Menu
import com.example.sankofa.view.CriarCorrida

import androidx.navigation.NavController
import com.example.sankofa.view.Corres
import com.example.sankofa.view.CriarDebate
import com.example.sankofa.view.CriarVolei
import com.example.sankofa.view.ScreenDebate
import com.example.sankofa.view.ScreenVolei

import com.example.sankofa.view.Screencorrida

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SankofaTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.SPLASH
                ) {
                    composable(Routes.SPLASH) {
                        SplashScreen {
                            navController.navigate(Routes.MENU) {
                                popUpTo(Routes.SPLASH) { inclusive = true }
                            }
                        }
                    }

                    composable(Routes.MENU) {
                        Menu(navController)
                    }


                    composable(Routes.VOLEI) {
                        ScreenVolei(navController)
                    }

                    composable(Routes.SCREEENCORRIDA) {
                        Screencorrida(navController)
                    }

                    composable(Routes.FUTEBOL) {
                        Futebol(navController)
                    }

                    composable(Routes.CORRES) {
                        Corres(navController)
                    }

                    composable(Routes.CRUDF) {
                        CrudF()
                    }

                    composable(Routes.CRUDM) {
                        CrudM()
                    }

                    composable(Routes.CRIAR_CORRIDA) {
                        CriarCorrida(navController)
                    }

                    composable(Routes.CRIAR_VOLEI) {
                        CriarVolei(navController)
                    }

                    composable(Routes.CRIARDEBATE) {
                        CriarDebate(navController)
                    }

                    composable(Routes.SCREENDEBATE) {
                        ScreenDebate(navController)
                    }

                }
            }
        }
    }
}
