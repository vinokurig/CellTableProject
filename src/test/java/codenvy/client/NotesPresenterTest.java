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

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotesPresenterTest {

    private static final String NAME = "name";

    private static final String ADDRESS = "address";

    private static final Date BIRTHDAY = new Date();

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
        when(user.getName()).thenReturn(NAME);
        when(user.getBirthday()).thenReturn(BIRTHDAY);
        when(user.getAddress()).thenReturn(ADDRESS);

        when(notesView.getNotesText()).thenReturn(NOTES);

        notesPresenter.showNotes(callback, user);
        notesPresenter.onCloseButtonClicked();

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);

        verify(callback).onCloseButtonClicked(user.capture());

        assertEquals(NAME, user.getValue().getName());
        assertEquals(BIRTHDAY, user.getValue().getBirthday());
        assertEquals(ADDRESS, user.getValue().getAddress());
        assertEquals(NOTES, user.getValue().getNotes());

        verify(notesView).closeNotes();
    }

    @Test
    public void shouldCloseOnSavedNote() {
        when(user.getNotes()).thenReturn(NOTES);

        when(notesView.getNotesText()).thenReturn(NOTES);

        notesPresenter.showNotes(callback, user);
        notesPresenter.onCloseButtonClicked();

        verify(callback, never()).onCloseButtonClicked(any(User.class));

        verify(notesView).closeNotes();
    }
}