package codenvy.client;

import codenvy.client.mainPage.MainPagePresenter;
import codenvy.client.mainPage.MainPageView;
import codenvy.client.mainPage.events.DeleteUserEvent;
import codenvy.client.models.User;
import codenvy.client.notes.NotesPresenter;
import codenvy.client.userCard.UserCardPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@GwtModule("codenvy.Module")
public class MainPagePresenterTest extends GwtTestWithMockito {

    @Captor
    private ArgumentCaptor<List<User>> usersList;

    @Mock
    private EventBus eventBus;

    @Mock
    private DeleteUserEvent event;

    @Mock
    private MainPageView mainPageView;

    @Mock
    private UserCardPresenter userCardPresenter;

    @Mock
    private NotesPresenter notesPresenter;

    @InjectMocks
    private MainPagePresenter mainPagePresenter;

    @Test
    public void shouldUserAdded() {
        final User user = mock(User.class);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                UserCardPresenter.Callback callback = (UserCardPresenter.Callback) invocation.getArguments()[0];

                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), eq((User) null));

        mainPagePresenter.onAddButtonClicked();

        verify(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), eq((User) null));

        verify(mainPageView).setUser(usersList.capture());

        assertTrue(usersList.getValue().contains(user));

        assertEquals(1, usersList.getValue().size());
    }

    @Test
    public void shouldSelectedUserEdited() {
        final User user = mock(User.class);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                UserCardPresenter.Callback callback = (UserCardPresenter.Callback) invocation.getArguments()[0];

                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), any(User.class));

        mainPagePresenter.onAddButtonClicked();

        reset(userCardPresenter);
        reset(mainPageView);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                UserCardPresenter.Callback callback = (UserCardPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), any(User.class));

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.onEditButtonClicked();

        verify(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), any(User.class));

        verify(mainPageView).setUser(usersList.capture());

        assertTrue(usersList.getValue().contains(user));

        assertEquals(1, usersList.getValue().size());
    }

    @Test
    public void shouldNotUnselectedUserEdited() {
        mainPagePresenter.onEditButtonClicked();

        verify(userCardPresenter, never()).showDialog(any(UserCardPresenter.Callback.class), any(User.class));
    }

    @Test
    public void shouldSelectedUserDeleted() {
        final User user = mock(User.class);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                UserCardPresenter.Callback callback = (UserCardPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), eq((User) null));

        mainPagePresenter.onAddButtonClicked();

        reset(userCardPresenter);
        reset(mainPageView);

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.deleteUser(event);

        verify(mainPageView).setUser(usersList.capture());

        assertEquals(0, usersList.getValue().size());
    }

    @Test
    public void shouldNotUnselectedUserDeleted() {
        mainPagePresenter.deleteUser(event);

        verify(mainPageView, never()).setUser(anyListOf(User.class));
    }

    @Test
    public void shouldPresenterGo() {
        HasWidgets container = mock(HasWidgets.class);

        mainPagePresenter.go(container);

        verify(mainPageView).asWidget();
        verify(container).clear();
        verify(container).add(eq(mainPageView.asWidget()));
    }

    @Test
    public void shouldEventBusFiredUp() {
        mainPagePresenter.onDeleteButtonClicked();

        verify(eventBus).fireEvent(isA(DeleteUserEvent.class));
    }

    @Test
    public void shouldNotesSaved() {
        final User user = mock(User.class);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                UserCardPresenter.Callback callback = (UserCardPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), any(User.class));

        mainPagePresenter.onAddButtonClicked();

        reset(userCardPresenter);
        reset(mainPageView);

        when(user.getNotes()).thenReturn("");

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                NotesPresenter.Callback callback = (NotesPresenter.Callback) invocation.getArguments()[0];

                callback.onCloseButtonClicked("Notes");

                return null;
            }
        }).when(notesPresenter).showDialog(any(NotesPresenter.Callback.class), any(String.class));

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.onUserClicked();

        verify(notesPresenter).showDialog(any(NotesPresenter.Callback.class), eq(""));

        verify(user).setNotes("Notes");

        verify(mainPageView).setUser(usersList.capture());

        assertTrue(usersList.getValue().contains(user));

        assertEquals(1, usersList.getValue().size());
    }

    @Test
    public void shouldJustCloseOnSavedNotes() {
        final User user = mock(User.class);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                UserCardPresenter.Callback callback = (UserCardPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(userCardPresenter).showDialog(any(UserCardPresenter.Callback.class), any(User.class));

        mainPagePresenter.onAddButtonClicked();

        reset(userCardPresenter);
        reset(mainPageView);

        when(user.getNotes()).thenReturn("Notes");

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                NotesPresenter.Callback callback = (NotesPresenter.Callback) invocation.getArguments()[0];

                callback.onCloseButtonClicked("Notes");

                return null;
            }
        }).when(notesPresenter).showDialog(any(NotesPresenter.Callback.class), any(String.class));

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.onUserClicked();

        verify(notesPresenter).showDialog(any(NotesPresenter.Callback.class), eq("Notes"));

        verify(user, never()).setNotes(anyString());

        verify(mainPageView, never()).setUser(usersList.capture());
    }
}