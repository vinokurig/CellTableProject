package codenvy.client;

import codenvy.client.mvp.presenters.MainPaigePresenterImpl;
import codenvy.client.mvp.views.MainPageViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    public void onModuleLoad() {
        Resources.IMPL.Styles().ensureInjected();

        SimpleEventBus eventBus = new SimpleEventBus();

        final MainPaigePresenterImpl presenter = new MainPaigePresenterImpl(new MainPageViewImpl(eventBus), eventBus);

        presenter.go(RootLayoutPanel.get());
    }
}
