package localhost.spalanie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

interface DBName {
    String DATA_BASE_NAME = "CombustionDB.db";
}

class DBHelper extends SQLiteOpenHelper {
    private static final int DATA_BASE_VER = 1;

    public DBHelper(Context context) {
        super(context, DBName.DATA_BASE_NAME, null, DATA_BASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RefuelTable.TABLE_NAME + "( " + RefuelTable.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + RefuelTable.DATE + " TEXT, " + RefuelTable.STATION + " TEXT, " + RefuelTable.SUB_BILLING + " REAL, " + RefuelTable.LITERS + " REAL, " + RefuelTable.PRICE + " REAL, " + RefuelTable.COMBUSTION + " REAL, " + RefuelTable.COMBUSTION_CAR + " REAL, " + RefuelTable.AVG_SPEED + " REAL, " + RefuelTable.COMMENT + " TEXT );");

        seedData(db);
    }

    private void seedData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-06-05','Shell Komorowice',0,44,4.53,0,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-07-02','Shell Komorowice',460.9,42.79,4.49,9.28,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-08-08','Shell Komorowice',463.1,40.09,4.23,8.66,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-08-08','0',0,0,0,0,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-09-03','BP w Białej',504,45,4.89,8.93,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-09-23','Orlen Katowice',540.1,44.41,4.49,8.22,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-10-21','BP Dworzec',425.7,43,4.59,10.1,9.4,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-10-22','BP w Białej',129,10,4.57,7.75,6.5,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-10-22','BP Dworzec',128.6,7,4.57,5.44,6.1,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-11-11','Shell Komorowice',520.8,45,4.48,8.64,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-11-29','Shell Komorowice',585.2,47,4.49,8.03,7.8,45,'24.11 zmiana opon');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2016-12-20','BP w Białej',568.2,46,4.94,8.1,8.1,44,'wymiana hamulcy');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-01-18','Shell Tychy BB',516.7,46,5.19,8.9,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-02-09','Shell Warszawska BB',558.1,45.03,5.63,8.07,8,45,'45 485 zmiana aku');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-03-02','Shell Warszawska BB',516,43.94,4.83,8.52,8.2,42,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-03-18','Shell Tychy BB',565.3,47,4.59,8.31,7.8,0,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-04-02','Shell Tychy BB',628.5,46,4.47,7.32,7.3,46,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-04-12','BP Rybna',682.7,46,4.68,6.74,6.6,49,'Ailleron + Sosnowic');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-04-30','Shell Tychy Ktw',607.1,47.01,4.61,7.74,7.6,53,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-05-20','Shell Komorowice',563.3,44,4.45,7.81,7.5,47,'0');");
        db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('2017-06-10','Shell Tychy Ktw',583.8,47,4.42,8.05,7.5,48,'0');");
    }

    private boolean insertRefuel(SQLiteDatabase db, Refuel insertRefuel) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date = dateFormat.format(insertRefuel.getDate());

            db.execSQL("INSERT INTO " + RefuelTable.TABLE_NAME + " (" + RefuelTable.DATE + "," + RefuelTable.STATION + "," + RefuelTable.SUB_BILLING + "," + RefuelTable.LITERS + "," + RefuelTable.PRICE + "," + RefuelTable.COMBUSTION + "," + RefuelTable.COMBUSTION_CAR + "," + RefuelTable.AVG_SPEED + "," + RefuelTable.COMMENT + ") VALUES ('" + date + "','" + insertRefuel.getPetrolStation() + "'," + insertRefuel.getSubBilling() + "," + insertRefuel.getLiters() + "," + insertRefuel.getPrice() + "," + insertRefuel.getCombustion() + "," + insertRefuel.getCombustionPC() + "," + insertRefuel.getAvg_speed() + "," + insertRefuel.getComment() + ");");

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void addRefuel(SQLiteDatabase db, Refuel addedRefuel) {
        boolean isInserted = insertRefuel(db, addedRefuel);
        if (isInserted) {
            FileHelper file = new FileHelper();
            file.exportTOExternalStorage();
        }
    }

    public void dropDatabase(Context context){
        context.deleteDatabase(DBName.DATA_BASE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RefuelTable.TABLE_NAME);
        onCreate(db);
    }
}
