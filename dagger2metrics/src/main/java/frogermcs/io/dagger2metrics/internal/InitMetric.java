package frogermcs.io.dagger2metrics.internal;

import android.util.Log;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import frogermcs.io.dagger2metrics.aspect.GraphAnalyzer;

/**
 * Created by Miroslaw Stanek on 23.01.2016.
 */
public class InitMetric {

    public long initTimeMillis = 0;
    public Class<?> cls;
    public Set<InitMetric> args = new HashSet<>();

    public long getTotalInitTime() {
        long total = 0;
        for (InitMetric initMetric : args) {
            total += initMetric.initTimeMillis;
        }
        return total;
    }

    public void printArgsAndInitTime(int depth) {
        String depthStr = new String(new char[depth]).replace("\0", "-");
        Log.d(GraphAnalyzer.TAG, depthStr + getClassName() + ", self(args) init time: " + initTimeMillis + "ms (" + getTotalInitTime() + "ms)");
        for (InitMetric initMetric : args) {
            initMetric.printArgsAndInitTime(depth + 1);
        }
    }

    public String getClassName() {
        if (Proxy.isProxyClass(cls)) {
            final Class<?>[] interfaces = cls.getInterfaces();
            if (interfaces.length == 1) {
                return interfaces[0].getSimpleName();
            } else {
                return Arrays.asList(interfaces).toString();
            }
        } else {
            return cls.getSimpleName();
        }
    }

    @Override
    public String toString() {
        if (Proxy.isProxyClass(cls)) {
            return "InitMetric{" +
                    "initTimeMillis=" + initTimeMillis +
                    ", cls=" + Arrays.asList(cls.getInterfaces()) +
                    ", args=" + args +
                    '}';
        } else {
            return "InitMetric{" +
                    "initTimeMillis=" + initTimeMillis +
                    ", cls=" + cls.getSimpleName() +
                    ", args=" + args +
                    '}';
        }
    }
}
