package codenvy.client.Dialog;

import codenvy.client.mvp.View;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.Date;

public interface DialogView extends View<DialogView.ActionDelegate> {

    public interface ActionDelegate {
        void onSaveButtonClicked();

        void onCloseButtonClicked();
    }

    void showDialog();

    void closeDialog();

    void setName(String name);

    void setAddress(String address);

    void setBirthday(Date birthday);

    String getName();

    String getAddress();

    Date getBirthday();

}
