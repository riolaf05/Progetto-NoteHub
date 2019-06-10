package it.unirc.pjam.notehub;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class PostActivity extends ListActivity {
    public final static String EXTRA_MESSAGE="valorePost";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getActionBar().setDisplayHomeAsUpEnabled(true);



    }
    protected void onResume() {
        super.onResume();

        Intent intent1 = getIntent();
        String[] postListString = intent1.getStringArrayExtra(MainActivity.EXTRA_POST);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_post, R.id.textView1, postListString));

    }

}
