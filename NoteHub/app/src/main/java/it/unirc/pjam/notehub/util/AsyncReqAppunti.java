package it.unirc.pjam.notehub.util;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import it.unirc.pjam.notehub.MainActivity;


public class AsyncReqAppunti extends AsyncTask<InternetConnection, Void , String> {

    private ProgressDialog progressDialog;
    private MainActivity mainActivity;
    private String dtitle;
    private String dcontent;
    private String url;



    public AsyncReqAppunti(MainActivity a, String dtitle, String dcontent, String url)
    {
        this.mainActivity=a;
        this.dtitle=dtitle;
        this.dcontent=dcontent;
        this.url=url;
    }



    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        mainActivity.runOnUiThread(new Runnable()
        {
            public void run()
            {
                progressDialog = ProgressDialog.show(mainActivity, dtitle, dcontent+"\n"+url, true);
            }
        });
    }


    protected void onPostExecute(String jsonString) {
        // TODO Auto-generated method stub
        super.onPostExecute(jsonString);
        //delegate.processFinish(jsonString);
        if(mainActivity!=null)
            mainActivity.setResult(jsonString);
        if(progressDialog!=null)
            progressDialog.dismiss();
    }



    @Override
    protected String doInBackground(InternetConnection... params) {

        InternetConnection internetConnection=params[0];
        String jsonString=internetConnection.getHttpSource();

        return jsonString;
    }




}
