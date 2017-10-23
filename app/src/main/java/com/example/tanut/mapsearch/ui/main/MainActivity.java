package com.example.tanut.mapsearch.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load Main Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance(), MainFragment.TAG).commit();
    }


    @Override
    public void onBackPressed() {

        Log.d("BackPrise","BackPrise");
        if(getSupportFragmentManager().findFragmentByTag(MainFragment.TAG).getChildFragmentManager().getBackStackEntryCount()>0){

            getSupportFragmentManager().findFragmentByTag(MainFragment.TAG).getChildFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }

    }
}
