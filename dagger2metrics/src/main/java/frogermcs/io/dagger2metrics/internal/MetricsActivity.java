package frogermcs.io.dagger2metrics.internal;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import frogermcs.io.dagger2metrics.R;

/**
 * Created by Miroslaw Stanek on 25.01.2016.
 */
public class MetricsActivity extends Activity {

    private LinearLayout vRootView;
    List<MetricDescription> metricsDescriptions = new ArrayList<>();

    int paddingStep = Utils.dpToPx(12);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);
        vRootView = (LinearLayout) findViewById(R.id.vRootView);
        InitManager.getInstance().printTree();

        for (InitMetric initMetric : InitManager.getInstance().initializedMetrics.values()) {
            getDescriptionForItemAndArgs(initMetric, 0);
        }

        for (MetricDescription metricDescription : metricsDescriptions) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (metricDescription.isRoot) {
                textView.setTypeface(null, Typeface.BOLD);
            }
            if (metricDescription.depth > 1) {
                textView.setPadding((metricDescription.depth - 1) * paddingStep, 0, 0, 0);
            }
            textView.setText(metricDescription.description);
            vRootView.addView(textView);
        }
    }

    private void getDescriptionForItemAndArgs(InitMetric initMetric, int depth) {
        String description = initMetric.getClassName() + " " + initMetric.initTimeMillis + "ms (" + initMetric.getTotalInitTime() + "ms)";
        metricsDescriptions.add(new MetricDescription(description, depth));
        for (InitMetric argInitMetric : initMetric.args) {
            getDescriptionForItemAndArgs(argInitMetric, depth + 1);
        }

        if (depth == 0) {
            metricsDescriptions.add(new MetricDescription(null, 0));
        }
    }
}
