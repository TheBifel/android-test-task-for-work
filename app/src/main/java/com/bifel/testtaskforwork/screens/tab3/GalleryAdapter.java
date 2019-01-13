package com.bifel.testtaskforwork.screens.tab3;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bifel.testtaskforwork.R;

import java.util.List;

import static com.bifel.testtaskforwork.screens.tab3.FileHelper.compressBitmap;

public final class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<Bitmap> galleryList;

    public void setGalleryList(List<Bitmap> galleryList) {
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.img_cell_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.img.setImageBitmap(compressBitmap(galleryList.get(i)));
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
        }
    }
}