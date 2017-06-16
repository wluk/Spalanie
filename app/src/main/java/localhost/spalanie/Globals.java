package localhost.spalanie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
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
        refule.avg_speed = 48;
        refule.combustion = 7.6;
        refule.liters = 44;
        refule.price = 4.44;
        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
        refule.combustionPC = 4.8;
        refules.add(refule);

        refule = new Refule();
        refule.subBilling = 600;
        refule.date = new Date();
        refule.petrolStation = "Shell Komorowice";
        refule.avg_speed = 47;
        refule.combustion = 7.4;
        refule.liters = 47;
        refule.price = 4.64;
        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
        refule.combustionPC = 4.6;
        refules.add(refule);

        refule = new Refule();
        refule.subBilling = 525;
        refule.date = new Date();
        refule.petrolStation = "Shell Tychy Ktw";
        refule.avg_speed = 39;
        refule.combustion = 8.2;
        refule.liters = 40;
        refule.price = 4.32;
        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
        refule.combustionPC = 5.1;
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

    public int getRefuleCount() {
        return refules.size();
    }

    public Refule getRefuleByPosition(int position) {
        return refules.get(position);
    }

    public double getAvgCombustion() {
        double sum = 0;
        for (Refule item : refules
                ) {
            sum += item.combustion;
        }

        return Math.round(((sum / refules.size())) * 100.0) / 100.0;
    }

    public double getAvgPrice() {
        double sum = 0;
        for (Refule item : refules
                ) {
            sum += item.price;
        }

        return Math.round(((sum / refules.size())) * 100.0) / 100.0;
    }

    public double getAvgSubBilling() {
        double sum = 0;
        for (Refule item : refules
                ) {
            sum += item.subBilling;
        }

        return Math.round(((sum / refules.size())) * 100.0) / 100.0;
    }

    public double getAvgKilometers() {
        double sum = 0;
        for (Refule item : refules
                ) {
            if (item.liters >= 37) {
                sum += item.subBilling;
            }
        }

        return Math.round(((sum / refules.size())) * 100.0) / 100.0;
    }

    public double getMaxCombustion() {
        ArrayList<Double> combustionArrayList = new ArrayList<Double>();
        for (Refule item : refules
                ) {
            combustionArrayList.add(item.combustion);
        }
        return Collections.max(combustionArrayList);
    }

    public double getMaxPrice() {
        ArrayList<Double> priceArrayList = new ArrayList<Double>();
        for (Refule item : refules
                ) {
            priceArrayList.add(item.price);
        }
        return Collections.max(priceArrayList);
    }

    public double getMaxKilometers() {
        ArrayList<Integer> kilometersArrayList = new ArrayList<Integer>();
        for (Refule item : refules
                ) {
            kilometersArrayList.add(item.subBilling);
        }
        return Collections.max(kilometersArrayList);
    }

    public double getMinCombustion() {
        ArrayList<Double> combustionArrayList = new ArrayList<Double>();
        for (Refule item : refules
                ) {
            combustionArrayList.add(item.combustion);
        }
        return Collections.max(combustionArrayList);
    }

    public double getMinPrice() {
        ArrayList<Double> priceArrayList = new ArrayList<Double>();
        for (Refule item : refules
                ) {
            priceArrayList.add(item.price);
        }
        return Collections.min(priceArrayList);
    }
}
