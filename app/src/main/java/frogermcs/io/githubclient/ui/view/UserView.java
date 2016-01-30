package frogermcs.io.githubclient.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import javax.inject.Inject;

import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.ui.activity.DetailUi;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;

public class UserView extends TextView implements DetailUi {

    @Inject
    RepositoryDetailsActivityPresenter presenter;

    public UserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        GithubClientApplication.get(getContext())
                .getUserComponent()
                .plus(new RepositoryDetailsActivityModule(this))
                .inject(this);
        presenter.init();
    }

    @Override
    public void setupUserName(final String userName) {
        setText(userName);
    }
}
