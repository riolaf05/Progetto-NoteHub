package it.unirc.pjam.notehub.model.post;

import java.util.ArrayList;

/**
 * Created by USER on 13/06/2017.
 */

public interface PostJSONDAO {
    public abstract ArrayList<Post> getPost(String jsonString);

}
