package it.unirc.pjam.notehub.model.appunto;

import java.util.ArrayList;

public interface AppuntoJSONDAO {
	public abstract  ArrayList<Appunto> getAppunto(String jsonString);
}
