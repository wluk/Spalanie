package localhost.spalanie;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Globals global;

    private LinearLayout avgVP;
    private LinearLayout statsVP;
    private RelativeLayout addRefuleViwe;
    private ConstraintLayout refuleView;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setLayout();
        btnAddLisnerer();

        avgVP.setVisibility(View.GONE);
        statsVP.setVisibility(View.GONE);
        addRefuleViwe.setVisibility(View.GONE);
        refuleView.setVisibility(View.VISIBLE);


        global = Globals.getInstance();
        ListView listRefule = (ListView) findViewById(R.id.list);
        final ArrayList<Refule> list = new ArrayList<Refule>();
        for (Refule item : global.getData()) {
            list.add(item);
        }
        final RefuleAdapter adapter = new RefuleAdapter(this, list);
        listRefule.setAdapter(adapter);

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
            DataPoint newest = new DataPoint(iterator, item.price);
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
        avgCombunious.setText(avgCombunious.getText() + String.valueOf(global.getAvgCombustion()));
        TextView avgSubBilings = (TextView) findViewById(R.id.tvAVGKIlometers);
        avgSubBilings.setText(avgSubBilings.getText() + String.valueOf(global.getAvgSubBilling()));
        TextView avgPrice = (TextView) findViewById(R.id.tvAVGPrice);
        avgPrice.setText(avgPrice.getText() + String.valueOf(global.getAvgPrice()));
        TextView minCombunious = (TextView) findViewById(R.id.tvMinCombunious);
        minCombunious.setText(minCombunious.getText() + String.valueOf(global.getMinCombustion()));
        TextView minPrice = (TextView) findViewById(R.id.tvMinPrice);
        minPrice.setText(minPrice.getText() + String.valueOf(global.getMinPrice()));
        TextView maxCombunious = (TextView) findViewById(R.id.tvMaxCombunius);
        maxCombunious.setText(maxCombunious.getText() + String.valueOf(global.getMaxCombustion()));
        TextView maxPrice = (TextView) findViewById(R.id.tvMaxPrice);
        maxPrice.setText(maxPrice.getText() + String.valueOf(global.getMaxPrice()));
        TextView maxKilometers = (TextView) findViewById(R.id.tvMaxKilometers);
        maxKilometers.setText(maxKilometers.getText() + String.valueOf(global.getMaxKilometers()));
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

    private void btnAddLisnerer(){
        Button addRefule = (Button) findViewById(R.id.btnAdd);
        addRefule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Refule refule = getRefuleData(view);
                global.addData(refule);
                goToMainActivity();
            }
        });
    }

    private Refule getRefuleData(View v) {
        Refule refule = new Refule();

        EditText valueSubBilling = (EditText) findViewById(R.id.editSub_billing);
        refule.subBilling = Integer.valueOf(valueSubBilling.getText().toString());

        EditText valueLiters = (EditText) findViewById(R.id.editLiters);
        refule.liters = Integer.valueOf(valueLiters.getText().toString());

        EditText valuePrice = (EditText) findViewById(R.id.editPrice);
        refule.price = Double.valueOf(valuePrice.getText().toString());

        EditText valueCombustion = (EditText) findViewById(R.id.editCombustion);
        refule.combustionPC = Double.valueOf(valueCombustion.getText().toString());

        EditText valueAvgSpeed = (EditText) findViewById(R.id.editAvgSpeed);
        refule.avg_speed = Integer.valueOf(valueAvgSpeed.getText().toString());

        EditText valuePetrolStation = (EditText) findViewById(R.id.editPetrolStation);
        refule.petrolStation = valuePetrolStation.getText().toString();

        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
        refule.date = new Date();

        return refule;
    }

    private void goToMainActivity() {
        Intent mainActivity = new Intent();
        mainActivity.setClass(this, TestActivity.class);
        //mainActivity.putExtra("refule", refuleData);

        startActivity(mainActivity);
    }
}
