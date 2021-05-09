package com.threeklines.isibayaacademyclient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {
    static ArrayList<Topic> topics;

    public TopicsAdapter(ArrayList<Topic> topics) {
        TopicsAdapter.topics = topics;
    }

    @NonNull
    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsAdapter.ViewHolder holder, int position) {
        holder.topicName.setText(topics.get(position).getTopic());
        holder.topicDesc.setText(topics.get(position).getTopicDesc());
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        TextView topicName;
        TextView topicDesc;
        ImageView procced;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.topic_layout);
            topicName = itemView.findViewById(R.id.topic_name);
            topicDesc = itemView.findViewById(R.id.topic_decs);
            procced = itemView.findViewById(R.id.procced);

            procced.setOnClickListener(v -> {
                TopicLearningOptions fragment = new TopicLearningOptions();
                fragment.setSelectedTopic(topics.get(getAdapterPosition()));
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
            });
        }


    }
}
