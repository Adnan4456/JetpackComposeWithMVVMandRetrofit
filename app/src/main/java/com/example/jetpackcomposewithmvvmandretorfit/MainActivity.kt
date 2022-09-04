package com.example.jetpackcomposewithmvvmandretorfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.ui.theme.JetpackComposewithMVVMandRetorfitTheme
import com.example.jetpackcomposewithmvvmandretorfit.utils.ApiState
import com.example.jetpackcomposewithmvvmandretorfit.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposewithMVVMandRetorfitTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Post")
                            },
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Menu , contentDescription = "menu")
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Notifications , contentDescription = "notification")
                                }
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Search , contentDescription = "search")
                                }
                            }
                        )
                    },
                    //second parameter floating action button
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            IconButton(onClick = {}) {
                                Icon(Icons.Filled.Add , contentDescription = "add")
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End

                ) {
                    // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    getData(mainViewModel = mainViewModel)
                 }
                }
            // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    getData(mainViewModel = mainViewModel)
//
//                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EachRow(post: Post){

    //layout of recyclerview item.

    Card(onClick = {},
    modifier = Modifier.padding(8.dp),
    elevation = 5.dp,
    shape = RoundedCornerShape(5.dp))
    {
        Text(text = post.body, color = Color.Black,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        fontStyle = FontStyle.Italic)
    }
}

@Composable
fun getData(mainViewModel: MainViewModel){

    when (val result = mainViewModel.response.value){
        is ApiState.Success ->{
            LazyColumn{
                items(result.data){ response ->
                    EachRow(post = response)
                }
            }
        }
        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }
        is ApiState.Loading ->{
            CircularProgressAnimated()
        }
        is ApiState.Empty ->{
            CircularProgressAnimated()
        }
    }
}

@Composable
fun CircularProgressAnimated(){
    val progressValue = 0.95f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimation by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue =progressValue ,
        animationSpec = infiniteRepeatable(animation = tween(400)))

    CircularProgressIndicator(
        modifier = Modifier.size(40.dp),
        progress = progressAnimation,
        strokeWidth = 10.dp)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposewithMVVMandRetorfitTheme {


    }
}