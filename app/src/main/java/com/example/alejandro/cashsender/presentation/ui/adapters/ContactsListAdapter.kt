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
    private val items: List<Contact>,
    private val listener: OnContactSelected?,
    private val amount: Double = 0.0
): RecyclerView.Adapter<ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder =
        ContactHolder(parent.inflate(R.layout.item_contact))

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) =
            holder.bind(items[position], listener?: null, amount)
}

class ContactHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView){
    fun bind(contact: Contact, listener: OnContactSelected?, amount: Double) = with(itemView){
        contact_name.text = contact.name
        contact_phone_number.text = contact.phoneNumber

        if (contact.thumbnail.isNotBlank()){
            contact_avatar.load(contact.thumbnail)
        }

        if (listener != null){
            contact_selector.setOnClickListener{
                if (contact_selector.isChecked){
                    listener.onContactSelected(contact)
                } else {
                    listener.onContactUnselected(contact)
                }
            }
        } else {
            contact_selector.visibility = View.INVISIBLE
            contact_amount.visibility = View.VISIBLE
            contact_amount.text = amount.toString()
        }
    }
}

interface OnContactSelected{
    fun onContactSelected(contact: Contact)
    fun onContactUnselected(contact: Contact)
}