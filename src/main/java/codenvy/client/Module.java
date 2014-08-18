package codenvy.client;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.dialog.DialogViewImpl;
import codenvy.client.mainPaige.MainPagePresenter;
import codenvy.client.mainPaige.MainPageViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    public void onModuleLoad() {
        Resources.IMPL.Styles().ensureInjected();

        SimpleEventBus eventBus = new SimpleEventBus();

        final DialogPresenter dialogPresenter = new DialogPresenter(new DialogViewImpl());

        final MainPagePresenter mainPagePresenter = new MainPagePresenter(new MainPageViewImpl(eventBus), dialogPresenter, eventBus);

        mainPagePresenter.go(RootLayoutPanel.get());
    }
}
