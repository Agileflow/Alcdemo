package com.alc.alcdemo.view.developer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alc.alcdemo.model.Developer;
import com.alc.alcdemo.R;

import com.alc.alcdemo.support.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by agileflow on 4/21/2017.
 */

public class DeveloperFragment extends Fragment implements DeveloperContract.View{

    @BindView(R.id.dev_avatar)
    ImageView profilePicImageView;

    @BindView(R.id.dev_github_profile_url)
    Button profileUrlButton;

    @BindView(R.id.dev_username)
    TextView usernameTextView;

    @BindView(R.id.dev_followers)
    TextView followersTextView;

    @BindView(R.id.dev_repos)
    TextView reposTextView;

    @BindView(R.id.share_icon)
    Button shareButton;

    private DeveloperContract.UserActionListener actionListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionListener = new DeveloperPresenter(this);

    }

    public static DeveloperFragment newInstance(Developer dev){
        Bundle args = new Bundle();
        args.putSerializable(Constants.DEVELOPER,dev);

        DeveloperFragment devDetailFragment = new DeveloperFragment();
        devDetailFragment.setArguments(args);

        return devDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  = inflater.inflate(R.layout.fragment_activity_developer, container, false);

        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        actionListener.openDevDetails((Developer) getArguments().getSerializable(Constants.DEVELOPER));
    }


    @Override
    public void showProfileUrl(String profileUrl) {
        profileUrlButton.setText(profileUrl);
    }

    @Override
    public void showProfilePhoto(String profilePhotoUrl) {
        Picasso.with(getContext())
                .load(profilePhotoUrl)
                .placeholder(R.drawable.placeholder_icon)
                .error(R.drawable.placeholder_icon)
                .into(profilePicImageView);
    }

    @Override
    public void showUsername(String username) {
        usernameTextView.setText(username);
    }

    @Override
    public void showFollowers(String followers) {
        followersTextView.setText(followers);
    }

    @Override
    public void showRepository(String repos) {
        reposTextView.setText(repos);
    }

    @Override
    public void launchShareIntent(String username, String profileUrl) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                String.format("Check out this awesome developer @%s, %s", username, profileUrl)
        );
        shareIntent.setType("text/plain");
        if(shareIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivity(Intent.createChooser(shareIntent,getString(R.string.share_dev_details)));
        }
    }

    @OnClick(R.id.dev_github_profile_url)
    public void openLink(){
        String profileUrl = profileUrlButton.getText().toString();
        actionListener.openWebPage(profileUrl);
    }

    @OnClick(R.id.share_icon)
    public void shareGithubProfile(){
        String username = usernameTextView.getText().toString();
        String profileUrl = profileUrlButton.getText().toString();

        actionListener.shareGithubProfile(username,profileUrl);
    }

    @Override
    public void openInBrowser(Uri pageUri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,pageUri);
        if(browserIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivity(browserIntent);
        }
    }
}
