package com.example.cn333

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Interface()
        }
    }

}

var random : Int = nextInt(1,1000)
var count = 0

@Composable
fun Interface(){

        val textState = remember { mutableStateOf(TextFieldValue()) }
        var textresult = remember { mutableStateOf("Guess your number")}
        var textcount = remember { mutableStateOf("")}

        var texthint = remember { mutableStateOf("show me answer")}


        fun showAns() {
            texthint.value = random.toString()

        }


        fun checkAns() {
            var ans = if (textState.value.text.isEmpty()){0} else {textState.value.text.toInt()}
            if (ans > random){
                count = count + 1
                textcount.value = ""
                textresult.value = "Guess lower ! "
            }
            else if (ans < random){
                count = count + 1
                textcount.value = ""
                textresult.value = "Guess higher ! "
            }
            else if (ans == random){
                count = count + 1
                textcount.value = "You guessed $count times"
                count = 0
                textresult.value = "Play again"
                random = nextInt( 1, 1000)

            }else{
                textresult.value = "Guess the answer !!!"
            }
        }




        Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

            ){
        Text(text = textcount.value)

        TextField(value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp, 50.dp),

        )

        Button(onClick = {checkAns()}) {
            Text(
                text = textresult.value,
            )
        }


            Button(onClick = {showAns()}) {
                Text(
                    text = texthint.value,
                )
            }




    }

}



@Preview
@Composable
fun Previewer(){
    Interface()
}
