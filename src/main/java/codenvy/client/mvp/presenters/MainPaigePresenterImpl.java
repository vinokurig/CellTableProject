package codenvy.client.mvp.presenters;

import codenvy.client.mvp.models.User;
import codenvy.client.mvp.views.DialogViewImpl;
import codenvy.client.mvp.views.MainPageView;

import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;
import java.util.List;

public class MainPaigePresenterImpl implements Presenter, MainPageView.Presenter {

    private final MainPageView view;

    private DialogPresenterImpl dialogPresenter;

    final private DialogPresenterImpl.Callback addCallback;

    final private DialogPresenterImpl.Callback editCallback;

    private User selectedUser;

    private List<User> usersList = new ArrayList<User>();

    public MainPaigePresenterImpl(MainPageView view) {
        dialogPresenter = new DialogPresenterImpl(new DialogViewImpl());

        this.view = view;
        this.view.setPresenter(this);

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
    public void onDeleteButtonClicked() {
        usersList.remove(selectedUser);
        view.setUser(usersList);
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
