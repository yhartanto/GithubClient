package frogermcs.io.dagger2metrics.internal.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import frogermcs.io.dagger2metrics.R;

/**
 * Created by Miroslaw Stanek on 25.01.2016.
 */
public class AboutMetricsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_metrics);
    }
}
