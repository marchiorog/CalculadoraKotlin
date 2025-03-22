package com.example.calculadorakotlin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorView() {
    var displayText by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = displayText,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )

        Column {
            Row {
                NumberButton("1") { appendNumber("1", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                NumberButton("2") { appendNumber("2", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                NumberButton("3") { appendNumber("3", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                OperationButton("+") { operation = "+"; displayText = "0" }
            }
            Row {
                NumberButton("4") { appendNumber("4", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                NumberButton("5") { appendNumber("5", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                NumberButton("6") { appendNumber("6", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                OperationButton("-") { operation = "-"; displayText = "0" }
            }
            Row {
                NumberButton("7") { appendNumber("7", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                NumberButton("8") { appendNumber("8", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                NumberButton("9") { appendNumber("9", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                OperationButton("*") { operation = "*"; displayText = "0" }
            }
            Row {
                NumberButton("C") {
                    // Limpar tudo
                    displayText = "0"
                    firstNumber = ""
                    secondNumber = ""
                    operation = ""
                }
                NumberButton("=") {
                    // Lógica para calcular o resultado
                    if (firstNumber.isNotEmpty() && secondNumber.isNotEmpty() && operation.isNotEmpty()) {
                        val result = calculate(firstNumber.toFloat(), secondNumber.toFloat(), operation)
                        displayText = result.toString()
                        firstNumber = ""
                        secondNumber = ""
                        operation = ""
                    }
                }
                NumberButton("0") { appendNumber("0", displayText, firstNumber, secondNumber, operation) { updatedText, fNum, sNum ->
                    displayText = updatedText
                    firstNumber = fNum
                    secondNumber = sNum
                } }
                OperationButton("/") { operation = "/"; displayText = "0" }
            }
        }
    }
}

@Composable
fun NumberButton(number: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .width(64.dp)
    ) {
        Text(text = number)
    }
}

@Composable
fun OperationButton(operation: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .width(64.dp)
    ) {
        Text(text = operation)
    }
}

fun appendNumber(
    number: String,
    displayText: String,
    firstNumber: String,
    secondNumber: String,
    operation: String,
    onUpdate: (String, String, String) -> Unit
) {
    if (operation.isEmpty()) {
        val updatedNumber = if (firstNumber.isEmpty() && number == "0") "0" else firstNumber + number
        onUpdate(updatedNumber, updatedNumber, secondNumber)
    } else {
        val updatedNumber = if (secondNumber.isEmpty() && number == "0") "0" else secondNumber + number
        onUpdate(updatedNumber, firstNumber, updatedNumber)
    }
}

// Função para calcular o resultado
fun calculate(num1: Float, num2: Float, operation: String): Float {
    return when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> if (num2 != 0f) num1 / num2 else 0f
        else -> 0f
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorView() {
    CalculatorView()
}
