package codenvy.client.mvp.views;

import codenvy.client.mvp.models.User;

import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

public interface MainPageView extends IsWidget {

    public interface Presenter {

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onDeleteButtonClicked();

        void onUserSelected(User user);

    }

    void setUser(List<User> users);

    void setPresenter(Presenter presenter);

}