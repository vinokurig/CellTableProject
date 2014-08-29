package codenvy.client;

import codenvy.client.userCard.UserCardPresenter;
import codenvy.client.userCard.UserCardView;
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
public class UserCardPresenterTest {

    private static final String NAME = "name";

    private static final String ADDRESS = "address";

    private static final Date BIRTHDAY = new Date();

    private static final String NOTES = "notes";

    @Mock
    private UserCardView userCardView;

    @Mock
    private UserCardPresenter.Callback callback;

    @Mock
    private User user;

    @InjectMocks
    private UserCardPresenter userCardPresenter;

    @Test
    public void shouldEmptyDialogShown() {
        userCardPresenter.showDialog(callback, null);

        verify(userCardView).setName(eq(""));
        verify(userCardView).setAddress(eq(""));
        verify(userCardView).setBirthday(null);
        verify(userCardView).setNotes("");

        verify(userCardView).showDialog();
    }

    @Test
    public void shouldFilledDialogShown() {
        when(user.getName()).thenReturn(NAME);
        when(user.getAddress()).thenReturn(ADDRESS);
        when(user.getBirthday()).thenReturn(BIRTHDAY);
        when(user.getNotes()).thenReturn(NOTES);

        userCardPresenter.showDialog(callback, user);

        verify(userCardView).setName(eq(NAME));
        verify(userCardView).setAddress(eq(ADDRESS));
        verify(userCardView).setBirthday(eq(BIRTHDAY));
        verify(userCardView).setNotes(eq(NOTES));

        verify(userCardView).showDialog();
    }

    @Test
    public void shouldSaveUser() {
        when(userCardView.getName()).thenReturn(NAME);
        when(userCardView.getAddress()).thenReturn(ADDRESS);
        when(userCardView.getBirthday()).thenReturn(BIRTHDAY);
        when(userCardView.getNotes()).thenReturn(NOTES);

        userCardPresenter.showDialog(callback, user);
        userCardPresenter.onSaveButtonClicked();

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);

        verify(callback).onSaveButtonClicked(user.capture());

        assertEquals(NAME, user.getValue().getName());
        assertEquals(ADDRESS, user.getValue().getAddress());
        assertEquals(BIRTHDAY, user.getValue().getBirthday());
        assertEquals(NOTES, user.getValue().getNotes());

        verify(userCardView).closeDialog();
    }

    @Test
    public void shouldDialogClosed() {
        userCardPresenter.onCloseButtonClicked();

        verify(userCardView).closeDialog();
    }
}