package frogermcs.io.githubclient.data;

import dagger.Subcomponent;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.ui.activity.CombinedActivity;
import frogermcs.io.githubclient.ui.activity.RepositoryDetailsActivity;
import frogermcs.io.githubclient.ui.activity.component.RepositoriesListActivityComponent;
import frogermcs.io.githubclient.ui.activity.component.RepositoryDetailsActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;

/**
 * Created by Miroslaw Stanek on 23.06.15.
 */
@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {

    RepositoriesListActivityComponent plus(RepositoriesListActivityModule module);

    RepositoryDetailsActivityComponent plus(RepositoryDetailsActivityModule module);

    CombinedActivity inject(CombinedActivity combinedActivity);

    RepositoryDetailsActivity inject(RepositoryDetailsActivity repositoryDetailsActivity);
}