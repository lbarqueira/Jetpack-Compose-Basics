package com.lbarqueira.jetpackcomposebasics

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.NEXUS_6
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
fun MyScreenContent(
    names: List<String> = List(1000) { "Hello Android $it" }
) {
    // state hoisting
    val counterState: MutableState<Int> = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(
            names = names,
            modifier = Modifier
                .weight(1f)
                .background(color = Color.Cyan)
        )
        Counter(
            count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
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

// Since consumers of Counter can be interested in the state, you can defer it
// to the caller completely by introducing a (count, updateCount) pair as parameters of Counter.
// In this way, Counter is hoisting its state.
@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    // val count: MutableState<Int> = remember { mutableStateOf(0) }
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I`ve been clicked $count times")
    }
}

@Preview(
    name = "MyScreen preview night mode",
    showSystemUi = true,
    device = NEXUS_6,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "MyScreen preview day mode",
    showSystemUi = true,
    device = NEXUS_6,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun DefaultPreview() {
    MyApp {
        MyApp(content = { MyScreenContent() })
    }
}


