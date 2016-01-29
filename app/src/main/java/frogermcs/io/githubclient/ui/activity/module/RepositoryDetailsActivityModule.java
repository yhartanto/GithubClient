package frogermcs.io.githubclient.ui.activity.module;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.DetailUi;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoryDetailsActivityModule {
    private DetailUi detailUi;

    public RepositoryDetailsActivityModule(DetailUi detailUi) {
        this.detailUi = detailUi;
    }

    @Provides
    @ActivityScope
    DetailUi provideRepositoryDetailsActivity() {
        return detailUi;
    }

    @Provides
    @ActivityScope
    RepositoryDetailsActivityPresenter provideRepositoryDetailsActivityPresenter(User user) {
        return new RepositoryDetailsActivityPresenter(detailUi, user);
    }
}