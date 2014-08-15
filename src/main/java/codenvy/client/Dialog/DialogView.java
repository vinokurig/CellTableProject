package codenvy.client.Dialog;

import com.google.gwt.user.client.ui.IsWidget;

import java.util.Date;

public interface DialogView extends IsWidget {

    public interface Presenter {
        void onSaveButtonClicked();

        void onCloseButtonClicked();
    }

    void setPresenter(Presenter presenter);

    void showDialog();

    void closeDialog();

    void setName(String name);

    void setAddress(String address);

    void setBirthday(Date birthday);

    String getName();

    String getAddress();

    Date getBirthday();

}
