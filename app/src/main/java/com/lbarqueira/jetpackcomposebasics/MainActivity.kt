package com.lbarqueira.jetpackcomposebasics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbarqueira.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(content = { MyScreenContent() })
        }
    }
}


// Inside your function, you define all of the shared configuration you want your container
// to provide and then invoke the passed children Composable. In this case, you want
// to apply a MaterialTheme and a yellow surface, and then call content().
// Making container composable functions is a good practice that improves readability and
// encourages reusing code.
@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetpackComposeBasicsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
    Column {
        for (name in names) {
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

// Note: Composable functions are Kotlin functions that are marked with the @Composable annotation,
// as you can see:
@Composable
fun Greeting(name: String) {
    // Modifier parameters tell a UI element how to lay out, display,
    // or behave within its parent layout. Modifiers are regular Kotlin objects.
    Surface(color = Color.Yellow) {
        Text(text = "Hello $name!", modifier = Modifier.padding(all = 24.dp))
    }

}


@Preview(name = "MyScreen preview")
@Composable
fun DefaultPreview() {
    MyApp {
        MyApp(content = { MyScreenContent() })
    }
}


