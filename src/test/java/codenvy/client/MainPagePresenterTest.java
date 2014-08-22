package codenvy.client;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.mainPaige.MainPagePresenter;
import codenvy.client.mainPaige.MainPageView;
import codenvy.client.models.User;
import com.google.gwt.event.shared.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

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
    private DialogPresenter.Callback callback;

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
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) eq(null));

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];
                return null;
            }
        }).when(mainPageView).setUser((ArrayList<User>) anyObject());

        mainPagePresenter.onAddButtonClicked();

        verify(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) eq(null));
    }

    @Test
    public void shouldUserEdited() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];
                return null;
            }
        }).when(mainPageView).setUser((ArrayList<User>) anyObject());

        mainPagePresenter.onAddButtonClicked();
        mainPagePresenter.onUserSelected(user);

        reset(dialogPresenter);

        mainPagePresenter.onEditButtonClicked();

        verify(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());
    }

    @Test
    public void shouldUserDeleted() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                DialogPresenter.Callback callback = (DialogPresenter.Callback) invocation.getArguments()[0];
                callback.onSaveButtonClicked(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) eq(null));;

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];
                return null;
            }
        }).doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<User> usersList = (ArrayList<User>) invocation.getArguments()[0];
                return null;
            }
        }).when(mainPageView).setUser((ArrayList<User>) anyObject());

        mainPagePresenter.onAddButtonClicked();
        mainPagePresenter.onUserSelected(user);
        mainPagePresenter.onDeleteButtonClicked();

        verify(dialogPresenter).showDialog((DialogPresenter.Callback) anyObject(), (User) anyObject());
    }
}
