package com.example.todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.ui.theme.ToDoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableStateOf(0) }  // Track selected navigation item
    val drawerState = rememberDrawerState(DrawerValue.Closed)  // Drawer state (open/close)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onItemClick = { selectedItem = it; scope.launch { drawerState.close() } })
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            selectedItem = selectedItem,
                            onItemSelected = { selectedItem = it },
                            onHamburgerClick = { scope.launch { drawerState.open() } }  // Open drawer on hamburger click

                        )
                    }
                ) {
                    // Show content based on selected item
                    when (selectedItem) {
                        0 -> HamburgerContent()
                        1 -> TasksContent()
                        2 -> CalendarContent()
                        3 -> MineContent()
                    }
                }
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    onHamburgerClick: () -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(60.dp),
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = { onHamburgerClick() },  // Open the drawer on hamburger click
            icon = { Icon(Icons.Default.Menu, contentDescription = "Hamburger") }
        )
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) },
            icon = { Icon(Icons.Default.CheckCircle, contentDescription = "Tasks") }
        )
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) },
            icon = { Icon(Icons.Default.CalendarToday, contentDescription = "Calendar") }
        )
        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = { onItemSelected(3) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Mine") }
        )
    }
}

@Composable
fun DrawerContent(onItemClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp) // Adjust width to one-third of the screen (standard size)
            .background(
                color = Color(0xFFEEEEEE),
                shape = RoundedCornerShape(topEnd = 16.dp)
            ) // Custom drawer background

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Hamburger Menu", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            // Add your menu items here
            Text(
                text = "Option 1",
                modifier = Modifier
                    .clickable { onItemClick(0) }
                    .padding(8.dp)
            )
            Text(
                text = "Option 2",
                modifier = Modifier
                    .clickable { onItemClick(1) }
                    .padding(8.dp)
            )
            Text(
                text = "Option 3",
                modifier = Modifier
                    .clickable { onItemClick(2) }
                    .padding(8.dp)
            )
            Text(
                text = "Option 4",
                modifier = Modifier
                    .clickable { onItemClick(3) }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun HamburgerContent() {
    Text("Hamburger Menu Content")
}

@Composable
fun TasksContent() {
    Text("Tasks Section")
}

@Composable
fun CalendarContent() {
    Text("Calendar Section")
}

@Composable
fun MineContent() {
    Text("Mine Section")
}