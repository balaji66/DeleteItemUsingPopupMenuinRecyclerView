package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragment = new CandidateListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //Adding Fragment to FragmentTransaction.
        transaction.add(R.id.fragment,mFragment,"homeFragment");
//        transaction.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down);
        //transaction.addToBackStack("homeFragment");
        transaction.commit();

    }
}
