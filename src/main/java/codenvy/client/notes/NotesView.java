package codenvy.client.notes;

import codenvy.client.mvp.View;
import com.google.inject.ImplementedBy;

@ImplementedBy(NotesViewImpl.class)
public interface NotesView extends View<NotesView.ActionDelegate>{

    public interface ActionDelegate {

        void onSaveButtonClicked();

    }
    void showNotes();

    void closeNotes();

    void setNotesText(String text);

    String getNotesText();
}
