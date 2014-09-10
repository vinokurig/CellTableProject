package codenvy.client.userCard;

import codenvy.client.MessageConstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Date;

public class UserCardViewImpl extends DialogBox implements UserCardView {

    @UiField(provided = true)
    final MessageConstants messages;
    @UiField
    TextBox name;
    @UiField
    TextBox address;
    @UiField
    DateBox birthday;
    @UiField
    TextArea notes;
    private ActionDelegate delegate;

    @Inject
    public UserCardViewImpl(DialogViewImplUiBinder uiBinder, MessageConstants messages) {
        this.messages = messages;

        add(uiBinder.createAndBindUi(this));

        setText(messages.userCardTitle());
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void showDialog() {
        center();
        show();
    }

    @Override
    public void closeDialog() {
        hide();
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public void setName(String name) {
        this.name.setValue(name);
    }

    @Override
    public String getAddress() {
        return address.getValue();
    }

    @Override
    public void setAddress(String address) {
        this.address.setValue(address);
    }

    @Override
    public Date getBirthday() {
        return birthday.getValue();
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday.setValue(birthday);
    }

    @Override
    public String getNotes() {
        return notes.getValue();
    }

    @Override
    public void setNotes(String notes) {
        this.notes.setValue(notes);
    }

    @UiHandler("saveButton")
    public void onSaveButtonClicked(ClickEvent event) {
        delegate.onSaveButtonClicked();
    }

    @UiHandler("closeButton")
    public void onCloseButtonClicked(ClickEvent event) {
        delegate.onCloseButtonClicked();
    }

    @Singleton
    interface DialogViewImplUiBinder extends UiBinder<Widget, UserCardViewImpl> {
    }
}
