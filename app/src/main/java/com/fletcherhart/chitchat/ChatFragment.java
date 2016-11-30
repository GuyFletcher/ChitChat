package com.fletcherhart.chitchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fletcher on 11/29/2016.
 */

public class ChatFragment extends Fragment {

    private RecyclerView mRecycler;
    private ChatAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        mRecycler = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
           //mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    private void updateUI()
    {

    }


    private class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public ChatHolder(View itemView)
        {
            super(itemView);

        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            startActivity(intent);
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder>
    {
        public ChatAdapter()
        {

        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_chat, parent, false);
            return new ChatHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1; //.size();
        }

    }


}
