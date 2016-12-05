package com.fletcherhart.chitchat;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatLab {
    private static ChatLab sChatLab;

    private ArrayList<ChatPost> mPosts;

    public static ChatLab get(Context context) {
        if (sChatLab == null) {
            sChatLab = new ChatLab(context);
        }
        return sChatLab;
    }

    private ChatLab(Context context) {
        mPosts = new ArrayList<>();

        FetchPost post = new FetchPost();

        for (int i = 0; i < 100; i++) {
           // ChatPost post = new ChatPost();
           // post.setPostText("Post #" + i);
           // post.setTime("Noon");
            //post.setVotes(3);
           // mPosts.add(post);
        }
    }

    public List<ChatPost> getPosts() {
        return mPosts;
    }

    public ChatPost getPost(UUID id) {
        for (ChatPost post : mPosts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
    }
}
