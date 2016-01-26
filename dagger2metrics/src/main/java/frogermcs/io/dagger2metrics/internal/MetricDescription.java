package frogermcs.io.dagger2metrics.internal;

/**
 * Created by Miroslaw Stanek on 25.01.2016.
 */
public class MetricDescription {
    public String description;
    public int depth;
    boolean isRoot;

    public MetricDescription(String description, int depth) {
        this(description, depth, depth == 0);
    }

    public MetricDescription(String description, int depth, boolean isRoot) {
        this.description = description;
        this.depth = depth;
        this.isRoot = isRoot;

        if (!isRoot) {
            this.description = "|_" + description;
        }
    }
}
