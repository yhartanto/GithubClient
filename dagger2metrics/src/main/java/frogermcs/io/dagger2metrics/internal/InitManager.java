package frogermcs.io.dagger2metrics.internal;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import frogermcs.io.dagger2metrics.aspect.GraphAnalyzer;

/**
 * Created by Miroslaw Stanek on 24.01.2016.
 */
public class InitManager {
    private static class Holder {
        static final InitManager INSTANCE = new InitManager();
    }

    public static InitManager getInstance() {
        return Holder.INSTANCE;
    }

    public final Map<String, InitMetric> initializedMetrics = new HashMap<>();

    public void addInitMetric(Class<?> initializedClass, Object[] args, long initTimeMillis) {
        InitMetric initMetric = new InitMetric();
        initMetric.initTimeMillis = initTimeMillis;
        initMetric.cls = initializedClass;

        initializedMetrics.put(initializedClass.getSimpleName(), initMetric);

        for (Object object : args) {
            String simpleName = object.getClass().getSimpleName();
            InitMetric argMethics = initializedMetrics.get(simpleName);
            if (argMethics != null) {
                initMetric.args.add(argMethics);
                initializedMetrics.remove(simpleName);
            }
        }
    }

    public void printTree() {
        for (InitMetric metric : initializedMetrics.values()) {
            Log.d(GraphAnalyzer.TAG, metric.getClassName() + " init: " + metric.getTotalInitTime() + "ms");
            Log.d(GraphAnalyzer.TAG, "===");
            metric.printArgsAndInitTime(0);
            Log.d(GraphAnalyzer.TAG, "===");
            Log.d(GraphAnalyzer.TAG, "   ");
        }
    }
}
