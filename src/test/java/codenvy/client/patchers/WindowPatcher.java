package codenvy.client.patchers;

import com.google.gwt.user.client.Window;
import com.googlecode.gwt.test.patchers.PatchClass;
import com.googlecode.gwt.test.patchers.PatchMethod;

@PatchClass(Window.class)
public class WindowPatcher {
    @PatchMethod(override = true)
    static void alert(String msg) {
        System.out.println(msg + " (printed from patcher)");
    }
}
