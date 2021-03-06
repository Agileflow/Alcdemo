package com.alc.alcdemo.view.developer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.alc.alcdemo.R;
import com.alc.alcdemo.model.Developer;
import com.alc.alcdemo.support.Constants;

/**
 * Created by agileflow on 4/21/2017.
 */

public class DeveloperActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        Intent intent = getIntent();

        if(intent.hasExtra(Constants.DEVELOPER)){
            Developer dev = (Developer) intent.getSerializableExtra(Constants.DEVELOPER);
            DeveloperFragment fragment = DeveloperFragment.newInstance(dev);
            initFragment(fragment);
        }
    }

    private void initFragment(Fragment fragment) {
        // Add the fragment to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.detailsContentFrame, fragment);
        transaction.commit();
    }
}
