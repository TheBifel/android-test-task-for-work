package com.bifel.testtaskforwork.screens.tab3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class FileHelper {

    private FileHelper() {}

    public static List<File> unpackZip(String path, String zipName) {
        InputStream is;
        ZipInputStream zis;
        List<File> files = new ArrayList<>();

        try {
            String filename;
            is = new FileInputStream(path + zipName);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                filename = ze.getName();

                File unzipToThisFile = new File(path + filename);
                FileOutputStream fout = new FileOutputStream(unzipToThisFile);

                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
                files.add(unzipToThisFile);
            }

            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File(path + zipName).delete();

        return files;
    }

    public static List<Bitmap> transformFilesToImgBitmap(List<File> files) {
        List<Bitmap> imagines = new ArrayList<>();
        for (File file : files) {
            String name = file.getName();
            if (name.endsWith("png") || name.endsWith("jpg")){
                imagines.add(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        }
        return imagines;
    }

    public static Bitmap compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG , 20, out);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
    }

    public static List<File> getDownloadedFiles(){
        File folder = new File(FileDownloader.FOLDER_NAME);
        List<File> res = new ArrayList<>();
        if ((!folder.exists() || !folder.isDirectory())){
            return res;
        }
        for (final File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                res.add(file);
            }
        }
        return res;
    }

}
