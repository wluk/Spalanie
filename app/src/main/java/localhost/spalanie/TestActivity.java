package localhost.spalanie;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private Globals global;

    private LinearLayout avgVP;
    private LinearLayout statsVP;
    private RelativeLayout addRefuelVice;
    private ConstraintLayout refuelView;
    private ListView listRefuel;
    private RefuelAdapter adapter;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    avgVP.setVisibility(View.GONE);
                    statsVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.VISIBLE);
                    addRefuelVice.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    avgVP.setVisibility(View.GONE);
                    statsVP.setVisibility(View.VISIBLE);
                    refuelView.setVisibility(View.GONE);
                    graph();
                    return true;
                case R.id.navigation_notifications:
                    avgVP.setVisibility(View.VISIBLE);
                    statsVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.GONE);
                    addRefuelVice.setVisibility(View.GONE);
                    setStats();
                    return true;
                case R.id.navigation_add_refule:
                    avgVP.setVisibility(View.GONE);
                    statsVP.setVisibility(View.GONE);
                    refuelView.setVisibility(View.GONE);
                    addRefuelVice.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = Globals.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setLayout();
        binAddListener();
        goToHomePage();
        refreshRefuels();

        listRefuel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Refuel model = (Refuel) adapter.getItem(position);

                goToSingleRefuelActivity(model);
            }
        });
    }

    private void goToHomePage() {
        avgVP.setVisibility(View.GONE);
        statsVP.setVisibility(View.GONE);
        addRefuelVice.setVisibility(View.GONE);
        refuelView.setVisibility(View.VISIBLE);
    }

    private void refreshRefuels() {
        ArrayList<Refuel> list = new ArrayList<>();
        for (Refuel item : global.getData()) {
            list.add(item);
        }
        Collections.reverse(list);
        listRefuel = (ListView) findViewById(R.id.list);
        adapter = new RefuelAdapter(this, list);
        listRefuel.setAdapter(adapter);
    }

    private void setLayout() {
        avgVP = (LinearLayout) findViewById(R.id.avgView);
        statsVP = (LinearLayout) findViewById(R.id.statsView);
        addRefuelVice = (RelativeLayout) findViewById(R.id.addRefuleView);
        refuelView = (ConstraintLayout) findViewById(R.id.refuleView);
    }

    private void goToSingleRefuelActivity(Refuel refuel) {
        Intent singleRefuelActivity = new Intent();
        singleRefuelActivity.setClass(this, SingleRefuelActivity.class);

        singleRefuelActivity.putExtra("refuel", refuel);

        startActivity(singleRefuelActivity);
    }

    private void binAddListener() {
        Button addRefuel = (Button) findViewById(R.id.btnAdd);
        addRefuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAddRefuel(getRefuelData());
                goToHomePage();
                refreshRefuels();
            }
        });
    }

    private void graph() {
        // Line Graph
        List<Refuel> refuels = global.getData();

        GraphView line_graph = (GraphView) findViewById(R.id.graph);

        int iterator = 0;
        ArrayList<DataPoint> prices = new ArrayList<>();
        for (Refuel item : refuels) {
            DataPoint newest = new DataPoint(iterator, item.getPrice());
            prices.add(newest);
            iterator++;
        }
        DataPoint[] points = prices.toArray(new DataPoint[0]);
        LineGraphSeries<DataPoint> line_series = new LineGraphSeries<>(points);

        line_graph.addSeries(line_series);

        // set the bound

        // set manual X bounds
        line_graph.getViewport().setXAxisBoundsManual(false);
        line_graph.getViewport().setMinX(0);
        line_graph.getViewport().setMaxX(iterator);

        // set manual Y bounds
        line_graph.getViewport().setYAxisBoundsManual(false);
        line_graph.getViewport().setMinY(3);
        line_graph.getViewport().setMaxY(6);

        line_graph.getViewport().setScrollable(true);
        line_series.setDrawBackground(true);
        line_series.setBackgroundColor(Color.BLUE);
    }

    private Refuel getRefuelData() {
        Refuel refuel = new Refuel();

        EditText valueSubBilling = (EditText) findViewById(R.id.editSub_billing);
        refuel.setSubBilling(Double.valueOf(valueSubBilling.getText().toString()));

        EditText valueLiters = (EditText) findViewById(R.id.editLiters);
        refuel.setLiters(Double.valueOf(valueLiters.getText().toString()));

        EditText valuePrice = (EditText) findViewById(R.id.editPrice);
        refuel.setPrice(Double.valueOf(valuePrice.getText().toString()));

        EditText valueCombustion = (EditText) findViewById(R.id.editCombustion);
        refuel.setCombustionPC(Double.valueOf(valueCombustion.getText().toString()));

        EditText valueAvgSpeed = (EditText) findViewById(R.id.editAvgSpeed);
        refuel.setAvg_speed(Integer.valueOf(valueAvgSpeed.getText().toString()));

        EditText valuePetrolStation = (EditText) findViewById(R.id.editPetrolStation);
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
        } catch (SQLiteException ex) {
            System.out.println(ex);
        } finally {
            dbHelper.close();
        }
    }

    @Override
    public void onBackPressed() {
        goToHomePage();
//        new AlertDialog.Builder(this)
//                .setMessage("Are you sure you want to exit?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        TestActivity.super.onBackPressed();
//                    }
//                })
//                .setNegativeButton("No", null)
//                .show();


    }
}
