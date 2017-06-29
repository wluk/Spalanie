package localhost.spalanie;

import android.provider.BaseColumns;

/**
 * Created by wluka on 27.06.2017.
 */

public interface RefuleTable extends BaseColumns {
    public static final String TABLE_NAME = "refule";

    public static final String ID = "Id_Refule";
    public static final String DATE = "DateRefule";
    public static final String STATION = "Station";
    public static final String SUB_BILLING = "SubBilling";
    public static final String LITERS = "Liters";
    public static final String PRICE = "Price";
    public static final String COMBUSTION = "Combustion";
    public static final String COMBUSTION_CAR = "CombustionCar";
    public static final String AVG_SPEED = "AvgSpeed";
    public static final String COMMENT = "Comment";
}
