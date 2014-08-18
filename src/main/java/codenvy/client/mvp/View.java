package codenvy.client.mvp;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Created by ievinokur on 18.08.14.
 */
public interface View<T> extends IsWidget {

    void setDelegate(T delegate);

}
