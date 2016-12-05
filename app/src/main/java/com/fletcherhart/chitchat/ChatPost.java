package com.fletcherhart.chitchat;

import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Fletcher on 12/3/2016.
 */

public class ChatPost {
    public String mLikes, mDislikes;
    public String mPost;
    public String mPostTime;
    private UUID mId;

    //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
    //textView.setText(currentDateTimeString);

    public String getTime() {return mPostTime;}
    public String getLikes() {return mLikes;}
    public String getDislikes() {return mDislikes;}
    public String getText() {return mPost;}

    public void setPostText(String text) {mPost = text;}
    public void setLikes(String votes) {mLikes = votes;}
    public void setDislikes(String votes) {mDislikes = votes;}
    public void setTime(String text) {mPostTime = text;}
    public UUID getId() {
        return mId;
    }

}
