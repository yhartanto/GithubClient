package frogermcs.io.githubclient.ui.activity.component;

import dagger.Subcomponent;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;
import frogermcs.io.githubclient.ui.view.UserView;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = RepositoryDetailsActivityModule.class
)
public interface RepositoryDetailsActivityComponent {
    UserView inject(UserView userView);
}