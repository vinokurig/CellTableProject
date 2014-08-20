package codenvy.client.dialog;

import codenvy.client.models.User;
import com.google.inject.Inject;

public class DialogPresenter implements DialogView.ActionDelegate {

    public interface Callback {
        void onSaveButtonClicked(User user);
    }

    private final DialogView view;

    private Callback callback;
    @Inject
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

    public void showDialog(Callback callback, User editedRow) {
        this.callback = callback;

        view.setName(editedRow == null ? "" : editedRow.getName());
        view.setAddress(editedRow == null ? "" : editedRow.getAddress());
        view.setBirthday(editedRow == null ? null : editedRow.getBirthday());

        view.showDialog();
    }

}
