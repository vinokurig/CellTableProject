package codenvy.client.mainPaige;

import codenvy.client.mainPaige.events.DeleteUserEvent;
import codenvy.client.mainPaige.events.DeleteUserEventHandler;
import codenvy.client.models.User;
import codenvy.client.mvp.Presenter;
import codenvy.client.notes.NotesPresenter;
import codenvy.client.userCard.UserCardPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class MainPagePresenter implements Presenter, MainPageView.ActionDelegate, DeleteUserEventHandler {

    private final MainPageView view;

    private final UserCardPresenter userCardPresenter;

    private final NotesPresenter notesPresenter;

    private final UserCardPresenter.Callback UserCardAddCallback;

    private final UserCardPresenter.Callback UserCardEditCallback;

    private final NotesPresenter.Callback notesEditCallback;

    private final EventBus eventBus;

    private final List<User> usersList;

    private User selectedUser;

    @Inject
    public MainPagePresenter(MainPageView view, UserCardPresenter userCardPresenter, NotesPresenter notesPresenter, EventBus eventBus) {
        this.view = view;

        this.view.setDelegate(this);

        this.eventBus = eventBus;

        this.userCardPresenter = userCardPresenter;

        this.notesPresenter = notesPresenter;

        usersList = new ArrayList<>();

        eventBus.addHandler(DeleteUserEvent.TYPE, this);

        UserCardAddCallback = new UserCardPresenter.Callback() {
            @Override
            public void onSaveButtonClicked(User user) {
                usersList.add(user);

                MainPagePresenter.this.view.setUser(usersList);
            }
        };

        UserCardEditCallback = new UserCardPresenter.Callback() {
            @Override
            public void onSaveButtonClicked(User user) {
                usersList.add(usersList.indexOf(selectedUser), user);
                usersList.remove(selectedUser);

                MainPagePresenter.this.view.setUser(usersList);
            }
        };

        notesEditCallback = new NotesPresenter.Callback() {
            @Override
            public void onCloseButtonClicked(User user) {
                usersList.add(usersList.indexOf(selectedUser), user);
                usersList.remove(selectedUser);

                MainPagePresenter.this.view.setUser(usersList);
            }
        };
    }

    @Override
    public void onAddButtonClicked() {
        userCardPresenter.showDialog(UserCardAddCallback, null);
    }

    @Override
    public void onEditButtonClicked() {
        if (selectedUser != null) {
            userCardPresenter.showDialog(UserCardEditCallback, selectedUser);
        }
    }

    @Override
    public void onDeleteButtonClicked() {
        eventBus.fireEvent(new DeleteUserEvent());
    }

    @Override
    public void onUserSelected(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    @Override
    public void onUserClicked(User selectedUser) {
        notesPresenter.showNotes(notesEditCallback, selectedUser);
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();

        container.add(view.asWidget());
    }

    @Override
    public void deleteUser(DeleteUserEvent event) {
        if (selectedUser != null) {
            usersList.remove(selectedUser);

            view.setUser(usersList);
        }
    }
}
