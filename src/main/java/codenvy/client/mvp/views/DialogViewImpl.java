package codenvy.client.mvp.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;

public class DialogViewImpl extends DialogBox implements DialogView {

    interface DialogViewImplUiBinder extends UiBinder<Widget, DialogViewImpl> {}

    private static DialogViewImplUiBinder uiBinder = GWT.create(DialogViewImplUiBinder.class);

    private Presenter presenter;

    @UiField
    TextBox name;

    @UiField
    TextBox address;

    @UiField
    DateBox birthday;

    public DialogViewImpl() {
        add(uiBinder.createAndBindUi(this));

        setText("User");
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
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
    void onSaveButtonClicked(ClickEvent event) {
        presenter.onSaveButtonClicked();
    }

    @UiHandler("closeButton")
    void onCloseButtonClicked(ClickEvent event) {
        presenter.onCloseButtonClicked();
    }
}
