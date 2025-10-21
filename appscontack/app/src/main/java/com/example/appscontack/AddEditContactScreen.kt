package com.example.appscontack.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appscontack.data.Contact
import com.example.appscontack.data.ContactData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreen(
    navController: NavController,
    contactIndex: Int?
) {
    val context = LocalContext.current
    val isEditMode = contactIndex != null
    val title = if (isEditMode) "Edit Contact" else "Add Contact"


    val existingContact = if (isEditMode) {
        remember { ContactData.getContact(contactIndex!!) }
    } else {
        null
    }


    var name by remember { mutableStateOf(existingContact?.name ?: "") } // [cite: 18]
    var address by remember { mutableStateOf(existingContact?.address ?: "") } // [cite: 19]
    var phone by remember { mutableStateOf(existingContact?.phone ?: "") } // [cite: 20]
    var email by remember { mutableStateOf(existingContact?.email ?: "") } // [cite: 21]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val wordCount = address.split(" ").filter { it.isNotBlank() }.size
                    if (wordCount < 5) { // [cite: 11, 24]
                        Toast.makeText(
                            context,
                            "Address must be at least 5 words",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val contact = Contact(name, address, phone, email)


                        if (isEditMode) {

                            ContactData.updateContact(contactIndex!!, contact)
                        } else {

                            ContactData.addContact(contact)
                        }


                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditMode) "Update Contact" else "Add Contact")
            }
        }
    }
}