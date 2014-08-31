package codenvy.client.userCard;

import codenvy.client.models.User;
import com.google.inject.Inject;

public class UserCardPresenter implements UserCardView.ActionDelegate {

    private final UserCardView view;

    private Callback callback;

    @Inject
    public UserCardPresenter(UserCardView view) {
        this.view = view;
        this.view.setDelegate(this);
    }

    @Override
    public void onSaveButtonClicked() {
        callback.onSaveButtonClicked(new User(view.getName(), view.getBirthday(), view.getAddress(), view.getNotes()));

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
        view.setNotes(editedRow == null ? "" : editedRow.getNotes());

        view.showDialog();
    }

    public interface Callback {
        void onSaveButtonClicked(User user);
    }
}
