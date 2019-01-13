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
import android.widget.ListView;

import com.bifel.testtaskforwork.R;

public class Tab1 extends Fragment {

    private ContactList contactList;
    public ContactRepository repo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        final View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        final Context context = view.getContext();
        final EditText txtContact = view.findViewById(R.id.txt_contact_name);
        final Button btnAddContact = view.findViewById(R.id.btn_add_contact);

        // TODO can use DI here
        if (repo == null) {
            repo = new ContactRepositorySQL(context);
        }
        contactList = new ContactList(context, (ListView) view.findViewById(R.id.contact_list), R.layout.contact_list_item);

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
        contactList.addAll(repo.getRecords());
    }

    @Override
    public void onPause() {
        super.onPause();
        repo.saveRecords(contactList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repo.close();
    }
}
