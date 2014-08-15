package codenvy.client;

import codenvy.client.mvp.events.DeleteUserEvent;
import codenvy.client.mvp.events.DeleteUserEventHandler;
import codenvy.client.mvp.presenters.MainPaigePresenterImpl;
import codenvy.client.mvp.views.MainPageViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    public void onModuleLoad() {
        SimpleEventBus eventBus = new SimpleEventBus();

        final MainPaigePresenterImpl presenter = new MainPaigePresenterImpl(new MainPageViewImpl(), eventBus);

        eventBus.addHandler(DeleteUserEvent.TYPE, new DeleteUserEventHandler(){
            @Override
            public void deleteUser(DeleteUserEvent event) {
                presenter.deleteUser();
            }
        });

        Resources.IMPL.Styles().ensureInjected();

        presenter.go(RootLayoutPanel.get());
    }
}
