package app.jorge.mobile.com.transportalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class AlertsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alerts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_alerts_service, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText("BAD SERVICE ALERT");


                addSwitchListener(rootView, R.id.switch_compatBakeloo, R.string.bakerloo_selected);
                addSwitchListener(rootView, R.id.switch_compatCentral, R.string.central_selected);
                addSwitchListener(rootView, R.id.switch_compatCircle, R.string.circle_selected);
                addSwitchListener(rootView, R.id.switch_compatDistrict, R.string.district_selected);
                addSwitchListener(rootView, R.id.switch_compatHammersmith, R.string.hammersmith_selected);
                addSwitchListener(rootView, R.id.switch_compatJubilee, R.string.jubilee_selected);
                addSwitchListener(rootView, R.id.switch_compatMetropolitan, R.string.metropolitan_selected);
                addSwitchListener(rootView, R.id.switch_compatNothern, R.string.northern_selected);
                addSwitchListener(rootView, R.id.switch_compatPicadilly, R.string.piccadilly_selected);
                addSwitchListener(rootView, R.id.switch_compatVictoria, R.string.victoria_selected);
                addSwitchListener(rootView, R.id.switch_compatWaterloo, R.string.waterloo_selected);
                addSwitchListener(rootView, R.id.switch_compatOverground, R.string.london_overground_selected);
                addSwitchListener(rootView, R.id.switch_compatDlr, R.string.dlr_selected);
                addSwitchListener(rootView, R.id.switch_compatTflRail, R.string.tfl_rail_selected);

            
                setSelected(rootView, R.id.switch_compatBakeloo,R.string.bakerloo_selected);
                setSelected(rootView, R.id.switch_compatCentral,R.string.central_selected);
                setSelected(rootView, R.id.switch_compatCircle,R.string.circle_selected);
                setSelected(rootView, R.id.switch_compatDistrict,R.string.district_selected);
                setSelected(rootView, R.id.switch_compatHammersmith,R.string.hammersmith_selected);
                setSelected(rootView, R.id.switch_compatJubilee,R.string.jubilee_selected);
                setSelected(rootView, R.id.switch_compatMetropolitan,R.string.metropolitan_selected);
                setSelected(rootView, R.id.switch_compatNothern,R.string.northern_selected);
                setSelected(rootView, R.id.switch_compatPicadilly,R.string.piccadilly_selected);
                setSelected(rootView, R.id.switch_compatVictoria,R.string.victoria_selected);
                setSelected(rootView, R.id.switch_compatWaterloo,R.string.waterloo_selected);
                setSelected(rootView, R.id.switch_compatOverground,R.string.london_overground_selected);
                setSelected(rootView, R.id.switch_compatDlr,R.string.dlr_selected);
                setSelected(rootView, R.id.switch_compatTflRail,R.string.tfl_rail_selected);


                return rootView;
            }
            else{
                View rootView = inflater.inflate(R.layout.fragment_alerts, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
                /*
            View rootView = inflater.inflate(R.layout.fragment_alerts, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
            */
        }

        private void setSelected(View rootView,int switchId,int lineIdSelected){

            SwitchCompat switchCompact = (SwitchCompat) rootView.findViewById(switchId);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
           boolean isSelected=sharedPreferences.getBoolean(getString(lineIdSelected), false);
            switchCompact.setChecked(isSelected);
            Log.i(PlaceholderFragment.class.getSimpleName(), "str: "+String.valueOf(isSelected));

        }
        private void addSwitchListener(View rootView,int switchId,final int statusLineId) {

            SwitchCompat switchCompact = (SwitchCompat) rootView.findViewById(switchId);
            switchCompact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // do something, the isChecked will be
                    // true if the switch is in the On position
                    Log.i(PlaceholderFragment.class.getSimpleName(), String.valueOf(isChecked));
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(getString(statusLineId), isChecked);
                    editor.commit();
                }
            });
            
            
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
