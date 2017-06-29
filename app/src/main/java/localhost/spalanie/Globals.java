package localhost.spalanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@SuppressWarnings("DefaultFileTemplate")
public class Globals {
    private static Globals instance;
    // Global variable
    private final List<Refuel> refuels;

    private Globals() {
        refuels = new ArrayList<>();
    }

    public static synchronized Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    public List<Refuel> getData() {
        return this.refuels;
    }

    public void addData(Refuel refuel) {
        this.refuels.add(refuel);

        Collections.sort(refuels, new Comparator<Refuel>() {
            public int compare(Refuel obj1, Refuel obj2) {
                // TODO Auto-generated method stub
                return (obj1.getId() < obj2.getId()) ? -1 : (obj1.getId() > obj2.getId()) ? 1 : 0;
            }
        });
    }

    public void addRefuels(ArrayList<Refuel> refuelArrayList) {
        refuels.addAll(refuelArrayList);
    }

    public int getRefuelCount() {
        return refuels.size();
    }

    public Refuel getRefuelByPosition(int position) {
        return refuels.get(position);
    }

    //region Stats

    public double getAvgCombustion() {
        double sum = 0, avgCombustion = 0;

        if (hasData()) {
            for (Refuel item : refuels
                    ) {
                sum += item.getCombustion();
            }
            avgCombustion = Math.round(((sum / refuels.size())) * 100.0) / 100.0;
        }

        return avgCombustion;
    }

    public double getAvgPrice() {
        double sum = 0, avgPrice = 0;

        if (hasData()) {
            for (Refuel item : refuels
                    ) {
                sum += item.getPrice();
            }
            avgPrice = Math.round(((sum / refuels.size())) * 100.0) / 100.0;
        }

        return avgPrice;
    }

    public double getAvgSubBilling() {
        double sum = 0, avgSubBilling = 0;

        if (hasData()) {
            for (Refuel item : refuels
                    ) {
                sum += item.getSubBilling();
            }
            avgSubBilling = Math.round(((sum / refuels.size())) * 100.0) / 100.0;
        }

        return avgSubBilling;
    }

    public double getAvgKilometers() {
        double sum = 0, avgKilometers = 0;

        if (hasData()) {
            for (Refuel item : refuels
                    ) {
                if (item.getLiters() >= 37) {
                    sum += item.getSubBilling();
                }
            }
            avgKilometers = Math.round(((sum / refuels.size())) * 100.0) / 100.0;
        }

        return avgKilometers;
    }

    public double getMaxCombustion() {
        double maxCombustion = 0;

        if (hasData()) {
            ArrayList<Double> combustionArrayList = new ArrayList<>();
            for (Refuel item : refuels
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
            ArrayList<Double> priceArrayList = new ArrayList<>();
            for (Refuel item : refuels
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
            ArrayList<Double> kilometersArrayList = new ArrayList<>();
            for (Refuel item : refuels
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
            ArrayList<Double> combustionArrayList = new ArrayList<>();
            for (Refuel item : refuels
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
            ArrayList<Double> priceArrayList = new ArrayList<>();
            for (Refuel item : refuels
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
        return !refuels.isEmpty();
    }
    //endregion
}
