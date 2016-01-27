package frogermcs.io.dagger2metrics.internal;

import java.util.Set;

/**
 * Created by Miroslaw Stanek on 25.01.2016.
 */
public class MetricDescription {
    public String className;
    public String formattedInitTime;
    public String treeDescription;

    public MetricDescription() {
    }

    public static MetricDescription InitFromMetric(InitMetric initMetric) {
        MetricDescription metricDescription = new MetricDescription();
        metricDescription.className = initMetric.getClassName();
        metricDescription.formatInitTime(initMetric.getTotalInitTime(), initMetric.getInitTimeWithoutArgs());
        metricDescription.treeDescription = metricDescription.formatDescriptionTree(initMetric.args, 0);
        return metricDescription;
    }

    private void formatInitTime(long overallInitTime, long noArgsInitTime) {
        StringBuilder sb = new StringBuilder("Initialization: ");
        sb.append(noArgsInitTime);
        sb.append("ms, ");
        if (noArgsInitTime >= 0) {
            sb.append("with args: ");
            sb.append(overallInitTime);
            sb.append("ms");
        } else {
            sb.append("no-args");
        }
        formattedInitTime = sb.toString();
    }

    private String formatDescriptionTree(Set<InitMetric> initMetrics, int depthLevel) {
        StringBuilder tree = new StringBuilder();
        for (InitMetric initMetric : initMetrics) {
            String depthStr = new String(new char[depthLevel]).replace("\0", "\t\t");
            StringBuilder sb = new StringBuilder(depthStr);
            sb.append("|__<b>");
            sb.append(initMetric.getClassName());
            sb.append("</b>: ");
            sb.append(initMetric.getInitTimeWithoutArgs());
            if (initMetric.args.size() > 0) {
                sb.append("ms, with args: ");
                sb.append(initMetric.getTotalInitTime());
                sb.append("ms");
                sb.append("<br>");
                sb.append(formatDescriptionTree(initMetric.args, depthLevel + 1));
            } else {
                sb.append("ms, no-args");
                sb.append("<br>");
            }

            tree.append(sb);
        }

        return tree.toString();
    }
}
