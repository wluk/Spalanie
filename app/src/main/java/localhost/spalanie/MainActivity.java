package localhost.spalanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        global.addData(refule);


        TextView test = (TextView) findViewById(R.id.textView2);
        test.setText(test.getText() + " " + refule.subBilling.toString());
    }

    public void goToAddRefule() {
        Intent addActivity = new Intent();
        addActivity.setClass(this, AddActivity.class);

        startActivity(addActivity);
    }
}
