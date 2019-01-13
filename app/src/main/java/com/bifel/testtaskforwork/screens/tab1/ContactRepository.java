package com.bifel.testtaskforwork.screens.tab1;

import java.util.List;

public interface ContactRepository {

    List<Contact> getRecords();

    void saveRecords(ContactList contactList);

    void close();
}
