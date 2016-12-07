package com.fletcherhart.chitchat;

import android.net.Uri;
import android.util.Log;

//import org.apache.http.client.HttpResponseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchPost {

    private static final String TAG = "FetchPost";

    private static final String API_KEY = "champlainrocks1878";

    public byte[] getUrlBytes(String urlSpec) throws IOException {         //taken unedited from PhotoGallery code in chapter 23
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<ChatPost> fetchItems() {

        List<ChatPost> items = new ArrayList<>();

        try {
            String url = Uri.parse("https://www.stepoutnyc.com/chitchat")
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("limit", "40")
                    .appendQueryParameter("skip", Integer.toString(0))
                    .build().toString();
            System.out.println(url);
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<ChatPost> items, JSONObject jsonBody)
            throws IOException, JSONException {

        JSONArray postsJsonArray = jsonBody.getJSONArray("messages");

        for (int i = 0; i < postsJsonArray.length(); i++) {
            JSONObject postJsonObject = postsJsonArray.getJSONObject(i);

            ChatPost item = new ChatPost();
           // item.setId(postJsonObject.getString("id"));
            item.setPostText(postJsonObject.getString("message"));
            item.setLikes(postJsonObject.getString("likes"));
            item.setDislikes(postJsonObject.getString("dislikes"));
            item.setTime(postJsonObject.getString("date"));

            //item.setUrl(postJsonObject.getString("url_s"));
            items.add(item);
        }
    }

}
