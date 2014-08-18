package codenvy.client;

import codenvy.client.MainPaige.MainPagePresenter;
import codenvy.client.MainPaige.MainPageViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    public void onModuleLoad() {
        Resources.IMPL.Styles().ensureInjected();

        SimpleEventBus eventBus = new SimpleEventBus();

        final MainPagePresenter presenter = new MainPagePresenter(new MainPageViewImpl(eventBus), eventBus);

        presenter.go(RootLayoutPanel.get());
    }
}
