package codenvy.client;

import codenvy.client.mainPaige.MainPagePresenter;
import codenvy.client.mainPaige.MainPageView;
import codenvy.client.mainPaige.events.DeleteUserEvent;
import codenvy.client.models.User;
import codenvy.client.notes.NotesPresenter;
import codenvy.client.userCard.UserCardPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPagePresenterTest {

    @Captor
    ArgumentCaptor<List<User>> usersList;

    @Mock
    private User user;

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
    public void shouldUnselectedUserEdited() {
        mainPagePresenter.onEditButtonClicked();

        verify(userCardPresenter, never()).showDialog(any(UserCardPresenter.Callback.class), any(User.class));
    }

    @Test
    public void shouldSelectedUserDeleted() {
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
    public void shouldUnselectedUserDeleted() {
        mainPagePresenter.deleteUser(event);

        verify(mainPageView, never()).setUser(anyListOf(User.class));
    }

    @Test
    public void shouldPresenterGo() {
        HasWidgets container = mock(HasWidgets.class);

        mainPagePresenter.go(container);

        verify(container).clear();
        verify(container).add(any(Widget.class));

        verify(mainPageView).asWidget();
    }

    @Test
    public void shouldEventBusFiredUp() {
        mainPagePresenter.onDeleteButtonClicked();

        verify(eventBus).fireEvent(isA(DeleteUserEvent.class));
    }

    @Test
    public void shouldNotesUsed() {
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
                NotesPresenter.Callback callback = (NotesPresenter.Callback) invocation.getArguments()[0];

                callback.onCloseButtonClicked(user);

                return null;
            }
        }).when(notesPresenter).showNotes(any(NotesPresenter.Callback.class), any(User.class));

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.onUserClicked(user);

        verify(notesPresenter).showNotes(any(NotesPresenter.Callback.class), any(User.class));

        verify(mainPageView).setUser(usersList.capture());

        assertTrue(usersList.getValue().contains(user));
        assertEquals(1, usersList.getValue().size());
    }
}