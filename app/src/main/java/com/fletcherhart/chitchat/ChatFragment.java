package com.fletcherhart.chitchat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Fletcher on 11/29/2016.
 */

public class ChatFragment extends Fragment {

    private RecyclerView mRecycler;
    private ChatAdapter mAdapter;
    private List<ChatPost> mItems = new ArrayList<>();
    private EditText mNewMessage;
    private Button mSort;
    private SwipeRefreshLayout swipeContainer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new FetchItemsTask().execute();

                new CreatePostTask().execute();
                swipeContainer.setRefreshing(false);
            }
        });


        mNewMessage = (EditText) view.findViewById(R.id.message);
        mSort = (Button) view.findViewById(R.id.sort);
        mSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortPosts();
            }
        });

        mRecycler = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        //mRecycler.setAdapter(mAdapter);

        if (savedInstanceState != null) {
           //mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        new FetchItemsTask().execute();

        new CreatePostTask().execute();

        //updateUI();

        return view;
    }



    private void updateUI()
    {
        if (mAdapter == null) {
            mAdapter = new ChatAdapter(mItems);
            mRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void sortPosts() {
        Collections.sort(mItems, new Comparator<ChatPost>() { //copied and edited from link below
            @Override                                   //https://stackoverflow.com/questions/2535124/how-to-sort-an-arraylist-of-objects-by-a-property
            public int compare(ChatPost p1, ChatPost p2) {
                //System.out.println("p1: "+ p1.getlikes() + " p2: " + p2.getlikes());
                return p2.getlikes() - p1.getlikes() ;
            }
        });

        mAdapter = new ChatAdapter(mItems);
        mRecycler.setAdapter(mAdapter);
    }


    private class ChatHolder extends RecyclerView.ViewHolder
    {
        private ChatPost mPost;
        private TextView mLikes, mDislikes, mTime, mPostText;


        public ChatHolder(View itemView)
        {
            super(itemView);
            mTime = (TextView) itemView.findViewById(R.id.post_date);
            mPostText = (TextView) itemView.findViewById(R.id.post_text);
            mLikes = (TextView) itemView.findViewById(R.id.post_upvote);
            mDislikes = (TextView) itemView.findViewById(R.id.post_downvote);
        }

        public void bindPost(ChatPost post)
        {
            mPost = post;
            mLikes.setText("Likes: " + post.getLikes());
            mDislikes.setText("Dislikes: "+post.getDislikes());
            mTime.setText(post.getTime());
            mPostText.setText(post.getText());
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder>
    {
        private List<ChatPost> mPosts;

        public ChatAdapter(List<ChatPost> posts)
        {
            mPosts = posts;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_posts, parent, false);

            return new ChatHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder, int position) {
            ChatPost post = mPosts.get(position);
            holder.bindPost(post);
        }

        @Override
        public int getItemCount() {
            return mPosts.size();
        }

    }

    private class FetchItemsTask extends AsyncTask<Void,Void,List<ChatPost>> {

        @Override
        protected List<ChatPost> doInBackground(Void... params) {
            return new FetchPost().fetchItems();
        }

        @Override
        protected void onPostExecute(List<ChatPost> items) {
            mItems = items;
            updateUI();
        }

    }

    public class CreatePostTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            return new CreatePost().createItem(params);
        }


        @Override
        protected void onPostExecute(String result) {
            new FetchItemsTask().execute();
        }
    }


}
