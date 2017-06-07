package localhost.spalanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wluka on 08.06.2017.
 */

public class Globals {
    private static Globals instance;

    private Globals() {
        refules = new ArrayList<Refule>();
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
