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
    private ArrayAdapter<String> adapterContactList;
    private List<String> contacts = new ArrayList<>();
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
        return adapterContactList.getItem(position);
    }

    public void addAll(Collection<String> collection) {
        adapterContactList.addAll(collection);
    }

    public void add(String contact) {
        adapterContactList.add(contact);
    }

    public void removeContact(int position) {
        contacts.remove(position);
        adapterContactList.notifyDataSetChanged();
    }

    public List<String> getContacts() {
        return contacts;
    }
}
