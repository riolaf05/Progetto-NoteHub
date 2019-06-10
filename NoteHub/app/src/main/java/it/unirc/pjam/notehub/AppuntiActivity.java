package it.unirc.pjam.notehub;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class AppuntiActivity extends ListActivity {
    public final static String EXTRA_MESSAGE="valore";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appunti);
        getActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String[] appuntoListString = intent.getStringArrayExtra(MainActivity.EXTRA_USERS);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_appunti, R.id.textView,appuntoListString));

         final ListView lista = (ListView) findViewById(android.R.id.list);
         lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 // qui dentro stabilisco cosa fare dopo il click
                 final String valore = (String) parent.getItemAtPosition(position);
                 view.setSelected(true);
                 Log.d("List", "Ho cliccato sull'elemento "+ id +" nella posizione " + position+ " con titolo " + valore);

                 try {
                     Class download = Class.forName("it.unirc.pjam.notehub.DownloadActivity");
                     Intent intent1 = new Intent(AppuntiActivity.this, download);
                     intent1.putExtra(EXTRA_MESSAGE, valore);
                     startActivity(intent1);
                 }
                 catch (ClassNotFoundException e){
                     e.printStackTrace();

                 }

             }
         });
        }

    }
















