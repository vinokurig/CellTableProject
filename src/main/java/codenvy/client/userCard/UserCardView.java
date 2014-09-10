package codenvy.client.userCard;

import codenvy.client.mvp.View;

import com.google.inject.ImplementedBy;

import java.util.Date;

@ImplementedBy(UserCardViewImpl.class)
public interface UserCardView extends View<UserCardView.ActionDelegate> {

    void showDialog();

    void closeDialog();

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    Date getBirthday();

    void setBirthday(Date birthday);

    String getNotes();

    void setNotes(String notes);

    public interface ActionDelegate {
        void onSaveButtonClicked();

        void onCloseButtonClicked();
    }

}
