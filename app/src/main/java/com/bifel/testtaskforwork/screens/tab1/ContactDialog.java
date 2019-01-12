package com.bifel.testtaskforwork.screens.tab1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bifel.testtaskforwork.R;


public class ContactDialog extends Dialog {

    private int position;

    @SuppressLint("ClickableViewAccessibility")
    public ContactDialog(final Context context, final ContactList contactList) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pop_up_contact_dialog);

        final Button btnRemove = findViewById(R.id.btn_remove_contact);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, contactList.getContact(position) + " removed", Toast.LENGTH_SHORT).show();
                contactList.removeContact(position);
                hide();
            }
        });
    }

    public void show(int position) {
        super.show();
        this.position = position;
    }
}