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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DialogPresenterTest {

    private final Date someDate = new Date();

    @Mock
    private DialogView dialogView;

    @Mock
    private DialogPresenter.Callback callback;

    @Mock
    private User user;

    @InjectMocks
    private DialogPresenter dialogPresenter;

    @Test
    public void shouldEmptyDialogShown() {
        dialogPresenter.showDialog(callback, null);

        verify(dialogView).setName(eq(""));
        verify(dialogView).setAddress(eq(""));
        verify(dialogView).setBirthday(null);

        verify(dialogView).showDialog();
    }

    @Test
    public void shouldFilledDialogShown() {
        when(user.getName()).thenReturn("name");
        when(user.getAddress()).thenReturn("address");
        when(user.getBirthday()).thenReturn(someDate);

        dialogPresenter.showDialog(callback, user);

        verify(dialogView).setName(eq("name"));
        verify(dialogView).setAddress(eq("address"));
        verify(dialogView).setBirthday(eq(someDate));

        verify(dialogView).showDialog();
    }

    @Test
    public void shouldSaveUser() {
        when(dialogView.getName()).thenReturn("name");
        when(dialogView.getAddress()).thenReturn("address");
        when(dialogView.getBirthday()).thenReturn(someDate);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                User user = (User) invocation.getArguments()[0];

                assertEquals("name", user.getName());
                assertEquals("address", user.getAddress());
                assertEquals(someDate, user.getBirthday());

                return null;
            }
        }).when(callback).onSaveButtonClicked((User) anyObject());

        dialogPresenter.showDialog(callback, user);
        dialogPresenter.onSaveButtonClicked();

        verify(dialogView).setName(anyString());
        verify(dialogView).setAddress(anyString());
        verify(dialogView).setBirthday((Date) anyObject());

        verify(dialogView).showDialog();
        verify(dialogView).closeDialog();
    }

    @Test
    public void shouldDialogClosed() {
        dialogPresenter.onCloseButtonClicked();

        verify(dialogView).closeDialog();
    }
}
