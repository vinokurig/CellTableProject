package codenvy.client.mainPage;

import codenvy.client.models.User;
import codenvy.client.mvp.View;

import java.util.List;

public interface MainPageView extends View<MainPageView.ActionDelegate> {

    void setUser(List<User> users);

    interface ActionDelegate {

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onDeleteButtonClicked();

        void onUserSelected(User user);

        void onUserClicked();

    }
}