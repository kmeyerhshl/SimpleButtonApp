package com.example.simplebuttonappjc

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplebuttonappjc.ui.theme.SimpleButtonAppJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleButtonAppJCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpleButton()
                }
            }
        }
    }
}

private val TAG = "MainActivity"
private var clickcounter = 0

@Composable
fun SimpleButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                clickcounter++
                Log.i(TAG, "SimpleButton wurde $clickcounter mal gedrückt")
                Toast.makeText(context, "SimpleButton wurde $clickcounter mal gedrückt", Toast.LENGTH_LONG).show()
            },
            border = BorderStroke(2.dp, Color.Blue),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Blue
            )
        ) {
            Text(
                text = stringResource(R.string.simpleButtonText)
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