package frogermcs.io.dagger2metrics.internal;

import android.content.res.Resources;

/**
 * Created by Miroslaw Stanek on 25.01.2016.
 */
public class Utils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
