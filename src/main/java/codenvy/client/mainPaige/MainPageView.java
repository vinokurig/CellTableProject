package codenvy.client.mainPaige;

import codenvy.client.mvp.View;
import codenvy.client.models.User;

import java.util.List;

public interface MainPageView extends View<MainPageView.ActionDelegate> {

    interface ActionDelegate {

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onUserSelected(User user);

    }

    void setUser(List<User> users);

}