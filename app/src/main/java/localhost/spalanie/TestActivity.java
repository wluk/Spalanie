package localhost.spalanie;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Globals global;

    private LinearLayout avgVP;
    private LinearLayout statsVP;
    private RelativeLayout addRefuleViwe;
    private ConstraintLayout refuleView;
    private ArrayList<Refule> list;
    private ListView listRefule;
    private RefuleAdapter adapter;

    private Cursor todoCursor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    avgVP.setVisibility(View.GONE);
                    statsVP.setVisibility(View.GONE);
                    refuleView.setVisibility(View.VISIBLE);
                    addRefuleViwe.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    avgVP.setVisibility(View.GONE);
                    statsVP.setVisibility(View.VISIBLE);
                    refuleView.setVisibility(View.GONE);
                    wykres();
                    return true;
                case R.id.navigation_notifications:
                    avgVP.setVisibility(View.VISIBLE);
                    statsVP.setVisibility(View.GONE);
                    refuleView.setVisibility(View.GONE);
                    addRefuleViwe.setVisibility(View.GONE);
                    setStats();
                    return true;
                case R.id.navigation_add_refule:
                    avgVP.setVisibility(View.GONE);
                    statsVP.setVisibility(View.GONE);
                    refuleView.setVisibility(View.GONE);
                    addRefuleViwe.setVisibility(View.VISIBLE);
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

        setLayout();
        btnAddLisnerer();
        goTOHomePage();
        dbOp();
        refreshRefules();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listRefule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Refule model = (Refule) adapter.getItem(position);

                goToSingleRefuleActivity(model);
            }
        });
    }

    private void goTOHomePage(){
        avgVP.setVisibility(View.GONE);
        statsVP.setVisibility(View.GONE);
        addRefuleViwe.setVisibility(View.GONE);
        refuleView.setVisibility(View.VISIBLE);
    }

    private void refreshRefules(){
        list = new ArrayList<Refule>();
        for (Refule item : global.getData()) {
            list.add(item);
        }
        Collections.reverse(list);
        listRefule = (ListView) findViewById(R.id.list);
        adapter = new RefuleAdapter(this, list);
        listRefule.setAdapter(adapter);
    }

    private void goToAddRefule() {
        Intent addActivity = new Intent();
        addActivity.setClass(this, AddActivity.class);

        startActivity(addActivity);
    }

    private void wykres() {
        // Line Graph
        List<Refule> refules = global.getData();

        GraphView line_graph = (GraphView) findViewById(R.id.graph);

        int iterator = 0;
        ArrayList<DataPoint> prices = new ArrayList<>();
        for (Refule item : refules) {
            DataPoint newest = new DataPoint(iterator, item.getPrice());
            prices.add(newest);
            iterator++;
        }
        DataPoint[] points = prices.toArray(new DataPoint[0]);
        LineGraphSeries<DataPoint> line_series = new LineGraphSeries<DataPoint>(points);

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

    private void setStats() {
        TextView avgCombunious = (TextView) findViewById(R.id.tvAVGCombunius);
        avgCombunious.setText(getString(R.string.combustion) + " " + String.valueOf(global.getAvgCombustion() + getString(R.string.combustionPerKilometers)));
        TextView avgSubBilings = (TextView) findViewById(R.id.tvAVGKIlometers);
        avgSubBilings.setText(getString(R.string.kilometers) + " " + String.valueOf(global.getAvgSubBilling()));
        TextView avgPrice = (TextView) findViewById(R.id.tvAVGPrice);
        avgPrice.setText(getString(R.string.price) + " " + String.valueOf(global.getAvgPrice() + getString(R.string.currency)));
        TextView minCombunious = (TextView) findViewById(R.id.tvMinCombunious);
        minCombunious.setText(getString(R.string.combustion) + " " + String.valueOf(global.getMinCombustion() + getString(R.string.combustionPerKilometers)));
        TextView minPrice = (TextView) findViewById(R.id.tvMinPrice);
        minPrice.setText(getString(R.string.price) + " " + String.valueOf(global.getMinPrice() + getString(R.string.currency)));
        TextView maxCombunious = (TextView) findViewById(R.id.tvMaxCombunius);
        maxCombunious.setText(getString(R.string.combustion) + " " + String.valueOf(global.getMaxCombustion() + getString(R.string.combustionPerKilometers)));
        TextView maxPrice = (TextView) findViewById(R.id.tvMaxPrice);
        maxPrice.setText(getString(R.string.price) + " " + String.valueOf(global.getMaxPrice() + getString(R.string.currency)));
        TextView maxKilometers = (TextView) findViewById(R.id.tvMaxKilometers);
        maxKilometers.setText(getString(R.string.kilometers) + " " + String.valueOf(global.getMaxKilometers()));
    }

    private void setLayout() {
        avgVP = (LinearLayout) findViewById(R.id.avgView);
        statsVP = (LinearLayout) findViewById(R.id.statsView);
        addRefuleViwe = (RelativeLayout) findViewById(R.id.addRefuleView);
        refuleView = (ConstraintLayout) findViewById(R.id.refuleView);
    }

    private void goToSingleRefuleActivity(Refule refule) {
        Intent singleRefuleActivity = new Intent();
        singleRefuleActivity.setClass(this, SingleRefuleActivity.class);

        singleRefuleActivity.putExtra("refule", refule);

        startActivity(singleRefuleActivity);
    }

    private void btnAddLisnerer() {
        Button addRefule = (Button) findViewById(R.id.btnAdd);
        addRefule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global.addData(getRefuleData(view));
                goTOHomePage();
                refreshRefules();
            }
        });
    }

    private Refule getRefuleData(View v) {
        Refule refule = new Refule();

        EditText valueSubBilling = (EditText) findViewById(R.id.editSub_billing);
        refule.setSubBilling(Double.valueOf(valueSubBilling.getText().toString()));

        EditText valueLiters = (EditText) findViewById(R.id.editLiters);
        refule.setLiters(Double.valueOf(valueLiters.getText().toString()));

        EditText valuePrice = (EditText) findViewById(R.id.editPrice);
        refule.setPrice(Double.valueOf(valuePrice.getText().toString()));

        EditText valueCombustion = (EditText) findViewById(R.id.editCombustion);
        refule.setCombustionPC(Double.valueOf(valueCombustion.getText().toString()));

        EditText valueAvgSpeed = (EditText) findViewById(R.id.editAvgSpeed);
        refule.setAvg_speed(Integer.valueOf(valueAvgSpeed.getText().toString()));

        EditText valuePetrolStation = (EditText) findViewById(R.id.editPetrolStation);
        refule.setPetrolStation(valuePetrolStation.getText().toString());

        refule.setCombustion(Math.round(((refule.getLiters() / (double) refule.getSubBilling()) * 100) * 100.0) / 100.0);
        refule.setDate(new Date());

        refule.setId(global.getRefuleCount() + 1);

        return refule;
    }

    private void goToMainActivity() {
        Intent mainActivity = new Intent();
        mainActivity.setClass(this, TestActivity.class);
        //mainActivity.putExtra("refule", refuleData);

        startActivity(mainActivity);
    }

    private void dbOp() {
        DBHelper dbHelper = new DBHelper(this);
        try {
            SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
            dbHelper.onUpgrade(dbRead, 3, 4);
            Cursor c = dbRead.query(RefuleTable.TABLE_NAME, new String[]{RefuleTable.ID, RefuleTable.DATE, RefuleTable.STATION, RefuleTable.SUB_BILLING, RefuleTable.LITERS, RefuleTable.PRICE, RefuleTable.COMBUSTION, RefuleTable.COMBUSTION_CAR, RefuleTable.AVG_SPEED, RefuleTable.COMMENT}, null, null, null, null, null, null);

            ArrayList<Refule> refules = new ArrayList<Refule>();

            try {
                while (c.moveToNext()) {
                    Refule tmpRefule = new Refule();
                    tmpRefule.setId(Integer.parseInt(c.getString(0)));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = dateFormat.parse(c.getString(1));
                    tmpRefule.setDate(date);
                    tmpRefule.setPetrolStation(c.getString(2));
                    tmpRefule.setSubBilling(Double.parseDouble(c.getString(3)));
                    tmpRefule.setLiters(Double.parseDouble(c.getString(4)));
                    tmpRefule.setPrice(Double.parseDouble(c.getString(5)));
                    tmpRefule.setCombustion(Double.parseDouble(c.getString(6)));
                    tmpRefule.setCombustionPC(Double.parseDouble(c.getString(7)));
                    tmpRefule.setAvg_speed(Integer.parseInt(c.getString(8)));
                    tmpRefule.setComment(c.getString(9));

                    refules.add(tmpRefule);
                }
            } catch (Exception e) {
                System.out.print(e.toString());
            } finally {
                c.close();
                global.addRefules(refules);
            }

        } catch (SQLiteException ex) {
            System.out.println(ex);
        } finally {
            dbHelper.close();
        }
    }
}
