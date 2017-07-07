package localhost.spalanie;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Globals global;

    private LinearLayout avgVP;
    private LinearLayout pricesVP;
    private LinearLayout combustionVP;
    private RelativeLayout addRefuelVice;
    private ConstraintLayout refuelView;
    private ListView listRefuel;
    private boolean doubleBackToExitPressedOnce = false;
    private Graph graph;

    private EditText valueSubBilling, valueLiters, valuePrice, valueCombustion, valueAvgSpeed, valuePetrolStation;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    avgVP.setVisibility(View.GONE);
                    pricesVP.setVisibility(View.GONE);
                    combustionVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.VISIBLE);
                    addRefuelVice.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_pricesStat:
                    graph.graphPrices((GraphView) findViewById(R.id.graphPrices));
                    avgVP.setVisibility(View.GONE);
                    pricesVP.setVisibility(View.VISIBLE);
                    combustionVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.GONE);
                    addRefuelVice.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    setStats();
                    avgVP.setVisibility(View.VISIBLE);
                    pricesVP.setVisibility(View.GONE);
                    combustionVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.GONE);
                    addRefuelVice.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_add_refule:
                    avgVP.setVisibility(View.GONE);
                    pricesVP.setVisibility(View.GONE);
                    combustionVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.GONE);
                    addRefuelVice.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_combustionStat:
                    graph.graphCombustion((GraphView) findViewById(R.id.graphCombustion));
                    avgVP.setVisibility(View.GONE);
                    pricesVP.setVisibility(View.GONE);
                    combustionVP.setVisibility(View.VISIBLE);
                    refuelView.setVisibility(View.GONE);
                    addRefuelVice.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = Globals.getInstance();
        graph = new Graph();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setLayout();
        goToHomePage();
        refreshRefuels();
        registerViews();

        listRefuel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToSingleRefuelActivity(((int) id) - 1);
            }
        });
    }

    private void loadEdits() {
        valueSubBilling = (EditText) findViewById(R.id.editSub_billing);
        valueLiters = (EditText) findViewById(R.id.editLiters);
        valuePrice = (EditText) findViewById(R.id.editPrice);
        valueCombustion = (EditText) findViewById(R.id.editCombustion);
        valueAvgSpeed = (EditText) findViewById(R.id.editAvgSpeed);
        valuePetrolStation = (EditText) findViewById(R.id.editPetrolStation);
    }

    private void goToHomePage() {
        avgVP.setVisibility(View.GONE);
        pricesVP.setVisibility(View.GONE);
        addRefuelVice.setVisibility(View.GONE);
        combustionVP.setVisibility(View.GONE);
        refuelView.setVisibility(View.VISIBLE);
    }

    private void refreshRefuels() {
        ArrayList<Refuel> list = new ArrayList<>();
        for (Refuel item : global.getData()) {
            list.add(item);
        }
        Collections.reverse(list);
        listRefuel = (ListView) findViewById(R.id.list);
        RefuelAdapter adapter = new RefuelAdapter(this, list);
        listRefuel.setAdapter(adapter);
    }

    private void setLayout() {
        avgVP = (LinearLayout) findViewById(R.id.statsView);
        pricesVP = (LinearLayout) findViewById(R.id.statsPricesView);
        combustionVP = (LinearLayout) findViewById(R.id.statsCombustionView);
        addRefuelVice = (RelativeLayout) findViewById(R.id.addRefuelView);
        refuelView = (ConstraintLayout) findViewById(R.id.refuleView);
    }

    private void goToSingleRefuelActivity(int refuel_id) {
        Intent singleRefuelActivity = new Intent();
        singleRefuelActivity.setClass(this, SingleRefuelActivity.class);
        singleRefuelActivity.putExtra("refuel_id", refuel_id);

        startActivity(singleRefuelActivity);
    }

    private Refuel getRefuelData() {
        Refuel refuel = new Refuel();

        refuel.setSubBilling(Double.valueOf(valueSubBilling.getText().toString()));
        refuel.setLiters(Double.valueOf(valueLiters.getText().toString()));
        refuel.setPrice(Double.valueOf(valuePrice.getText().toString()));
        refuel.setCombustionPC(Double.valueOf(valueCombustion.getText().toString()));
        refuel.setAvg_speed(Integer.valueOf(valueAvgSpeed.getText().toString()));
        refuel.setPetrolStation(valuePetrolStation.getText().toString());

        refuel.setCombustion(Math.round(((refuel.getLiters() / (double) refuel.getSubBilling()) * 100) * 100.0) / 100.0);
        refuel.setDate(new Date());

        refuel.setId(global.getRefuelCount() + 1);


        return refuel;
    }

    private void setStats() {
        TextView avgCombustion = (TextView) findViewById(R.id.tvAVGCombunius);
        avgCombustion.setText(getString(R.string.combustion) + " " + String.valueOf(global.getAvgCombustion() + getString(R.string.combustionPerKilometers)));

        TextView avgSubBillings = (TextView) findViewById(R.id.tvAVGKIlometers);
        avgSubBillings.setText(getString(R.string.kilometers) + " " + String.valueOf(global.getAvgSubBilling()));

        TextView avgPrice = (TextView) findViewById(R.id.tvAVGPrice);
        avgPrice.setText(getString(R.string.price) + " " + String.valueOf(global.getAvgPrice() + getString(R.string.currency)));

        TextView minCombustion = (TextView) findViewById(R.id.tvMinCombunious);
        minCombustion.setText(getString(R.string.combustion) + " " + String.valueOf(global.getMinCombustion() + getString(R.string.combustionPerKilometers)));

        TextView minPrice = (TextView) findViewById(R.id.tvMinPrice);
        minPrice.setText(getString(R.string.price) + " " + String.valueOf(global.getMinPrice() + getString(R.string.currency)));

        TextView maxCombustion = (TextView) findViewById(R.id.tvMaxCombunius);
        maxCombustion.setText(getString(R.string.combustion) + " " + String.valueOf(global.getMaxCombustion() + getString(R.string.combustionPerKilometers)));

        TextView maxPrice = (TextView) findViewById(R.id.tvMaxPrice);
        maxPrice.setText(getString(R.string.price) + " " + String.valueOf(global.getMaxPrice() + getString(R.string.currency)));

        TextView maxKilometers = (TextView) findViewById(R.id.tvMaxKilometers);
        maxKilometers.setText(getString(R.string.kilometers) + " " + String.valueOf(global.getMaxKilometers()));
    }

    private void dbAddRefuel(Refuel refuelToInsert) {
        global.addData(refuelToInsert);

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        try {
            SQLiteDatabase dbWrite = dbHelper.getReadableDatabase();
            dbHelper.addRefuel(dbWrite, refuelToInsert);
            Toast.makeText(this, getString(R.string.add_refuel), Toast.LENGTH_SHORT).show();
        } catch (SQLiteException ex) {
            System.out.println(ex);
        } finally {
            dbHelper.close();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        goToHomePage();
        Toast.makeText(this, getString(R.string.double_back_btn), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void registerViews() {
        loadEdits();

        valueSubBilling.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(valueSubBilling, getApplicationContext());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        valueLiters.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(valueLiters, getApplicationContext());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        valuePrice.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(valuePrice, getApplicationContext());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        valueCombustion.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(valueCombustion, getApplicationContext());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        valueAvgSpeed.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(valueAvgSpeed, getApplicationContext());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        valuePetrolStation.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(valuePetrolStation, getApplicationContext());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        Button addBtnSubmit = (Button) findViewById(R.id.btnAdd);
        addBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation())
                    submitForm();
                else
                    Toast.makeText(MainActivity.this, getString(R.string.invalid_form), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        Toast.makeText(this, getString(R.string.submitting_form), Toast.LENGTH_LONG).show();

        dbAddRefuel(getRefuelData());
        goToHomePage();
        refreshRefuels();
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(valueSubBilling, getApplicationContext())) ret = false;
        if (!Validation.hasText(valueLiters, getApplicationContext())) ret = false;
        if (!Validation.hasText(valuePrice, getApplicationContext())) ret = false;
        if (!Validation.hasText(valueCombustion, getApplicationContext())) ret = false;
        if (!Validation.hasText(valueAvgSpeed, getApplicationContext())) ret = false;
        if (!Validation.hasText(valuePetrolStation, getApplicationContext())) ret = false;

        return ret;
    }
}
