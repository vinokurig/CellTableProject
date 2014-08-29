package codenvy.client;

import codenvy.client.models.User;
import codenvy.client.notes.NotesPresenter;
import codenvy.client.notes.NotesView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
}
