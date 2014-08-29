package codenvy.client;

import codenvy.client.models.User;
import codenvy.client.notes.NotesPresenter;
import codenvy.client.notes.NotesView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        when(user.getNotes()).thenReturn(NOTES);

        notesPresenter.showNotes(callback, user);

        verify(notesView).setNotesText(eq(NOTES));

        verify(notesView).showNotes();
    }

    @Test
    public void shouldSaveNote() {
        when(notesView.getNotesText()).thenReturn(NOTES);

        notesPresenter.showNotes(callback, user);
        notesPresenter.onSaveButtonClicked();

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);

        verify(callback).onSaveButtonClicked(user.capture());

        assertEquals(NOTES, user.getValue().getNotes());

        verify(notesView).closeNotes();
    }
}
