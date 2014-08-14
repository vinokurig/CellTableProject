package codenvy.client.mvp.presenters;

import codenvy.client.mvp.models.User;
import codenvy.client.mvp.views.DialogView;
import com.google.gwt.user.client.ui.HasWidgets;

public class DialogPresenterImpl implements Presenter, DialogView.Presenter {

    interface Callback {
        void onSaveButtonClicked(User user);
    }

    private final DialogView view;

    private Callback callback;

    public DialogPresenterImpl(DialogView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void onSaveButtonClicked() {
        callback.onSaveButtonClicked(new User(view.getName(), view.getBirthday(), view.getAddress()));

        view.closeDialog();
    }

    @Override
    public void onCloseButtonClicked() {
        view.closeDialog();
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    void showDialog(Callback callback, User editedRow) {
        this.callback = callback;

        view.setName(editedRow == null ? "" : editedRow.getName());
        view.setAddress(editedRow == null ? "" : editedRow.getAddress());
        view.setBirthday(editedRow == null ? null : editedRow.getBirthday());

        view.showDialog();
    }

}
