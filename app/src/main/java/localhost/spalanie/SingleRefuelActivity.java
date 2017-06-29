package localhost.spalanie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class SingleRefuelActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_refule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_refule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private final Globals global;

        public PlaceholderFragment() {
            global = Globals.getInstance();
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt("position", sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            SingleRefuelActivity singleRefuelActivity = (SingleRefuelActivity) getActivity();

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
            Refuel refuel = global.getRefuelByPosition(pozycja);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formatDate = formatter.format(refuel.getDate());
            data.setText(formatDate);
            kilometry.setText(refuel.getSubBilling().toString());
            litry.setText(refuel.getLiters().toString());
            spalanie.setText(refuel.getCombustion().toString());
            spalaniePC.setText(refuel.getCombustionPC().toString());
            cena.setText(refuel.getPrice().toString());
            predkosc.setText(refuel.getAvg_speed().toString());
            stacja.setText(refuel.getPetrolStation());

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
            global = Globals.getInstance();
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return global.getRefuelCount();
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