package codenvy.client.mainPaige;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.mainPaige.events.DeleteUserEvent;
import codenvy.client.mainPaige.events.DeleteUserEventHandler;
import codenvy.client.models.User;
import codenvy.client.mvp.Presenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class MainPagePresenter implements Presenter, MainPageView.ActionDelegate {

    private final MainPageView view;

    private final DialogPresenter dialogPresenter;

    private final DialogPresenter.Callback addCallback;

    private final DialogPresenter.Callback editCallback;

    private User selectedUser;

    private final List<User> usersList;

    @Inject
    public MainPagePresenter(MainPageView view, DialogPresenter dialogPresenter, EventBus eventBus) {
        this.view = view;
        this.view.setDelegate(this);

        this.dialogPresenter = dialogPresenter;

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
