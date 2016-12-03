package com.fletcherhart.chitchat;

import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Fletcher on 12/3/2016.
 */

public class ChatPost {
    public int mVotes;
    public String mPost;
    public String mPostTime;
    private UUID mId;

    //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
    //textView.setText(currentDateTimeString);

    public String getTime() {return mPostTime;}
    public int getVotes() {return mVotes;}

    public void setPostText(String text) {mPost = text;}
    public UUID getId() {
        return mId;
    }

}
