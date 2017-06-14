package localhost.spalanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by wluka on 08.06.2017.
 */

public class Globals {
    private static Globals instance;

    private Globals() {

        refules = new ArrayList<Refule>();

        Refule refule = new Refule();
        refule.subBilling = 580;
        refule.date = new Date();
        refule.petrolStation = "BP BB Dworzec";
        refule.avg_seppd = 48;
        refule.combustion = 7.6;
        refule.liters = 44;
        refule.price = 4.44;
        refules.add(refule);

        refule.subBilling = 600;
        refule.date = new Date();
        refule.petrolStation = "Shell Komorowice";
        refule.avg_seppd = 47;
        refule.combustion = 7.4;
        refule.liters = 47;
        refule.price = 4.64;
        refules.add(refule);

        refule.subBilling = 525;
        refule.date = new Date();
        refule.petrolStation = "Shell Tychy Ktw";
        refule.avg_seppd = 39;
        refule.combustion = 8.2;
        refule.liters = 40;
        refule.price = 4.32;
        refules.add(refule);
    }

    public static synchronized Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    // Global variable
    private List<Refule> refules;

    public List<Refule> getData() {
        return this.refules;
    }

    public void addData(Refule refule) {
        this.refules.add(refule);

        Collections.sort(refules, new Comparator<Refule>() {
            @Override
            public int compare(Refule o1, Refule o2) {
                return o2.date.compareTo(o1.date);
            }
        });
    }


}
