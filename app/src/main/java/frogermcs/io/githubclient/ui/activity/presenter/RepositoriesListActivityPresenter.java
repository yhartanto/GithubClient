package frogermcs.io.githubclient.ui.activity.presenter;

import com.google.common.collect.ImmutableList;

import java.lang.ref.WeakReference;

import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.RepositoriesListUI;
import frogermcs.io.githubclient.utils.SimpleObserver;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepositoriesListActivityPresenter {

    private RepositoriesManager repositoriesManager;

    public RepositoriesListActivityPresenter(RepositoriesManager repositoriesManager) {
        this.repositoriesManager = repositoriesManager;
    }

    public void loadRepositories(final RepositoriesListUI repositoriesListUI) {
        final WeakReference<RepositoriesListUI> ui = new WeakReference<>(repositoriesListUI);
        ui.get().showLoading(true);
        repositoriesManager.getUsersRepositories().subscribe(new SimpleObserver<ImmutableList<Repository>>() {
            @Override
            public void onNext(ImmutableList<Repository> repositories) {
                if (ui.get() != null) {
                    ui.get().showLoading(false);
                    ui.get().setRepositories(repositories);
                }
            }

            @Override
            public void onError(Throwable e) {
                ui.get().showLoading(false);
            }
        });
    }

}
