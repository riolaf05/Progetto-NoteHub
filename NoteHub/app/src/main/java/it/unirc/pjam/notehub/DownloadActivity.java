package it.unirc.pjam.notehub;


import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import it.unirc.pjam.notehub.util.FileDownloader;


public class DownloadActivity extends Activity {

    // Progress dialog
    private ProgressDialog pDialog;
    private String nomeFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Intent intent=getIntent();
        String valore=intent.getStringExtra(AppuntiActivity.EXTRA_MESSAGE);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Attendere...");
        pDialog.setCancelable(false);
        this.nomeFile=valore;
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(nomeFile);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);

    }


    public void downloadPDF(View v)
    {
        Log.d( "file","nome file " + nomeFile);
        new DownloadFile().execute("http://10.0.2.2:8080/ProgettoWeb_PJAM/APPUNTI/"+nomeFile, nomeFile);

    }

    public void viewPDF(View v)
    {
        if (isExternalStorageReadable()) {


        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/PDFDOWNLOAD/" + nomeFile);  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf"); /* pdfIntent.setType("file/*"); non funziona  */
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); /* .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); non funziona */
/*
        try{
            //Toast.makeText(MainActivity.this, "Il file c'Ã¨!!!", Toast.LENGTH_SHORT).show();
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(MainActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        } */

        /* usando l'intent chooser.. */
        Intent intent = Intent.createChooser(pdfIntent, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }
        }


    }

    private class DownloadFile extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {

            if (!isExternalStorageWritable())
                return null;

            String fileUrl = strings[0];
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
            String fileName = strings[1];
// ->five-point-someone-chetan-bhagat_ebook.pdf
            String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            File folder = new File(extStorageDirectory, "PDFDOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hidepDialog();
            Toast.makeText(getApplicationContext(), "PDF scaricato correttamente!", Toast.LENGTH_SHORT).show();

            Log.d("Download completato", "----------");
        }
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public boolean isExternalStorageReadable() {

        //Necessario a partire da API 23
        String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        int permsRequestCode = 200;
        if (Build.VERSION.SDK_INT> Build.VERSION_CODES.LOLLIPOP_MR1)// > API 22
            requestPermissions(perms,permsRequestCode);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        Log.d("Extfile","Impossibile leggere dalla memoria esterna!");
        return false;
    }

    public boolean isExternalStorageWritable() {


        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        Log.d("Extfile","Impossibile scrivere sulla memoria esterna");
        return false;
    }

}