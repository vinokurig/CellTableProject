package codenvy.client.notes;

import codenvy.client.models.User;
import com.google.inject.Inject;

public class NotesPresenter implements NotesView.ActionDelegate{

    public interface Callback {
        void onSaveButtonClicked(User user);
    }

    private final NotesView view;

    private Callback callback;

    private User user;

    @Inject
    public NotesPresenter(NotesView view){
        this.view = view;
        this.view.setDelegate(this);
    }

    @Override
    public void onSaveButtonClicked() {
        callback.onSaveButtonClicked(new User(user.getName(), user.getBirthday(), user.getAddress(), view.getNotesText()));

        view.closeNotes();
    }

    public void showNotes(Callback callback, User user){
        this.callback = callback;

        this.user = user;

        view.setNotesText(user.getNotes());

        view.showNotes();
    }
}
