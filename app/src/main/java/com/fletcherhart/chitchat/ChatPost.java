package com.fletcherhart.chitchat;

import java.util.Comparator;

/**
 * Created by Fletcher on 12/3/2016.
 */

public class ChatPost implements Comparator<ChatPost> {
    public String mLikes, mDislikes;
    public String mPost;
    public String mPostTime;
    private String mId;
    private int likes;

    //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
    //textView.setText(currentDateTimeString);

    public String getTime() {return mPostTime;}
    public String getLikes() {return mLikes;}
    public String getDislikes() {return mDislikes;}
    public String getText() {return mPost;}
    public String getId() {return mId;}


    public void setId(String id) {mId = id;}
    public void setlikes(int votes) {likes = votes;}
    public int getlikes() {return likes;}


    public void setPostText(String text) {mPost = text;}
    public void setLikes(String votes) {mLikes = votes;}
    public void setDislikes(String votes) {mDislikes = votes;}
    public void setTime(String text) {mPostTime = text;}

    public int compare(ChatPost x, ChatPost y)
    {
        int comparison = compare(x.getlikes(), y.getlikes());

        return comparison != 0 ? comparison
                : compare(x.getlikes(), y.getlikes());
    }

    private static int compare(long a, long b) {
        return a < b ? -1
                : a > b ? 1
                : 0;
    }

}
