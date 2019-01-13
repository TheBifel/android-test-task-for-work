package com.bifel.testtaskforwork.screens.tab3;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.bifel.testtaskforwork.screens.tab3.FileHelper.unpackZip;

final class  FileDownloader extends AsyncTask<String, String, String> {

    private final DownloadCompleteListener downloadCompleteListener;
    private final DownloadingListener downloadingListener;
    private static final String FILE_NAME = "file.zip";

    public static final String FOLDER_NAME = Environment.getExternalStorageDirectory() + File.separator + "testForWork" + File.separator;

    public FileDownloader(DownloadCompleteListener downloadCompleteListener,
                          DownloadingListener downloadingListener) {

        this.downloadCompleteListener = downloadCompleteListener;
        this.downloadingListener = downloadingListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            downloadingListener.showDownloading(true);

            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            File directory = new File(FOLDER_NAME);

            if (!directory.exists()) {
                //noinspection ResultOfMethodCallIgnored
                directory.mkdir();
            }

            OutputStream output = new FileOutputStream(FOLDER_NAME + FILE_NAME);

            byte data[] = new byte[1024];
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.close();
            input.close();

            return "Downloaded at: " + FOLDER_NAME + FILE_NAME;

        } catch (Exception e) {
            downloadingListener.showDownloading(false);
            Log.e("Error: ", e.getMessage());
        }

        return "Something went wrong";
    }

    @Override
    protected void onPostExecute(String message) {
        downloadCompleteListener.onDownloadComplete(unpackZip(FOLDER_NAME, FILE_NAME));
        downloadingListener.showDownloading(false);
    }

    interface DownloadCompleteListener {
        void onDownloadComplete(File[] files);
    }

    interface DownloadingListener {
        void showDownloading(boolean isDownloading);
    }
}