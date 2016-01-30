package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.component.ActivityComponentProvider;
import frogermcs.io.githubclient.ui.activity.component.RepositoriesListActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.view.RepositoriesView;
import frogermcs.io.githubclient.utils.AnalyticsManager;

public class RepositoriesListActivity extends BaseActivity
        implements ActivityComponentProvider<RepositoriesListActivityComponent> {

    @Bind(R.id.repositories)
    RepositoriesView repositoriesView;

    @Inject
    AnalyticsManager analyticsManager;

    private RepositoriesListActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        component = GithubClientApplication.get(this).getUserComponent()
                .plus(new RepositoriesListActivityModule());

        component.inject(this);
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

    @Override
    public RepositoriesListActivityComponent getActivityComponent() {
        return component;
    }
}
