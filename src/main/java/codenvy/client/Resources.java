package codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {

    public static final Resources IMPL = GWT.create(Resources.class);

    @Source("Styles.css")
    Styles Styles();

    public interface Styles extends CssResource{

        String labelStyle();

        String textBoxStyle();

        String dialogButtonStyle();

        String mainPageButtonStyle();
    }
}
