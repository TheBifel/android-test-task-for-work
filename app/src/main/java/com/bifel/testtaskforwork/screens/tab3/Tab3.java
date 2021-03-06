package com.bifel.testtaskforwork.screens.tab3;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bifel.testtaskforwork.R;
import com.bifel.testtaskforwork.screens.tab3.FileDownloader.DownloadingListener;
import com.bifel.testtaskforwork.screens.tab3.FileDownloader.PhotoPreparedListener;

import java.util.ArrayList;
import java.util.List;

import static com.bifel.testtaskforwork.screens.tab3.FileHelper.getDownloadedFiles;
import static com.bifel.testtaskforwork.screens.tab3.FileHelper.transformFilesToImgBitmap;

public final class Tab3 extends Fragment {

    private PhotoPreparedListener photoPreparedListener;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        final View view = inflater.inflate(R.layout.fragment_zip_gallery, container, false);

        recyclerView = view.findViewById(R.id.gallery);

        final Button btnDownload = view.findViewById(R.id.btn_download_zip);
        final EditText txtURL = view.findViewById(R.id.txt_url_for_zip);
        final View progressBar = view.findViewById(R.id.progress_bar_download);
        final Context context = view.getContext();
        final GalleryAdapter adapter = new GalleryAdapter();
        final Handler handler = new Handler(context.getMainLooper());

        photoPreparedListener = new PhotoPreparedListener() {
            @Override
            public void onPhotoPrepared(final List<Bitmap> photos) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setGalleryList(photos);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        };

        final DownloadingListener downloadingListener = new DownloadingListener() {
            @Override
            public void showDownloading(final boolean isDownloading) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isDownloading) {
                            btnDownload.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            btnDownload.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        };

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FileDownloader(photoPreparedListener, downloadingListener)
                        .execute(txtURL.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                photoPreparedListener.onPhotoPrepared(transformFilesToImgBitmap(getDownloadedFiles()));
            }
        });
        thread.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
