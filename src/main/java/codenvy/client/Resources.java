package codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {

    public static final Resources IMPL = GWT.create(Resources.class);

    public interface Styles extends CssResource {

        String labelStyle();

        String textBoxStyle();

        String buttonStyle();
        String cellTableStyle();

    }

    @Source("Styles.css")
    Styles Styles();

    @Source("Text.txt")
    TextResource text();

    @Source("UserImage.jpg")
    ImageResource userImage();

}
