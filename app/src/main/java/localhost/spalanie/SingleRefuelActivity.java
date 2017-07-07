package localhost.spalanie;

import android.content.Intent;
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

    private Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_refule);

        global = Globals.getInstance();
        Intent intent = getIntent();
        int refuel_id = intent.getIntExtra("refuel_id", global.getMaxId());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(refuel_id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return id == R.id.navigation || super.onOptionsItemSelected(item);

    }

    public static class PlaceholderFragment extends Fragment {
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
            View rootView = inflater.inflate(R.layout.fragment_single_refule, container, false);

            TextView tvData = (TextView) rootView.findViewById(R.id.tvDate);
            TextView tvKilometers = (TextView) rootView.findViewById(R.id.tvSubBilling);
            TextView tvLiters = (TextView) rootView.findViewById(R.id.tvLiters);
            TextView tvCombustion = (TextView) rootView.findViewById(R.id.tvCombustion);
            TextView tvCombustionPC = (TextView) rootView.findViewById(R.id.tvCombustionPC);
            TextView tvPrices = (TextView) rootView.findViewById(R.id.tvPrice);
            TextView tvSpeed = (TextView) rootView.findViewById(R.id.tvavgSpeed);
            TextView tvPetrolStation = (TextView) rootView.findViewById(R.id.tvStation);

            Bundle bundle = getArguments();
            Integer position = bundle.getInt("position");
            Refuel refuel = global.getRefuelByPosition(position);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formatDate = formatter.format(refuel.getDate());
            tvData.setText(formatDate);
            tvKilometers.setText(refuel.getSubBilling().toString());
            tvLiters.setText(refuel.getLiters().toString());
            tvCombustion.setText(refuel.getCombustion().toString());
            tvCombustionPC.setText(refuel.getCombustionPC().toString());
            tvPrices.setText(refuel.getPrice().toString());
            tvSpeed.setText(refuel.getAvg_speed().toString());
            tvPetrolStation.setText(refuel.getPetrolStation());

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
            return PlaceholderFragment.newInstance(position);
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