package localhost.spalanie;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wluka on 06.06.2017.
 */

class Refule implements Serializable {
    public String petrolStation;
    public Integer subBilling;
    public Integer liters;
    public Double price;
    public Double combustion;
    public Integer avg_seppd;
    public Date date;
}