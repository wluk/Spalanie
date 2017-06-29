package localhost.spalanie;

import java.sql.Ref;
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

//        Refule refule = new Refule();
//        refule.subBilling = 580;
//        refule.date = new Date();
//        refule.petrolStation = "BP BB Dworzec";
//        refule.avg_speed = 48;
//        refule.combustion = 7.6;
//        refule.liters = 44;
//        refule.price = 4.44;
//        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
//        refule.combustionPC = 4.8;
//        refules.add(refule);
//
//        refule = new Refule();
//        refule.subBilling = 600;
//        refule.date = new Date();
//        refule.petrolStation = "Shell Komorowice";
//        refule.avg_speed = 47;
//        refule.combustion = 7.4;
//        refule.liters = 47;
//        refule.price = 4.64;
//        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
//        refule.combustionPC = 4.6;
//        refules.add(refule);
//
//        refule = new Refule();
//        refule.subBilling = 525;
//        refule.date = new Date();
//        refule.petrolStation = "Shell Tychy Ktw";
//        refule.avg_speed = 39;
//        refule.combustion = 8.2;
//        refule.liters = 40;
//        refule.price = 4.32;
//        refule.combustion = Math.round(((refule.liters / (double) refule.subBilling) * 100) * 100.0) / 100.0;
//        refule.combustionPC = 5.1;
//        refules.add(refule);
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
            public int compare(Refule obj1, Refule obj2) {
                // TODO Auto-generated method stub
                return (obj1.getId() < obj2.getId()) ? -1 : (obj1.getId() > obj2.getId()) ? 1 : 0;
            }
        });
    }

    public void addRefules(ArrayList<Refule> refuleArrayList) {
        refules.addAll(refuleArrayList);
    }

    public int getRefuleCount() {
        return refules.size();
    }

    public Refule getRefuleByPosition(int position) {
        return refules.get(position);
    }

    //region Stats

    public double getAvgCombustion() {
        double sum = 0, avgCombustion = 0;

        if (hasData()) {
            for (Refule item : refules
                    ) {
                sum += item.getCombustion();
            }
            avgCombustion = Math.round(((sum / refules.size())) * 100.0) / 100.0;
        }

        return avgCombustion;
    }

    public double getAvgPrice() {
        double sum = 0, avgPrice = 0;

        if (hasData()) {
            for (Refule item : refules
                    ) {
                sum += item.getPrice();
            }
            avgPrice = Math.round(((sum / refules.size())) * 100.0) / 100.0;
        }

        return avgPrice;
    }

    public double getAvgSubBilling() {
        double sum = 0, avgSubBilling = 0;

        if (hasData()) {
            for (Refule item : refules
                    ) {
                sum += item.getSubBilling();
            }
            avgSubBilling = Math.round(((sum / refules.size())) * 100.0) / 100.0;
        }

        return avgSubBilling;
    }

    public double getAvgKilometers() {
        double sum = 0, avgKilometers = 0;

        if (hasData()) {
            for (Refule item : refules
                    ) {
                if (item.getLiters() >= 37) {
                    sum += item.getSubBilling();
                }
            }
            avgKilometers = Math.round(((sum / refules.size())) * 100.0) / 100.0;
        }

        return avgKilometers;
    }

    public double getMaxCombustion() {
        double maxCombustion = 0;

        if (hasData()) {
            ArrayList<Double> combustionArrayList = new ArrayList<Double>();
            for (Refule item : refules
                    ) {
                combustionArrayList.add(item.getCombustion());
            }
            maxCombustion = Collections.max(combustionArrayList);
        }
        return maxCombustion;
    }

    public double getMaxPrice() {
        double maxPrice = 0;
        if (hasData()) {
            ArrayList<Double> priceArrayList = new ArrayList<Double>();
            for (Refule item : refules
                    ) {

                priceArrayList.add(item.getPrice());
            }
            maxPrice = Collections.max(priceArrayList);
        }
        return maxPrice;
    }

    public double getMaxKilometers() {
        double maxKilometers = 0;
        if (hasData()) {
            ArrayList<Double> kilometersArrayList = new ArrayList<Double>();
            for (Refule item : refules
                    ) {
                kilometersArrayList.add(item.getSubBilling());
            }
            maxKilometers = Collections.max(kilometersArrayList);
        }
        return maxKilometers;
    }

    public double getMinCombustion() {
        double minCombustion = 0;
        if (hasData()) {
            ArrayList<Double> combustionArrayList = new ArrayList<Double>();
            for (Refule item : refules
                    ) {
                if (item.getCombustion() > 0) {
                    combustionArrayList.add(item.getCombustion());
                }
            }
            minCombustion = Collections.max(combustionArrayList);
        }
        return minCombustion;
    }

    public double getMinPrice() {
        double minPrice = 0;
        if (hasData()) {
            ArrayList<Double> priceArrayList = new ArrayList<Double>();
            for (Refule item : refules
                    ) {
                if (item.getPrice() > 0) {
                    priceArrayList.add(item.getPrice());
                }
            }
            minPrice = Collections.min(priceArrayList);
        }
        return minPrice;
    }

    private boolean hasData() {
        return !refules.isEmpty();
    }

    private enum StatsData {
        AvgCombustion, AvgPrice, AvgSubBilling, AvgKilometers, MaxCombustion, MaxPrice, MaxKilometers, MinCombustion, MinPrice
    }

    //endregion
}
