package com.example.appscontack.data

data class Contact(
    val name: String,
    val address: String,
    val phone: String,
    val email: String
)

class ContactData {
    companion object {

        val contactList = mutableListOf<Contact>()


        fun addContact(contact: Contact) {
            contactList.add(contact)
        }


        fun getContact(index: Int): Contact? {
            return contactList.getOrNull(index)
        }


        fun updateContact(index: Int, contact: Contact) {
            if (index >= 0 && index < contactList.size) {
                contactList[index] = contact
            }
        }
    }
}