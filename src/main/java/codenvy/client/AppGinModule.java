package codenvy.client;

import codenvy.client.mainPage.MainPageView;
import codenvy.client.mainPage.MainPageViewImpl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class AppGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainPageView.class).to(MainPageViewImpl.class);

        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    }
}
