package localhost.spalanie;

import android.provider.BaseColumns;

interface RefuelTable extends BaseColumns {
    String TABLE_NAME = "refuel";

    String ID = "IdRefuel";
    String DATE = "DateRefuel";
    String STATION = "Station";
    String SUB_BILLING = "SubBilling";
    String LITERS = "Liters";
    String PRICE = "Price";
    String COMBUSTION = "Combustion";
    String COMBUSTION_CAR = "CombustionCar";
    String AVG_SPEED = "AvgSpeed";
    String COMMENT = "Comment";
}
