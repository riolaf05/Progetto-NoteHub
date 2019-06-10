package it.unirc.pjam.notehub.model.appunto;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AppuntoJSONDAOImpl implements AppuntoJSONDAO{
	private static final String TAG="JSON Activity"; 


	public ArrayList<Appunto> getAppunto(String jsonString)
	{
		ArrayList<Appunto> listaAppunti=new ArrayList<>();

		JSONArray jsonArray=null;
		try {			
			JSONObject jsonObject=new JSONObject(jsonString);
			jsonArray = jsonObject.getJSONArray(AppuntoJSONContract.nomeArray);
			for (int i=0; i<jsonArray.length(); i++) {
				jsonObject=jsonArray.getJSONObject(i);
				String fileFileName=jsonObject.getString(AppuntoJSONContract.AppuntoObject.fileFileName);
				listaAppunti.add(new Appunto(fileFileName));
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage()+e.getCause());
		}
		return listaAppunti;
	}
}
