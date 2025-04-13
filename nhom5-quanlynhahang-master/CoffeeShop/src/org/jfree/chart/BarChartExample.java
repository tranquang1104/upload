package org.jfree.chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

public class BarChartExample {

    public static JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
            "Sample Bar Chart",          // Title
            "Category Axis",             // X-axis Label
            "Value Axis",                // Y-axis Label
            dataset,                     // Dataset
            PlotOrientation.VERTICAL,    // Orientation
            true,                        // Show legend
            true,                        // Show tooltips
            false                        // URLs
        );
    }
}

