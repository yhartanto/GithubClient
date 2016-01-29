package frogermcs.io.githubclient.ui.activity.module;

import dagger.Module;

@Module(includes = {RepositoriesListActivityModule.class, RepositoryDetailsActivityModule.class})
public class CombinedModule {
}