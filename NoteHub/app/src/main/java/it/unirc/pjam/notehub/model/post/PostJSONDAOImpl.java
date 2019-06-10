package it.unirc.pjam.notehub.model.post;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostJSONDAOImpl implements PostJSONDAO {
    private static final String TAG="JSON Activity";


    public ArrayList<Post> getPost(String jsonString)
    {
        ArrayList<Post> listaPost=new ArrayList<>();

        JSONArray jsonArray=null;
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray(PostJSONContract.nomeArray);
            for (int i=0; i<jsonArray.length(); i++) {
                jsonObject=jsonArray.getJSONObject(i);
                String titolo=jsonObject.getString(PostJSONContract.PostObject.titolo);
                String contenuto=jsonObject.getString(PostJSONContract.PostObject.contenuto);
                listaPost.add(new Post(titolo,contenuto));
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage()+e.getCause());
        }
        return listaPost;
    }
}
