package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.UserComponent;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.component.ActivityComponentProvider;
import frogermcs.io.githubclient.ui.activity.component.RepositoriesListActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;
import frogermcs.io.githubclient.ui.adapter.RepositoriesListAdapter;
import frogermcs.io.githubclient.ui.view.RepositoriesView;
import frogermcs.io.githubclient.utils.AnalyticsManager;


public class CombinedActivity extends BaseActivity implements RepositoriesListUI, DetailUi,
        ActivityComponentProvider<RepositoriesListActivityComponent> {

    @Bind(R.id.repositories)
    RepositoriesView repositoriesView;

    @Bind(R.id.tvUserName)
    TextView tvUserName;

    @Inject
    RepositoriesListActivityPresenter listPresenter;

    @Inject
    RepositoryDetailsActivityPresenter detailPresenter;

    @Inject
    AnalyticsManager analyticsManager;

    private RepositoriesListActivityComponent listActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);
        ButterKnife.bind(this);
        listPresenter.loadRepositories(this);
        detailPresenter.init();
    }

    @Override
    protected void setupActivityComponent() {
        final RepositoriesListActivityModule module = new RepositoriesListActivityModule();
        final UserComponent userComponent = GithubClientApplication.get(this).getUserComponent();
        listActivityComponent = userComponent.plus(module);

        userComponent.plus(module, new RepositoryDetailsActivityModule(this))
                .inject(this);
    }

    @Override
    public void showLoading(boolean loading) {
        repositoriesView.showLoading(loading);
    }

    @Override
    public void setRepositories(ImmutableList<Repository> repositories) {
        repositoriesView.setRepositories(repositories);
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
    public void setupUserName(final String userName) {
        tvUserName.setText(getString(R.string.repositories_of, userName));
    }

    @Override
    public RepositoriesListActivityComponent getActivityComponent() {
        return listActivityComponent;
    }
}
