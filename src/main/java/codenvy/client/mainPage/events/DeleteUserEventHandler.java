package codenvy.client.mainPage.events;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteUserEventHandler extends EventHandler {
    void deleteUser(DeleteUserEvent event);
}
