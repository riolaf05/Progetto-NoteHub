package it.unirc.pjam.notehub;


import android.os.Bundle;


import it.unirc.pjam.notehub.model.appunto.Appunto;
import it.unirc.pjam.notehub.model.appunto.AppuntoJSONDAOImpl;
import it.unirc.pjam.notehub.model.post.Post;
import it.unirc.pjam.notehub.model.post.PostJSONDAOImpl;
import it.unirc.pjam.notehub.util.AsyncReqAppunti;
import it.unirc.pjam.notehub.util.AsyncReqPost;
import it.unirc.pjam.notehub.util.InternetConnection;


import java.util.ArrayList;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "asyncTask Activity";

    public final static String EXTRA_USERS = "it.unirc.pjam.notehub.model.APPUNTI";
    public final static String EXTRA_POST = "it.unirc.pjam.notehub.model.post.POST";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Button button=(Button)findViewById(R.id.button);
        Button button2=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openAppunti();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openPost();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appunti:
                openAppunti();
                break;
            case R.id.post:
                openPost();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openPost(){

        InternetConnection internetConnection = new InternetConnection(getString(R.string.urlPost));
        AsyncReqPost asyncReqPostPost = new AsyncReqPost(this, getString(R.string.running), getString(R.string.conser), getString(R.string.urlPost));
        asyncReqPostPost.execute(internetConnection);




    }


    public void openAppunti(){

        InternetConnection internetConnection = new InternetConnection(getString(R.string.url));
        AsyncReqAppunti asyncReqAppunti = new AsyncReqAppunti(this, getString(R.string.running), getString(R.string.conser), getString(R.string.url));
        asyncReqAppunti.execute(internetConnection);



    }
    public void setResult(String jsonString){

        AppuntoJSONDAOImpl appuntoContractImpl=new AppuntoJSONDAOImpl();
        ArrayList<Appunto> listaAppunti1=appuntoContractImpl.getAppunto(jsonString);

        String[] appuntoListString;
        if(listaAppunti1.size()==0){
            appuntoListString=new String[1];
            appuntoListString[0]=getString(R.string.nodata);
        } else{
            appuntoListString=new String[listaAppunti1.size()];
            for(int i=0; i<listaAppunti1.size(); i++){
                appuntoListString[i]=listaAppunti1.get(i).toString();
                Log.d(TAG,appuntoListString[i]);
            }
        }
        Intent intent = new Intent(this, AppuntiActivity.class);
        intent.putExtra(EXTRA_USERS,appuntoListString);
        startActivity(intent);
    }



    public void setResultPost(String jsonString1){

        PostJSONDAOImpl postContractImpl=new PostJSONDAOImpl();
        ArrayList<Post> listaPost=postContractImpl.getPost(jsonString1);

        String[] postListString;
        if(listaPost.size()==0){
            postListString=new String[1];
            postListString[0]=getString(R.string.nodata);
        } else{
            postListString=new String[listaPost.size()];
            for(int i=0; i<listaPost.size(); i++){
                postListString[i]=listaPost.get(i).toString();
                Log.d(TAG,postListString[i]);
            }
        }
        Intent intent1 = new Intent(this, PostActivity.class);
        intent1.putExtra(EXTRA_POST,postListString);
        startActivity(intent1);
    }


}
