package com.alc.alcdemo.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alc.alcdemo.R;
import com.alc.alcdemo.view.developer.DeveloperActivity;
import com.alc.alcdemo.controller.Developer;
import com.alc.alcdemo.support.Helper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agileflow on 4/21/2017.
 */

public class MainFragment extends Fragment implements MainContract.View{

    @BindView(R.id.developers_rv)

    RecyclerView recyclerView_developers;

    @BindView(R.id.loading)
    FrameLayout loading;

    private List<Developer> developers;

    MainContract.UserActionListener actionListener;

    DevelopersAdapter adapter_developers;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter_developers = new DevelopersAdapter(new ArrayList<Developer>(0),developerItemClickedListener);
        actionListener = new MainPresenter(this);
        loadDevelopers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_activity_main, container, false);

        ButterKnife.bind(this,root);

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView(){
        recyclerView_developers.setAdapter(adapter_developers);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView_developers.setLayoutManager(layoutManager);
    }

    private void loadDevelopers(){
        String searchQualifiers = "location:lagos+type:user+language:java";
        actionListener.loadDevelopers(searchQualifiers);
    }



    @Override
    public void showDevelopers(List<Developer> developers) {
        adapter_developers.replaceData(developers);
    }

    @Override
    public void openDevDetails(Developer developer) {
        Intent intent = new Intent(getContext(),DeveloperActivity.class);
        intent.putExtra(DeveloperActivity.EXTRA_DEVELOPER,developer);
        startActivity(intent);
    }

    @Override
    public void hideProgressIndicator() {
        // Hide Load
        loading.setVisibility(View.GONE);
    }

    @Override
    public boolean checkNetworkConnection() {
        return Helper.isNetworkConnected(getContext());
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Helper.showToast(getContext(),errorMessage);
    }

    DevelopersAdapter.OnDeveloperItemClickedListener developerItemClickedListener = new DevelopersAdapter.OnDeveloperItemClickedListener() {
        @Override
        public void onDeveloperItemClicked(Developer dev) {
            actionListener.showDevDetail(dev);
        }
    };

    static class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {
        private List<Developer> developers;
        private OnDeveloperItemClickedListener developerItemClickedListener;

        DevelopersAdapter(List<Developer> developers,OnDeveloperItemClickedListener developerItemClickedListener){
            this.developers = developers;
            this.developerItemClickedListener = developerItemClickedListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();

            LayoutInflater inflater = LayoutInflater.from(context);

            View devitemView = inflater.inflate(R.layout.developer_item,parent,false);

            return new ViewHolder(devitemView,developerItemClickedListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Developer developer = developers.get(position);

            holder.loadImage(developer.getAvatarUrl());
            holder.usernameTextView.setText(developer.getUsername());
        }

        @Override
        public int getItemCount() {
            return developers.size();
        }

        public Developer getItem(int position){
            return developers.get(position);
        }

        public void replaceData(List<Developer> developers){
            this.developers = developers;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private OnDeveloperItemClickedListener itemClickedListener;

            @BindView(R.id.github_profile_thumbnail)
            ImageView thumbnailImageView;

            @BindView(R.id.dev_username)
            TextView usernameTextView;


            public ViewHolder(View itemView,OnDeveloperItemClickedListener itemClickedListener) {
                super(itemView);

                ButterKnife.bind(this,itemView);

                this.itemClickedListener = itemClickedListener;

                itemView.setOnClickListener(cardClickListener);
            }

            View.OnClickListener cardClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Developer developer= getItem(position);
                    itemClickedListener.onDeveloperItemClicked(developer);
                }
            };

            private void loadImage(String imageUrl){
                Context context = thumbnailImageView.getContext();

                Picasso.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_icon)
                        .error(R.drawable.placeholder_icon)
                        .into(thumbnailImageView);
            }


        }

        public interface OnDeveloperItemClickedListener{
            void onDeveloperItemClicked(Developer dev);
        }
    }
}
