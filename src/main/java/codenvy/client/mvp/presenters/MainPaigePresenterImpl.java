package codenvy.client.mvp.presenters;

import codenvy.client.mvp.events.DeleteUserEvent;
import codenvy.client.mvp.events.DeleteUserEventHandler;
import codenvy.client.mvp.models.User;
import codenvy.client.mvp.views.DialogViewImpl;
import codenvy.client.mvp.views.MainPageView;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;
import java.util.List;

public class MainPaigePresenterImpl implements Presenter, MainPageView.Presenter {

    private final MainPageView view;

    private final DialogPresenterImpl dialogPresenter;

    final private DialogPresenterImpl.Callback addCallback;

    final private DialogPresenterImpl.Callback editCallback;

    private User selectedUser;

    private List<User> usersList;

    private SimpleEventBus eventBus;

    public MainPaigePresenterImpl(MainPageView view, SimpleEventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;

        this.view.setPresenter(this);
        this.view.setEventBus(eventBus);

        dialogPresenter = new DialogPresenterImpl(new DialogViewImpl());

        usersList = new ArrayList<User>();

        addCallback = new DialogPresenterImpl.Callback() {
            @Override
            public void onSaveButtonClicked(User user) {
                usersList.add(user);

                MainPaigePresenterImpl.this.view.setUser(usersList);
            }
        };

        editCallback = new DialogPresenterImpl.Callback() {
            @Override
            public void onSaveButtonClicked(User user) {
                usersList.add(usersList.indexOf(selectedUser), user);
                usersList.remove(selectedUser);

                MainPaigePresenterImpl.this.view.setUser(usersList);
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

    public void deleteUser(){
        usersList.remove(selectedUser);
        MainPaigePresenterImpl.this.view.setUser(usersList);
    };
}
