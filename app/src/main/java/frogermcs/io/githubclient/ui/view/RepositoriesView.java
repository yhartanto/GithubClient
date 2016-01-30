package frogermcs.io.githubclient.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.RepositoriesListUI;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.adapter.RepositoriesListAdapter;

public class RepositoriesView extends LinearLayout implements RepositoriesListUI {
    private RepositoriesListAdapter adapter;

    @Bind(R.id.list)
    ListView lvRepositories;

    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;

    @Inject
    RepositoriesListActivityPresenter presenter;

    public RepositoriesView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public RepositoriesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RepositoriesView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.repositories, this);
        GithubClientApplication.get(getContext())
                .getUserComponent()
                .plus(new RepositoriesListActivityModule())
                .inject(this);

        ButterKnife.bind(this);

        adapter = new RepositoriesListAdapter(getContext(), new ArrayList<Repository>());
        lvRepositories.setAdapter(adapter);
        presenter.loadRepositories(this);
    }

    public Repository getItem(int index) {
        return adapter.getItem(index);
    }

    @Override
    public void setRepositories(final ImmutableList<Repository> repositories) {
        adapter.clear();
        adapter.addAll(repositories);
    }

    @Override
    public void showLoading(boolean loading) {
        lvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
