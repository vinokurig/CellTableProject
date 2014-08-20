package codenvy.client.dialog;

import codenvy.client.MessageConstants;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.inject.Named;
import java.util.Date;

public class DialogViewImpl extends DialogBox implements DialogView {

    @Singleton
    interface DialogViewImplUiBinder extends UiBinder<Widget, DialogViewImpl> {
    }

    private ActionDelegate delegate;

    @UiField
    TextBox name;

    @UiField
    TextBox address;

    @UiField
    DateBox birthday;

    @UiField(provided = true)
    MessageConstants messages;

    @Inject
    public DialogViewImpl(DialogViewImplUiBinder uiBinder, MessageConstants messages, @Named("myString") String someText) {
        this.messages = messages;

        add(uiBinder.createAndBindUi(this));

        setText(someText);
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
    public void setName(String name) {
        this.name.setValue(name);
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public void setAddress(String address) {
        this.address.setValue(address);
    }

    @Override
    public String getAddress() {
        return address.getValue();
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday.setValue(birthday);
    }

    @Override
    public Date getBirthday() {
        return birthday.getValue();
    }

    @UiHandler("saveButton")
    public void onSaveButtonClicked(ClickEvent event) {
        delegate.onSaveButtonClicked();
    }

    @UiHandler("closeButton")
    public void onCloseButtonClicked(ClickEvent event) {
        delegate.onCloseButtonClicked();
    }
}
