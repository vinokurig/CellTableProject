package codenvy.client.mainPage.events;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteUserEvent extends GwtEvent<DeleteUserEventHandler> {

    public static Type<DeleteUserEventHandler> TYPE = new Type<>();

    @Override
    public Type<DeleteUserEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeleteUserEventHandler handler) {
        handler.deleteUser(this);
    }
}
