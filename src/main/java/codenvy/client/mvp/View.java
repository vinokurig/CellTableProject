package codenvy.client.mvp;

import com.google.gwt.user.client.ui.IsWidget;

public interface View<T> extends IsWidget {
    void setDelegate(T delegate);
}
