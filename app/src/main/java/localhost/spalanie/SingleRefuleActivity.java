package localhost.spalanie;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;

public class SingleRefuleActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static Refule refule = null;

    private ViewPager mViewPager;
    private Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_refule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_refule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        private final Globals global;

        public PlaceholderFragment() {
            global = Globals.getInstance();
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt("position", sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            SingleRefuleActivity singleRefuleActivity = (SingleRefuleActivity) getActivity();

            View rootView = inflater.inflate(R.layout.fragment_single_refule, container, false);

            TextView data = (TextView) rootView.findViewById(R.id.tvDate);
            TextView kilometry = (TextView) rootView.findViewById(R.id.tvSubBilling);
            TextView litry = (TextView) rootView.findViewById(R.id.tvLiters);
            TextView spalanie = (TextView) rootView.findViewById(R.id.tvCombustion);
            TextView spalaniePC = (TextView) rootView.findViewById(R.id.tvCombustionPC);
            TextView cena = (TextView) rootView.findViewById(R.id.tvPrice);
            TextView predkosc = (TextView) rootView.findViewById(R.id.tvavgSpeed);
            TextView stacja = (TextView) rootView.findViewById(R.id.tvStation);

            Bundle bundle = getArguments();
            Integer pozycja = bundle.getInt("position");
            Refule refule = global.getRefuleByPosition(pozycja);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formatDate = formatter.format(refule.getDate());
            data.setText(formatDate);
            kilometry.setText(refule.getSubBilling().toString());
            litry.setText(refule.getLiters().toString());
            spalanie.setText(refule.getCombustion().toString());
            spalaniePC.setText(refule.getCombustionPC().toString());
            cena.setText(refule.getPrice().toString());
            predkosc.setText(refule.getAvg_speed().toString());
            stacja.setText(refule.getPetrolStation());

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
            global = Globals.getInstance();
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return global.getRefuleCount();
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
