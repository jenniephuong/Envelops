package com.example.envelops.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationBar() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Gray,
                modifier = Modifier.height(70.dp)
            ) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Screens.Envelopes.screen) {
                            popUpTo(0)
                        }
                    }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.List
                        navController.navigate(Screens.Transactions.screen) {
                            popUpTo(0)
                        }
                    }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.List) Color.White else Color.DarkGray
                    )
                }

                Box(
                    modifier = Modifier
                      .weight(1f)
                      .padding(16.dp), contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(onClick = {
                        navController.navigate(Screens.NewTransaction.screen)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Yellow)
                    }
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Info
                        navController.navigate(Screens.Reports.screen) {
                            popUpTo(0)
                        }
                    }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Info) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.AccountCircle
                        navController.navigate(Screens.Account.screen) {
                            popUpTo(0)
                        }
                    }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.AccountCircle) Color.White else Color.DarkGray
                    )
                }

            }
        }
    ) { paddingValues ->
        SetupNavGraph(navController, paddingValues)
    }
}

@Preview
@Composable
fun PreviewNavigationBar() {
    NavigationBar()
}