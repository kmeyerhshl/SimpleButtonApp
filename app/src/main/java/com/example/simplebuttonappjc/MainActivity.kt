package com.example.simplebuttonappjc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplebuttonappjc.ui.theme.SimpleButtonAppJCTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleButtonAppJCTheme {
                SimpleButton()
            }
        }
    }
}

private val TAG = "MainActivity"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleButton() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var clickcounter by remember { mutableStateOf(0) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerpadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    clickcounter++
                    Log.i(TAG, "SimpleButton wurde $clickcounter mal gedrückt")
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "SimpleButton wurde gedrückt",
                            actionLabel = "Action",
                            duration = SnackbarDuration.Indefinite
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                //Reaktion auf angeklickte Aktion
                            }

                            SnackbarResult.Dismissed -> {
                                //Reaktion auf Schließen der Snackbar
                            }
                        }
                        snackbarHostState.showSnackbar("SimpleButton wurde gedrückt")
                    }
                },
                border = BorderStroke(2.dp, Color.Blue),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue
                )
            ) {
                Text(
                    text = "Drück Mich"
                )
            }
            Text(
                text = "Button wurde $clickcounter mal gedrückt"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleButtonPreview() {
    SimpleButtonAppJCTheme {
        SimpleButton()
    }
}