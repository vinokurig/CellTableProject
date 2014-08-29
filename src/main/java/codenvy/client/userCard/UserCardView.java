package codenvy.client.userCard;

import codenvy.client.mvp.View;
import com.google.inject.ImplementedBy;

import java.util.Date;

@ImplementedBy(UserCardViewImpl.class)
public interface UserCardView extends View<UserCardView.ActionDelegate> {

    public interface ActionDelegate {
        void onSaveButtonClicked();

        void onCloseButtonClicked();
    }

    void showDialog();

    void closeDialog();

    void setName(String name);

    void setAddress(String address);

    void setBirthday(Date birthday);

    void setNotes(String notes);

    String getName();

    String getAddress();

    Date getBirthday();

    String getNotes();

}
