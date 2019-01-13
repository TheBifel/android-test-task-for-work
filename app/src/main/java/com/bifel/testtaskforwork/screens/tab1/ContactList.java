package com.bifel.testtaskforwork.screens.tab1;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ContactList {

    private ArrayAdapter<Contact> adapterContactList;
    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> contactsToRemove = new ArrayList<>();
    private List<Contact> contactsToAdd = new ArrayList<>();

    private ListView viewContactList;

    public ContactList(Context context, ListView listView, int listItemViewId) {
        adapterContactList = new ArrayAdapter<>(context, listItemViewId, contacts);
        viewContactList = listView;
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

    public void clearData(){
        contactsToAdd = new ArrayList<>();
        contactsToRemove = new ArrayList<>();
    }
}
