package codenvy.client;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.mainPaige.MainPagePresenter;
import codenvy.client.mainPaige.MainPageView;
import codenvy.client.mainPaige.events.DeleteUserEvent;
import codenvy.client.models.User;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPagePresenterTest {

    @Mock
    private User user;

    @Mock
    private EventBus eventBus;

    @Mock
    private HasWidgets container;

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
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), eq((User) null));

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                List<User> usersList = (List<User>) invocation.getArguments()[0];

                assertTrue(usersList.contains(user));

                return null;
            }
        }).when(mainPageView).setUser(anyListOf(User.class));

        mainPagePresenter.onAddButtonClicked();

        verify(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), eq((User) null));

        verify(mainPageView).setUser(anyListOf(User.class));
    }

    @Test
    public void shouldSelectedUserEdited() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());

        mainPagePresenter.onAddButtonClicked();
        mainPagePresenter.onUserSelected(user);

        reset(dialogPresenter);
        reset(mainPageView);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];

                assertTrue(usersList.contains(user));

                return null;
            }
        }).when(mainPageView).setUser(anyListOf(User.class));

        mainPagePresenter.onEditButtonClicked();

        verify(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());

        verify(mainPageView).setUser(anyListOf(User.class));
    }

    @Test
    public void shouldUnselectedUserEdited() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                User user = (User) invocation.getArguments()[1];

                assertNull(user);

                return null;
            }
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());

        mainPagePresenter.onEditButtonClicked();
    }

    @Test
    public void shouldSelectedUserDeleted() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), eq((User) null));

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];

                assertTrue(usersList.contains(user));

                return null;
            }
        }).when(mainPageView).setUser(anyListOf(User.class));

        mainPagePresenter.onAddButtonClicked();

        reset(dialogPresenter);
        reset(mainPageView);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];

                assertFalse(usersList.contains(user));

                return null;
            }
        }).when(mainPageView).setUser(anyListOf(User.class));

        mainPagePresenter.onUserSelected(user);

        mainPagePresenter.deleteUser(event);

        verify(mainPageView).setUser(anyListOf(User.class));
    }

    @Test
    public void shouldUnselectedUserDeleted() {
        mainPagePresenter.deleteUser(event);

        verify(mainPageView, never()).setUser(anyListOf(User.class));
    }

    @Test
    public void shouldPresenterGo() {
        mainPagePresenter.go(container);

        verify(container).clear();

        verify(container).add(mainPageView.asWidget());
    }

    @Test
    public void shouldEventBusFiredUp() {
        mainPagePresenter.onDeleteButtonClicked();

        verify(eventBus).fireEvent(isA(DeleteUserEvent.class));
    }
}
