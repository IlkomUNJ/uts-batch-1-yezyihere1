package com.example.appscontack.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appscontack.data.Contact
import com.example.appscontack.data.ContactData
import com.example.appscontack.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListContactScreen(navController: NavController) {
    // Mengambil daftar kontak dari companion object [cite: 32]
    val contacts = remember { ContactData.contactList }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") }, // App Bar dengan teks "Dashboard" [cite: 15]
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Pindah ke Add Contact Screen saat FAB diklik [cite: 13]
                    navController.navigate(Routes.ADD_EDIT_SCREEN)
                },
                modifier = Modifier // FAB di kanan bawah [cite: 12]
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Menampilkan daftar kontak [cite: 9]
            itemsIndexed(contacts) { index, contact ->
                ContactItem(
                    contact = contact,
                    onLongClick = {
                        // Long press akan membuka Edit Contact Screen [cite: 28]
                        // Mengirim indeks kontak yang akan diedit [cite: 29]
                        navController.navigate("${Routes.ADD_EDIT_SCREEN}?${Routes.ARG_CONTACT_INDEX}=$index")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactItem(contact: Contact, onLongClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = contact.address, style = MaterialTheme.typography.bodyMedium)
        }
    }
}