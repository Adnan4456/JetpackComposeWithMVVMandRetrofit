package com.example.jetpackcomposewithmvvmandretorfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.ui.theme.JetpackComposewithMVVMandRetorfitTheme
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
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EachRow(post: Post){

    //layout of recyclerview item.
    Card(onClick = {},
    modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
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

    val posts = mainViewModel.post.collectAsLazyPagingItems()
    LazyColumn{
        items(posts) { post ->
            post?.let {
                EachRow(post = post)
            }
        }
        when (posts.loadState.append){

            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> {
                item {

                }
            }
        }


        when (posts.loadState.refresh){

            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ){
                        LoadingItem()
                    }
                }
            }
            is LoadState.Error -> {
                item {

                    ErrorItem(message = "Network error occurred.")

                }
            }
        }
    }
}

@Composable
fun LoadingItem(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressAnimated()
    }
}

@Composable
fun ErrorItem(message: String) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(42.dp)
                    .height(42.dp),
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                color = Color.White,
                text = message,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(CenterVertically)
            )
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
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .padding(8.dp),
        progress = progressAnimation,
        strokeWidth = 10.dp)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    JetpackComposewithMVVMandRetorfitTheme {


    }
}