package com.bifel.testtaskforwork.screens.tab1;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bifel.testtaskforwork.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContactList {
    private ArrayAdapter<Contact> adapterContactList;
    private List<Contact> contacts = new ArrayList<>();

    private List<Contact> contactsToRemove = new ArrayList<>();
    private List<Contact> contactsToAdd = new ArrayList<>();

    private ListView viewContactList;

    public ContactList(View view, final Context context) {
        adapterContactList = new ArrayAdapter<>(context, R.layout.list_item, contacts);
        viewContactList = view.findViewById(R.id.contactList);
        viewContactList.setAdapter(adapterContactList);
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener listener) {
        viewContactList.setOnItemLongClickListener(listener);
    }

    public String getContact(int position) {
        return adapterContactList.getItem(position).toString();
    }

    public void addAll(Collection<Contact> collection) {
        contacts.addAll(collection);
    }

    public void add(String name) {
        Contact contact = new Contact(name,0);
        contactsToAdd.add(contact);
        adapterContactList.add(contact);
    }

    public void removeContact(int position) {
        contactsToRemove.add(contacts.get(position));
        contacts.remove(position);
        adapterContactList.notifyDataSetChanged();
    }

    public List<Contact> getContactsToRemove() {
        return contactsToRemove;
    }

    public List<Contact> getContactsToAdd() {
        return contactsToAdd;
    }

    public void applyChanges(){
        contactsToAdd = new ArrayList<>();
        contactsToRemove = new ArrayList<>();
    }
}
