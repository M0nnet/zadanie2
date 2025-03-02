package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for NBA teams
data class NbaTeam(
    val name: String,
    val imageRes: Int,
    val championshipYear: Int,
    val coach: String,
    val detailedInfo: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbaTeamsApp()
        }
    }
}

@Composable
fun NbaTeamsApp() {
    val teams = listOf(
        NbaTeam("Denver Nuggets", R.drawable.denver, 2023, "Michael Malone", "Первый титул в истории клуба."),
        NbaTeam("Golden State Warriors", R.drawable.gsw, 2022, "Steve Kerr", "Четвёртый титул за 8 лет."),
        NbaTeam("Milwaukee Bucks", R.drawable.milawuakee, 2021, "Mike Budenholzer", "Первый титул с 1971 года."),
        NbaTeam("Los Angeles Lakers", R.drawable.lakers, 2020, "Frank Vogel", "17-й титул в истории клуба."),
        NbaTeam("Toronto Raptors", R.drawable.toronto, 2019, "Nick Nurse", "Первый титул в истории клуба."),
        NbaTeam("Golden State Warriors", R.drawable.gsw, 2018, "Steve Kerr", "Третий титул за 4 года."),
        NbaTeam("Golden State Warriors", R.drawable.gsw, 2017, "Steve Kerr", "Возвращение Кевина Дюранта."),
        NbaTeam("Cleveland Cavaliers", R.drawable.cleveland, 2016, "Tyronn Lue", "Леброн Джеймс вернул титул в Кливленд."),
        NbaTeam("Golden State Warriors", R.drawable.gsw, 2015, "Steve Kerr", "Начало династии ГСВ."),
        NbaTeam("San Antonio Spurs", R.drawable.spers, 2014, "Gregg Popovich", "Пятый титул Тима Данкана.")
    )

    var currentIndex by remember { mutableStateOf(0) }
    var showDetails by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val currentTeam = teams[currentIndex]

        Image(
            painter = painterResource(id = currentTeam.imageRes),
            contentDescription = "Team Logo",
            modifier = Modifier.size(200.dp).clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = currentTeam.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Чемпионство: ${currentTeam.championshipYear}", fontSize = 18.sp)
        Text(text = "Тренер: ${currentTeam.coach}", fontSize = 18.sp)

        if (showDetails) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = currentTeam.detailedInfo, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { if (currentIndex > 0) currentIndex-- }, enabled = currentIndex > 0) {
                Text("Предыдущий")
            }
            Button(onClick = { showDetails = !showDetails }) {
                Text(if (showDetails) "Скрыть" else "Подробнее")
            }
            Button(onClick = { if (currentIndex < teams.size - 1) currentIndex++ }, enabled = currentIndex < teams.size - 1) {
                Text("Следующий")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNbaTeamsApp() {
    NbaTeamsApp()
}