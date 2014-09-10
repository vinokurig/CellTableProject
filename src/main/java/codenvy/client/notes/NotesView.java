package codenvy.client.notes;

import codenvy.client.mvp.View;

import com.google.inject.ImplementedBy;

@ImplementedBy(NotesViewImpl.class)
public interface NotesView extends View<NotesView.ActionDelegate> {

    void showDialog();

    void closeDialog();

    String getNotesText();

    void setNotesText(String text);

    public interface ActionDelegate {
        void onCloseButtonClicked();
    }

}
