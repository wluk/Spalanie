package localhost.spalanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button addRefule = (Button) findViewById(R.id.btnAdd);
        addRefule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals global = Globals.getInstance();

                Refule refule = getRefuleData(view);

                refule.setId(global.getRefuleCount() + 1);
                global.addData(refule);
                goToMainActivity(refule);
            }
        });

//        Button btnAdd = (Button)findViewById(R.id.btnAdd);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Refule refule = getRefuleData(v);
//                goToMainActivity(refule);
//            }
//        });
    }

    public Refule getRefuleData(View v) {
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

        return refule;
    }

    public void goToMainActivity(Refule refuleData) {
        Intent mainActivity = new Intent();
        mainActivity.setClass(this, MainActivity.class);

        startActivity(mainActivity);
    }
}
