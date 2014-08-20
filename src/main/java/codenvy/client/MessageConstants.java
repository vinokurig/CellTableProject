package codenvy.client;

import com.google.gwt.i18n.client.Messages;
import com.google.inject.Singleton;

@Singleton
public interface MessageConstants extends Messages {

    String mpBtnAdd();

    String mpBtnEdit();

    String mpBtnDelete();

    String mpColName();

    String mpColAddress();

    String mpColBirthday();

    String dialogHeadLabel();

    String dialogNameLabel();

    String dialogAddressLabel();

    String dialogBirthdayLabel();

    String dialogBtnSave();

    String dialogBtnClose();

}
