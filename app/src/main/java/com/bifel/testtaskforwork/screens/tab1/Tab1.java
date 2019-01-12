package com.bifel.testtaskforwork.screens.tab1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.bifel.testtaskforwork.R;

public class Tab1 extends Fragment {

    private ContactList contactList;
    private DB db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        final View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        final Context context = view.getContext();
        final EditText txtContact = view.findViewById(R.id.txt_contact_name);
        final Button btnAddContact = view.findViewById(R.id.btn_add_contact);

        if (db == null) {
            db = new DB(context);
        }
        if (contactList == null) {
            contactList = new ContactList(view, context);
        }

        final ContactDialog contactDialog = new ContactDialog(context, contactList);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.add(txtContact.getText().toString());
                txtContact.setText("");
            }
        });

        contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contactDialog.show(position);
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        contactList.addAll(db.getRecords());
    }

    @Override
    public void onPause() {
        super.onPause();
        db.saveRecords(contactList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.onDestroy();
    }
}
