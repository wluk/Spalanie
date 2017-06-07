package localhost.spalanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
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
                Refule refule = getRefuleData(view);
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
        refule.subBilling = Integer.valueOf(valueSubBilling.getText().toString());

        EditText valueLiters = (EditText) findViewById(R.id.editLiters);
        refule.liters = Integer.valueOf(valueLiters.getText().toString());

        EditText valuePrice = (EditText) findViewById(R.id.editPrice);
        refule.price = Double.valueOf(valuePrice.getText().toString());

        EditText valueCombustion = (EditText) findViewById(R.id.editCombustion);
        refule.combustion = Double.valueOf(valueCombustion.getText().toString());

        EditText valueAvgSpeed = (EditText) findViewById(R.id.editAvgSpeed);
        refule.avg_seppd = Integer.valueOf(valueAvgSpeed.getText().toString());

        EditText valuePetrolStation = (EditText) findViewById(R.id.editPetrolStation);
        refule.petrolStation = valuePetrolStation.getText().toString();

        refule.date = new Date();

        return refule;
    }

    public void goToMainActivity(Refule refuleData) {
        Intent mainActivity = new Intent();
        mainActivity.setClass(this, MainActivity.class);
        mainActivity.putExtra("refule", refuleData);

        startActivity(mainActivity);
    }

    public void enter(View v) {
        ((Button) v).setText("test");
    }
}
