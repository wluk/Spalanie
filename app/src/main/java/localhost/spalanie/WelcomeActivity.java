package localhost.spalanie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initDatabase();
        dbReadRefuel();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent addRefuel = new Intent();
                addRefuel.setClass(WelcomeActivity.this, MainActivity.class);
                startActivity(addRefuel);
                finish();
            }
        }, 1000);
    }

    private void initDatabase(){
        FileHelper file = new FileHelper();
        file.initialDatabase();
    }

    private void dbReadRefuel() {

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        //dbHelper.dropDatabase(getApplicationContext());

        try {
            SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

            dbHelper.onUpgrade(dbRead, 6, 7);

            Cursor c = dbRead.query(RefuelTable.TABLE_NAME, new String[]{RefuelTable.ID, RefuelTable.DATE, RefuelTable.STATION, RefuelTable.SUB_BILLING, RefuelTable.LITERS, RefuelTable.PRICE, RefuelTable.COMBUSTION, RefuelTable.COMBUSTION_CAR, RefuelTable.AVG_SPEED, RefuelTable.COMMENT}, null, null, null, null, null, null);

            try {
                ArrayList<Refuel> refuels = new ArrayList<>();
                while (c.moveToNext()) {
                    Refuel tmpRefuel = new Refuel();
                    tmpRefuel.setId(Integer.parseInt(c.getString(0)));
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                    Date date = dateFormat.parse(c.getString(1));
                    tmpRefuel.setDate(date);
                    tmpRefuel.setPetrolStation(c.getString(2));
                    tmpRefuel.setSubBilling(Double.parseDouble(c.getString(3)));
                    tmpRefuel.setLiters(Double.parseDouble(c.getString(4)));
                    tmpRefuel.setPrice(Double.parseDouble(c.getString(5)));
                    tmpRefuel.setCombustion(Double.parseDouble(c.getString(6)));
                    tmpRefuel.setCombustionPC(Double.parseDouble(c.getString(7)));
                    tmpRefuel.setAvg_speed(Integer.parseInt(c.getString(8)));
                    tmpRefuel.setComment(c.getString(9));

                    refuels.add(tmpRefuel);
                }
                Globals global = Globals.getInstance();
                global.addRefuels(refuels);
                global.setAllPrices();
                global.setAllCombustion();
            } catch (Exception e) {
                System.out.print(e.toString());
            } finally {
                c.close();
            }

        } catch (SQLiteException ex) {
            System.out.println(ex);
        } finally {
            dbHelper.close();
        }
    }
}
