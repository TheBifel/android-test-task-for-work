package com.bifel.testtaskforwork.screens.tab2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bifel.testtaskforwork.R;
import com.lespinside.simplepanorama.view.SphericalView;
import com.panoramagl.utils.PLUtils;

public class Tab2 extends Fragment {

    //    private RotationManager rotationManager;
    private SphericalView sphericalView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        View view = inflater.inflate(R.layout.fragment_layout_2, container, false);
//        Context context = view.getContext();
//        rotationManager = new RotationManager(context);

        sphericalView = view.findViewById(R.id.spherical_view);
        sphericalView.setPanorama(PLUtils.getBitmap(view.getContext(), R.drawable.pic1), false);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
//        rotationManager.start();
        sphericalView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        rotationManager.stop();
        sphericalView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sphericalView.onDestroy();
    }
}
