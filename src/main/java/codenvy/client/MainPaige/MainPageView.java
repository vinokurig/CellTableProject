package codenvy.client.MainPaige;

import codenvy.client.mvp.models.User;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

public interface MainPageView extends IsWidget {

    public interface ActionDelegate {

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onUserSelected(User user);

    }

    void setUser(List<User> users);

    void setPresenter(ActionDelegate presenter);
}