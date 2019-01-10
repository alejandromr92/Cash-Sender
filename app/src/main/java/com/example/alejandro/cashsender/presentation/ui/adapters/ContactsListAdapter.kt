package com.example.alejandro.cashsender.presentation.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.extensions.inflate
import com.example.alejandro.cashsender.presentation.extensions.load
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsListAdapter(
    private val items: List<Contact>
): RecyclerView.Adapter<ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder =
        ContactHolder(parent.inflate(R.layout.item_contact))

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) =
            holder.bind(items[position])
}

class ContactHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView){
    fun bind(contact: Contact) = with(itemView) {
        contact_name.text = contact.name
        contact_phone_number.text = contact.phoneNumber
        // TODO cargar foto si tiene
        contact_avatar.load(contact.thumbnail)

    }
}