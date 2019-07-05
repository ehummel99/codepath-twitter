package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


@Parcel
public class Entities {

    //list attributes
    public String media_url;
    public boolean has_entity;

    //deserialize json
    public static Entities fromJSON(JSONObject jsonObject) {
        Entities entity = new Entities();

        entity.media_url = "";
        //extract and fill values
        try {
            JSONArray media = jsonObject.getJSONArray("media");
            JSONObject object = media.getJSONObject(0);
            entity.media_url = object.getString("media_url_https");
            entity.has_entity = true;
        } catch (JSONException e) {
            entity.has_entity = false;
        }
        return entity;
    }
}