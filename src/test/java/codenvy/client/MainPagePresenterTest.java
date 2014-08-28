package codenvy.client;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.mainPaige.MainPagePresenter;
import codenvy.client.mainPaige.MainPageView;
import codenvy.client.mainPaige.events.DeleteUserEvent;
import codenvy.client.models.User;
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
    private DialogPresenter dialogPresenter;

    @InjectMocks
    private MainPagePresenter mainPagePresenter;

    @Test
    public void shouldUserAdded() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];

                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(dialogPresenter).showDialog(any(DialogPresenter.Callback.class), eq((User) null));

        mainPagePresenter.onAddButtonClicked();

        verify(dialogPresenter).showDialog(any(DialogPresenter.Callback.class), eq((User) null));

        verify(mainPageView).setUser(usersList.capture());

        assertTrue(usersList.getValue().contains(user));
        assertEquals(1, usersList.getValue().size());
    }

    @Test
    public void shouldSelectedUserEdited() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(dialogPresenter).showDialog(any(DialogPresenter.Callback.class), any(User.class));

        mainPagePresenter.onAddButtonClicked();

        reset(dialogPresenter);
        reset(mainPageView);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);

                return null;
            }
        }).when(dialogPresenter).showDialog(any(DialogPresenter.Callback.class), any(User.class));

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.onEditButtonClicked();

        verify(dialogPresenter).showDialog(any(DialogPresenter.Callback.class), any(User.class));

        verify(mainPageView).setUser(usersList.capture());

        assertTrue(usersList.getValue().contains(user));
        assertEquals(1, usersList.getValue().size());
    }

    @Test
    public void shouldUnselectedUserEdited() {
        mainPagePresenter.onEditButtonClicked();

        verify(dialogPresenter, never()).showDialog(any(DialogPresenter.Callback.class), any(User.class));
    }

    @Test
    public void shouldSelectedUserDeleted() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(dialogPresenter).showDialog(any(DialogPresenter.Callback.class), eq((User) null));

        mainPagePresenter.onAddButtonClicked();

        reset(dialogPresenter);
        reset(mainPageView);

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.deleteUser(event);

        verify(mainPageView).setUser(usersList.capture());

        assertFalse(usersList.getValue().contains(user));
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

}