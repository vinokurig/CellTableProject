package codenvy.client;

import codenvy.client.dialog.DialogPresenter;
import codenvy.client.dialog.DialogView;
import codenvy.client.models.User;
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
public class DialogPresenterTest {
    public static final String NAME = "name";

    public static final String ADDRESS = "address";

    public static final Date BIRTHDAY = new Date();

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
        when(user.getName()).thenReturn(NAME);
        when(user.getAddress()).thenReturn(ADDRESS);
        when(user.getBirthday()).thenReturn(BIRTHDAY);

        dialogPresenter.showDialog(callback, user);

        verify(dialogView).setName(eq(NAME));
        verify(dialogView).setAddress(eq(ADDRESS));
        verify(dialogView).setBirthday(eq(BIRTHDAY));

        verify(dialogView).showDialog();
    }

    @Test
    public void shouldSaveUser() {
        when(dialogView.getName()).thenReturn(NAME);
        when(dialogView.getAddress()).thenReturn(ADDRESS);
        when(dialogView.getBirthday()).thenReturn(BIRTHDAY);

        dialogPresenter.showDialog(callback, user);

        dialogPresenter.onSaveButtonClicked();

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);

        verify(callback).onSaveButtonClicked(user.capture());

        assertEquals(NAME, user.getValue().getName());
        assertEquals(ADDRESS, user.getValue().getAddress());
        assertEquals(BIRTHDAY, user.getValue().getBirthday());

        verify(dialogView).closeDialog();
    }

    @Test
    public void shouldDialogClosed() {
        dialogPresenter.onCloseButtonClicked();

        verify(dialogView).closeDialog();
    }
}
