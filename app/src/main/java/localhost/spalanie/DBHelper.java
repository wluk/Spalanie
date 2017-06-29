package localhost.spalanie;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATA_BASE_NAME = "CombustionDB.db";
    public static final int DATA_BASE_VER = 1;

    public DBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RefuleTable.TABLE_NAME + "( " + RefuleTable.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + RefuleTable.DATE + " TEXT, " + RefuleTable.STATION + " TEXT, " + RefuleTable.SUB_BILLING + " REAL, " + RefuleTable.LITERS + " REAL, " + RefuleTable.PRICE + " REAL, " + RefuleTable.COMBUSTION + " REAL, " + RefuleTable.COMBUSTION_CAR + " REAL, " + RefuleTable.AVG_SPEED + " REAL, " + RefuleTable.COMMENT + " TEXT );");
        seedData(db);

    }

    public void seedData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-06-05','Shell Komorowice',0,44,4.53,0,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-07-02','Shell Komorowice',460.9,42.79,4.49,9.28,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-08-08','Shell Komorowice',463.1,40.09,4.23,8.66,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-08-08','0',0,0,0,0,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-09-03','BP w Białej',504,45,4.89,8.93,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-09-23','Orlen Katowice',540.1,44.41,4.49,8.22,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-10-21','BP Dworzec',425.7,43,4.59,10.1,9.4,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-10-22','BP w Białej',129,10,4.57,7.75,6.5,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-10-22','BP Dworzec',128.6,7,4.57,5.44,6.1,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-11-11','Shell Komorowice',520.8,45,4.48,8.64,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-11-29','Shell Komorowice',585.2,47,4.49,8.03,7.8,45,'24.11 zmiana opon');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2016-12-20','BP w Białej',568.2,46,4.94,8.1,8.1,44,'wymiana hamulcy');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-01-18','Shell Tychy BB',516.7,46,5.19,8.9,0,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-02-09','Shell Warszawska BB',558.1,45.03,5.63,8.07,8,45,'45 485 zmiana aku');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-03-02','Shell Warszawska BB',516,43.94,4.83,8.52,8.2,42,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-03-18','Shell Tychy BB',565.3,47,4.59,8.31,7.8,0,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-04-02','Shell Tychy BB',628.5,46,4.47,7.32,7.3,46,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-04-12','BP Rybna',682.7,46,4.68,6.74,6.6,49,'Ailleron + Sosnowic');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-04-30','Shell Tychy Ktw',607.1,47.01,4.61,7.74,7.6,53,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-05-20','Shell Komorowice',563.3,44,4.45,7.81,7.5,47,'0');");
        db.execSQL("INSERT INTO " + RefuleTable.TABLE_NAME + " (" + RefuleTable.DATE + "," + RefuleTable.STATION + "," + RefuleTable.SUB_BILLING + "," + RefuleTable.LITERS + "," + RefuleTable.PRICE + "," + RefuleTable.COMBUSTION + "," + RefuleTable.COMBUSTION_CAR + "," + RefuleTable.AVG_SPEED + "," + RefuleTable.COMMENT + ") VALUES ('2017-06-10','Shell Tychy Ktw',583.8,47,4.42,8.05,7.5,48,'0');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RefuleTable.TABLE_NAME);
        onCreate(db);
    }
}
