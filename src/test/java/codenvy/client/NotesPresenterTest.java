package codenvy.client;

import codenvy.client.models.User;
import codenvy.client.notes.NotesPresenter;
import codenvy.client.notes.NotesView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotesPresenterTest {

    private static final String NOTES = "notes";

    @Mock
    User user;

    @Mock
    NotesView notesView;

    @Mock
    NotesPresenter.Callback callback;

    @InjectMocks
    NotesPresenter notesPresenter;

    @Test
    public void shouldDialogShown() {
        notesPresenter.showDialog(callback, NOTES);

        verify(notesView).setNotesText(NOTES);

        verify(notesView).showDialog();
    }

    @Test
    public void shouldSaveNote() {
        when(notesView.getNotesText()).thenReturn(NOTES);

        notesPresenter.showDialog(callback, "");
        notesPresenter.onCloseButtonClicked();

        verify(callback).onCloseButtonClicked(NOTES);

        verify(notesView).closeDialog();
    }
}