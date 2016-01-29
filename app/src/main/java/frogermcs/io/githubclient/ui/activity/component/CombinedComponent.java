package frogermcs.io.githubclient.ui.activity.component;

import dagger.Subcomponent;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.CombinedActivity;
import frogermcs.io.githubclient.ui.activity.module.CombinedModule;

@ActivityScope
@Subcomponent(
        modules = CombinedModule.class
)
public interface CombinedComponent extends RepositoriesListActivityComponent {

    CombinedActivity inject(CombinedActivity combinedActivity);
}