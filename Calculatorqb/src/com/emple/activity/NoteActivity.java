package com.emple.activity;

import cn.gqb.calculator.R;
import net.yanzm.mth.MaterialTabHost;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.emple.fragment.ElementFragment;
import com.emple.fragment.MvoidFragment;
import com.emple.fragment.UnitCalFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class NoteActivity extends StatusSetActivity {

	private TextView mhead_title;
	private List<Fragment> framents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_note);
		
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		mhead_title=(TextView) findViewById(R.id.mhead_title);
		mhead_title.setText("功能说明");
		
		framents=new ArrayList<Fragment>();
		framents.add(new MvoidFragment());
		framents.add(new UnitCalFragment());
		framents.add(new ElementFragment());
		
		MaterialTabHost tabHost = (MaterialTabHost) findViewById(android.R.id.tabhost);
        tabHost.setType(MaterialTabHost.Type.FullScreenWidth);
//        tabHost.setType(MaterialTabHost.Type.Centered);
//        tabHost.setType(MaterialTabHost.Type.LeftOffset);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(pagerAdapter.getPageTitle(i));
        }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(tabHost);

        tabHost.setOnTabChangeListener(new MaterialTabHost.OnTabChangeListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });
	}	
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return framents.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return framents.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "∑函数";
                case 1:
                    return "单位计算";
                case 2:
                    return "元素表";
            }
            return null;
        }
    }

	/*public static class PlaceholderFragment extends Fragment {
        
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.section_label);
            tv.setText("Here is page " + getArguments().getInt(ARG_SECTION_NUMBER));
            return rootView;
        }
    }*/	
}
