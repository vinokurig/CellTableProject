package codenvy.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.google.inject.Singleton;

@Singleton
public interface Resources extends ClientBundle {

    public interface Styles extends CssResource {
        String labelStyle();

        String textBoxStyle();

        String buttonStyle();

        String cellTableStyle();

        String userCardTexAreaStyle();

        String notesTextAreaStyle();

        String notesButtonStyle();
    }

    @Source("Styles.css")
    Styles styles();

    @Source("Text.txt")
    TextResource text();

    @Source("UserImage.jpg")
    ImageResource userImage();

}
