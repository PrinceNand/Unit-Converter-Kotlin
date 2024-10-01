package com.project.unitconverterkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.unitconverterkotlin.ui.theme.UnitConverterKotlinTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        name = "Unit Converter!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(name: String, modifier: Modifier){

    // states
//    val inputValue = remember { mutableStateOf("") }    // required .value to get the value
    var inputValue by remember { mutableStateOf("") }  // doesn't required .value
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var  iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(0.01) }


    // Unit converter function to convert value
    fun convertUnit() {
        // ?: elvish operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        val context = LocalContext.current;
        Text(text = name)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {inputValue = it},
            label = {Text("Enter Value!")},
            modifier = Modifier.padding(16.dp))

        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        iExpanded = false;
                        inputUnit = "Centimeter"
                        conversionFactor = 0.01
                        convertUnit()
//                        Toast.makeText(context, "Centimeter Selected!", Toast.LENGTH_SHORT).show()
                    })
                    DropdownMenuItem(text = { Text("Meter") }, onClick = {
                        iExpanded = false;
                        inputUnit = "Meter"
                        conversionFactor = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        iExpanded = false;
                        inputUnit = "Feet"
                        conversionFactor = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        iExpanded = false;
                        inputUnit = "Millimeter"
                        conversionFactor = 0.001
                        convertUnit()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text("Meter") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = { /*TODO*/ })
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun UnitConverterPreview() {
    UnitConverter(
        name = "Hey Android!",
        modifier = Modifier.padding(16.dp)
    )}