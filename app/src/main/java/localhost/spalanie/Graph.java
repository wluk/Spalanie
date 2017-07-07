package localhost.spalanie;

import android.graphics.Color;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Graph {

    private final Globals global;

    public Graph() {
        global = Globals.getInstance();
    }

    public void graphPrices(GraphView line_graph) {
        // Line Graph
        line_graph.addTouchables(new ArrayList<View>());
        line_graph.removeAllSeries();
        ArrayList<DataPoint> prices = global.getPrices();
        DataPoint[] points = prices.toArray(new DataPoint[0]);
        LineGraphSeries<DataPoint> line_series = new LineGraphSeries<>(points);

        line_graph.addSeries(line_series);
        // set manual X bounds
        line_graph.getViewport().setXAxisBoundsManual(false);
        line_graph.getViewport().setMinX(0);
        line_graph.getViewport().setMaxX(prices.size());

        // set manual Y bounds
        line_graph.getViewport().setYAxisBoundsManual(true);
        line_graph.getViewport().setMinY(global.getMinPrice());
        line_graph.getViewport().setMaxY(global.getMaxPrice());

        line_graph.getViewport().setScalable(true);
        line_graph.getViewport().setScalableY(true);

        line_graph.getViewport().setScrollable(true);
        line_series.setDrawBackground(true);
        line_series.setBackgroundColor(Color.BLUE);
        line_series.setThickness(2);
    }

    public void graphCombustion(GraphView line_graph) {
        // Line Graph
        line_graph.addTouchables(new ArrayList<View>());
        line_graph.removeAllSeries();
        ArrayList<DataPoint> combustion = global.getCombustion();

        DataPoint[] points = combustion.toArray(new DataPoint[0]);
        LineGraphSeries<DataPoint> line_series = new LineGraphSeries<>(points);

        line_graph.addSeries(line_series);

        // set manual X bounds
        line_graph.getViewport().setXAxisBoundsManual(true);
        line_graph.getViewport().setMinX(0);
        line_graph.getViewport().setMaxX(combustion.size());

        // set manual Y bounds
        line_graph.getViewport().setYAxisBoundsManual(true);
        line_graph.getViewport().setMinY(global.getMinCombustion());
        line_graph.getViewport().setMaxY(global.getMaxCombustion());

        line_graph.getViewport().setScalable(true);
        line_graph.getViewport().setScalableY(true);

        line_graph.getViewport().setScrollable(true);
        line_series.setDrawBackground(true);
        line_series.setBackgroundColor(Color.YELLOW);
        line_series.setThickness(5);
    }

    public void graphPrices2(GraphView graph) {

        ArrayList<DataPoint> prices = global.getPrices();
        DataPoint[] points = prices.toArray(new DataPoint[0]);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });

        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.resetData(points);
//series.setValuesOnTopSize(50);
    }
}
