package codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface MessageConstants extends Messages {
    public static final MessageConstants MESSAGES = GWT.create(MessageConstants.class);

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
