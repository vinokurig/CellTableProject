package codenvy.client.notes;

import codenvy.client.MessageConstants;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class NotesViewImpl extends DialogBox implements NotesView {

    @Singleton
    interface NotesViewImplUiBinder extends UiBinder<Widget, NotesViewImpl> {
    }

    private ActionDelegate delegate;

    @UiField
    TextArea notesTextArea;

    @UiField(provided = true)
    MessageConstants messages;

    @Inject
    public NotesViewImpl(NotesViewImplUiBinder uiBinder, MessageConstants messages) {
        this.messages = messages;

        add(uiBinder.createAndBindUi(this));

        setText(messages.notesTitle());
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void showNotes() {
        center();
        show();
    }

    @Override
    public void closeNotes() {
        hide();
    }

    @Override
    public void setNotesText(String text) {
        this.notesTextArea.setValue(text);
    }

    @Override
    public String getNotesText() {
        return notesTextArea.getValue();
    }

    @UiHandler("saveButton")
    public void onSaveButtonClicked(ClickEvent event) {
        delegate.onSaveButtonClicked();
    }
}
