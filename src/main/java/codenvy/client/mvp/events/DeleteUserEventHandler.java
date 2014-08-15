package codenvy.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteUserEventHandler extends EventHandler {
    void deleteUser(DeleteUserEvent event);
}
