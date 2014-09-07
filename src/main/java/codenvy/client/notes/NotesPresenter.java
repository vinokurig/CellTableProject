package codenvy.client.notes;

import com.google.inject.Inject;

public class NotesPresenter implements NotesView.ActionDelegate {

    private final NotesView view;

    private Callback callback;

    @Inject
    public NotesPresenter(NotesView view) {
        this.view = view;
        this.view.setDelegate(this);
    }

    @Override
    public void onCloseButtonClicked() {
        callback.onCloseButtonClicked(view.getNotesText());

        view.closeDialog();
    }

    public void showDialog(Callback callback, String notes) {
        this.callback = callback;

        view.setNotesText(notes);

        view.showDialog();
    }

    public interface Callback {
        void onCloseButtonClicked(String notes);
    }
}
