package codenvy.client;

import codenvy.client.mainPaige.MainPageView;
import codenvy.client.mainPaige.MainPageViewImpl;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.inject.Named;

public class AppGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainPageView.class).to(MainPageViewImpl.class);

        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    @Named("myString")
    protected String provideString() {
        return "User";
    }

}
