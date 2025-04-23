package com.moviles.vinilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moviles.vinilos.ui.theme.VinilosTheme
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VinilosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val screenwidth = LocalConfiguration.current.screenWidthDp
                    val buttonwidth = (screenwidth * 0.8f).dp
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color(red = 244, green = 244, blue = 243))
                            .fillMaxSize()
                    ){
                        Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Logo()
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B7500)),
                                shape = RoundedCornerShape(0.dp),
                                modifier = Modifier
                                    .width(buttonwidth)
                                    .height(80.dp)
                            ){
                                Text(
                                    text = "Albumes",
                                    color=Color.White,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Logo(){
    Image(
        painter= painterResource(R.drawable.logo),
        contentDescription = "App Logo",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .height(300.dp)
            .padding(top=35.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VinilosTheme {
        Greeting("Android")
    }
}