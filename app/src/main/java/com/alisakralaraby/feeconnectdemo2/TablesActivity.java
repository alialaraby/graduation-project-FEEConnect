package com.alisakralaraby.feeconnectdemo2;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.alisakralaraby.feeconnectdemo2.tables_fragments.FragmentTablesCSE;
import com.alisakralaraby.feeconnectdemo2.tables_fragments.FragmentTablesCommu;
import com.alisakralaraby.feeconnectdemo2.tables_fragments.FragmentTablesControl;

public class TablesActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:
                    return new FragmentTablesCSE();
                case 1:
                    return new FragmentTablesControl();
                case 2:
                    return new FragmentTablesCommu();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "CSE Dept";
                case 1:
                    return "Control Dept";
                case 2:
                    return "Commu Dept";
            }
            return null;
        }
    }
}
