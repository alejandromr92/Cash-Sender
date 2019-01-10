package com.example.alejandro.cashsender

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.extensions.AmountIntent
import com.example.alejandro.cashsender.presentation.presenter.GetMarvelCharactersPresenter
import com.example.alejandro.cashsender.presentation.presenter.GetPhoneContactsPresenter
import com.example.alejandro.cashsender.presentation.presenter.impl.GetMarvelCharactersPresenterImpl
import com.example.alejandro.cashsender.presentation.presenter.impl.GetPhoneContactsPresenterImpl
import com.example.alejandro.cashsender.presentation.ui.activities.BaseActivity
import com.example.alejandro.cashsender.presentation.ui.adapters.ContactsListAdapter
import com.example.alejandro.cashsender.presentation.ui.adapters.OnContactSelected
import com.example.alejandro.cashsender.utils.LoggerUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.ArrayList

class ContactsActivity : BaseActivity(),
    GetMarvelCharactersPresenter.View, GetPhoneContactsPresenter.View,
    OnContactSelected{

    private var getMarvelCharactersPresenter: GetMarvelCharactersPresenter? = null

    private var getPhoneContactsPresenter: GetPhoneContactsPresenter? = null

    private var contactsListAdapter: ContactsListAdapter? = null

    private var contactsList: MutableList<Contact>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_contacts
        super.onCreate(savedInstanceState)
    }

    override fun initializePresenters() {
        super.initializePresenters()

        this.getMarvelCharactersPresenter = GetMarvelCharactersPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this
        )

        this.getPhoneContactsPresenter = GetPhoneContactsPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this
        )
    }

    override fun configViews() {
        super.configViews()

        this.configNextBtn()

        this.configRecyclerView()

        this.configErrorMessage()
    }

    private fun configNextBtn(){
        confirm_contacts_selected_btn.setOnClickListener {
            navigateToAmount()
        }
    }

    private fun configRecyclerView() {
        this.contactsList = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        contacts_selection_list.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(contacts_selection_list.context, layoutManager.orientation)
        contacts_selection_list.addItemDecoration(dividerItemDecoration)

        this.contactsListAdapter = ContactsListAdapter(contactsList as ArrayList<Contact>, this)
        contacts_selection_list.adapter = contactsListAdapter
    }

    private fun configErrorMessage(){
        //TODO
    }

    private fun displayContent(infoObtained: Boolean){
        // TODO
    }

    override fun loadData() {
        super.loadData()

        this.getMarvelCharactersPresenter!!.getMarvelCharacters()
    }

    /**
     * Listeners
     */

    private fun navigateToAmount(){
        startActivity(this.AmountIntent(contactsSelectedList as MutableList))
    }

    override fun onContactSelected(contact: Contact) {
        contactsSelectedList!!.add(contact)
    }

    override fun onContactUnselected(contact: Contact) {
        contactsSelectedList!!.remove(contact)
    }

    /**
     * Permissions
     */

    fun isPermissionGranted(permission:String):Boolean =
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    fun requestPermission(permission: String) =
        ActivityCompat.requestPermissions(this,
            listOf(permission).toTypedArray(),
            400) // TODO Add constant

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            400 ->{ // TODO add constant
                val isPermissionsGranted = isPermissionGranted(Manifest.permission.READ_CONTACTS)

                if(isPermissionsGranted){
                    // Do the task now
                    this.getPhoneContactsPresenter!!.getPhoneContacts(contentResolver)
                }else{
                    // TODO add rationale
                }
                return
            }
        }
    }

    /**
     * Callbacks
     */

    override fun onMarvelCharactersRetrieved(marvelCharactersList: List<Contact>) {

        for (character in marvelCharactersList){
            if (!this.contactsList!!.contains(character)){
                this.contactsList!!.add(character)
            }
        }

        if (isPermissionGranted(Manifest.permission.READ_CONTACTS)){
            this.getPhoneContactsPresenter!!.getPhoneContacts(contentResolver)
        } else {
            requestPermission(Manifest.permission.READ_CONTACTS)
        }

    }

    override fun onMarvelCharactersRetrievingError() {
        LoggerUtils.logMessage("CHARACTERS", "Error")
    }

    override fun onPhoneContactsRetrieved(phoneContacts: List<Contact>) {
        for (contact in phoneContacts){
            if (!this.contactsList!!.contains(contact)){
                this.contactsList!!.add(contact)
            }
        }
        contactsList!!.sortBy { it.name }
        contactsListAdapter!!.notifyDataSetChanged()
    }

    override fun onPhoneContactsRetrievingError() {
        LoggerUtils.logMessage("PHONE CONTACTS", "Error")
    }
}
