package com.bifel.testtaskforwork.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bifel.testtaskforwork.R;

public class Tab2 extends Fragment {

    private static Tab2 INSTANCE = new Tab2();

    public static Tab2 getInstance() {
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        return inflater.inflate(R.layout.fragment_layout_2, container, false);
    }
}
