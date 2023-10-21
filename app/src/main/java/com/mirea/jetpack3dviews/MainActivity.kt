package com.mirea.jetpack3dviews

import android.annotation.SuppressLint
import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.mirea.jetpack3dviews.ui.theme.Jetpack3DViewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var glSurfaceView = GLSurfaceView(this)
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack3DViewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductPage(glSurfaceView)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPage(glSurfaceView: GLSurfaceView) {

    var rendererSet = false
    var quantity by remember { mutableStateOf(1) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Name") },

                )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()){

            AndroidView(
                factory = {
                    glSurfaceView.apply {
                        setEGLContextClientVersion(2)
                        setRenderer(ObjectRenderer())
                        rendererSet = true
                    }
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Product Name",
//                    style = MaterialTheme.typography.labelLarge,
//                    modifier = Modifier.padding(16.dp)
//                )
//                Text(
//                    text = "Product Description",
//                    style = MaterialTheme.typography.labelMedium,
//                    modifier = Modifier.padding(16.dp)
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//            item {
//                var totalPrice = quantity * 29.99
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "Price: $$totalPrice",
//                        style = MaterialTheme.typography.labelSmall,
//                        modifier = Modifier.weight(1f)
//                    )
//                    BasicTextField(
//                        value = quantity.toString(),
//                        onValueChange = {
//                            quantity = it.toIntOrNull() ?: 0
//                        },
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            imeAction = ImeAction.Done,
//                            keyboardType = KeyboardType.Number
//                        ),
//                        keyboardActions = KeyboardActions(
//                            onDone = { // Обработка нажатия клавиши "Готово"
//                                // Дополнительная логика здесь, если необходимо
//                            }
//                        ),
//                        singleLine = true,
//                        modifier = Modifier
//                            .width(50.dp)
//                            .background(Color.White)
//                            .clip(MaterialTheme.shapes.small)
//                            .padding(8.dp)
//                            .fillMaxHeight()
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    IconButton(
//                        onClick = {
//                            quantity++
//                        }
//                    ) {
//                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                    }
//                    IconButton(
//                        onClick = {
//                            if (quantity > 1) {
//                                quantity--
//                            }
//                        }
//                    ) {
//                        Icon(imageVector = Icons.Default.Remove, contentDescription = null)
//                    }
//                }
//            }
//            item {
//                Button(
//                    onClick = { /* Действие по нажатию на кнопку "Добавить в корзину" */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                ) {
//                    Text(text = "Добавить в корзину")
//                }
//            }
//        }
    }
}
