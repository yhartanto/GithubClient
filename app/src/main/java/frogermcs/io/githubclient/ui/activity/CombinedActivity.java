package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.view.RepositoriesView;
import frogermcs.io.githubclient.utils.AnalyticsManager;


public class CombinedActivity extends BaseActivity {

    @Bind(R.id.repositories)
    RepositoriesView repositoriesView;

    @Inject
    AnalyticsManager analyticsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        GithubClientApplication.get(this).getUserComponent().inject(this);
    }

    @OnItemClick(R.id.list)
    public void onRepositoryClick(int position) {
        Repository repository = repositoriesView.getItem(position);
        RepositoryDetailsActivity.startWithRepository(repository, this);
    }

    @Override
    public void finish() {
        super.finish();
        GithubClientApplication.get(this).releaseUserComponent();
    }
}
