package codenvy.client;

import codenvy.client.mainPage.MainPagePresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    private final AppGinInjector injector = GWT.create(AppGinInjector.class);

    public void onModuleLoad() {
        injector.getResources().styles().ensureInjected();

        MainPagePresenter mainPagePresenter = injector.getMainPagePresenter();

        mainPagePresenter.go(RootLayoutPanel.get());
    }
}
