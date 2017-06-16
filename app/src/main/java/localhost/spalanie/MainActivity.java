package localhost.spalanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.Inflater;

import android.view.LayoutInflater;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Globals global = Globals.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tere feree", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                goToAddRefule();
            }
        });

        Refule refule = (Refule) getIntent().getSerializableExtra("refule");
        if (refule != null) {
            global.addData(refule);
        }

        final ArrayList<Refule> list = new ArrayList<Refule>();
        for (Refule item : global.getData()) {
            list.add(item);
        }

        ListView listRefule = (ListView) findViewById(R.id.list);

        final RefuleAdapter adapter = new RefuleAdapter(this, list);
        listRefule.setAdapter(adapter);

        listRefule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Refule model = (Refule) adapter.getItem(position);

                goToSingleRefuleActivity(model);
            }
        });


    }

    public void goToAddRefule() {
        Intent addActivity = new Intent();
        addActivity.setClass(this, Test2Activity.class);

        startActivity(addActivity);
    }

    public void goToSingleRefuleActivity(Refule refule) {
        Intent singleRefuleActivity = new Intent();
        singleRefuleActivity.setClass(this, SingleRefuleActivity.class);

        singleRefuleActivity.putExtra("refule", refule);

        startActivity(singleRefuleActivity);
    }
}
