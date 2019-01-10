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
import android.widget.Toast;

import com.bifel.testtaskforwork.R;

public class Tab1 extends Fragment {

    private ContactList contactList;
    private DB db;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        db = new DB();

        final View view = inflater.inflate(R.layout.fragment_layout_1, container, false);
        final Context context = view.getContext();
        final EditText txtContact = view.findViewById(R.id.txtContact);
        final Button btnAddContact = view.findViewById(R.id.btnAddContact);

        contactList = new ContactList(view, context);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactList.addAll(db.getRecords());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.saveRecords(contactList.getContacts());
    }
}
