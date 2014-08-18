package codenvy.client.Dialog;

import codenvy.client.mvp.Presenter;
import codenvy.client.models.User;
import com.google.gwt.user.client.ui.HasWidgets;

public class DialogPresenter implements Presenter, DialogView.ActionDelegate {

    public interface Callback {
        void onSaveButtonClicked(User user);
    }

    private final DialogView view;

    private Callback callback;

    public DialogPresenter(DialogView view) {
        this.view = view;
        this.view.setDelegate(this);
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

    public void showDialog(Callback callback, User editedRow) {
        this.callback = callback;

        view.setName(editedRow == null ? "" : editedRow.getName());
        view.setAddress(editedRow == null ? "" : editedRow.getAddress());
        view.setBirthday(editedRow == null ? null : editedRow.getBirthday());

        view.showDialog();
    }

}
