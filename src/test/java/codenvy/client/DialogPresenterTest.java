package codenvy.client;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.dialog.DialogView;
import codenvy.client.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Date;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DialogPresenterTest {

    @Mock
    private DialogView dialogView;

    @Mock
    private DialogPresenter.Callback callback;

    @Mock
    private User user;


    @InjectMocks
    private DialogPresenter dialogPresenter;

    @Test
    public void shouldSaveUser() {
        when(dialogView.getName()).thenReturn("name");
        when(dialogView.getAddress()).thenReturn("address");
        when(dialogView.getBirthday()).thenReturn(null);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(callback).onSaveButtonClicked((User) anyObject());

        dialogPresenter.showDialog(callback, user);

        verify(dialogView).setName(anyString());
    }

    @Test
    public void shouldDialogClosed() {
        dialogPresenter.onCloseButtonClicked();

        verify(dialogView).closeDialog();
    }
}
