package codenvy.client.MainPaige;

import codenvy.client.Dialog.DialogPresenter;
import codenvy.client.MainPaige.events.DeleteUserEvent;
import codenvy.client.MainPaige.events.DeleteUserEventHandler;
import codenvy.client.mvp.models.User;
import codenvy.client.Dialog.DialogViewImpl;

import codenvy.client.mvp.Presenter;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;
import java.util.List;

public class MainPagePresenter implements Presenter, MainPageView.ActionDelegate {

    private final MainPageView view;

    private final DialogPresenter dialogPresenter;

    private final DialogPresenter.Callback addCallback;

    private final DialogPresenter.Callback editCallback;

    private User selectedUser;

    private List<User> usersList;

    public MainPagePresenter(MainPageView view, SimpleEventBus eventBus) {
        this.view = view;
        this.view.setPresenter(this);

        dialogPresenter = new DialogPresenter(new DialogViewImpl());

        usersList = new ArrayList<>();

        eventBus.addHandler(DeleteUserEvent.TYPE, new DeleteUserEventHandler() {
            @Override
            public void deleteUser(DeleteUserEvent event) {
                usersList.remove(selectedUser);

                MainPagePresenter.this.view.setUser(usersList);
            }
        });

        addCallback = new DialogPresenter.Callback() {
            @Override
            public void onSaveButtonClicked(User user) {
                usersList.add(user);

                MainPagePresenter.this.view.setUser(usersList);
            }
        };

        editCallback = new DialogPresenter.Callback() {
            @Override
            public void onSaveButtonClicked(User user) {
                usersList.add(usersList.indexOf(selectedUser), user);
                usersList.remove(selectedUser);

                MainPagePresenter.this.view.setUser(usersList);
            }
        };
    }

    @Override
    public void onAddButtonClicked() {
        dialogPresenter.showDialog(addCallback, null);
    }

    @Override
    public void onEditButtonClicked() {
        dialogPresenter.showDialog(editCallback, selectedUser);
    }

    @Override
    public void onUserSelected(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

}
