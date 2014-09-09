package codenvy.client;

import codenvy.client.mainPage.MainPagePresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(AppGinModule.class)
public interface AppGinInjector extends Ginjector {
    MainPagePresenter getMainPagePresenter();

    Resources getResources();
}
