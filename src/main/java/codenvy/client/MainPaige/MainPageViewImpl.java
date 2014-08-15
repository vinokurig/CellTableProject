package codenvy.client.MainPaige;

import codenvy.client.Resources;
import codenvy.client.MainPaige.events.DeleteUserEvent;
import codenvy.client.mvp.models.User;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.Date;
import java.util.List;

public class MainPageViewImpl extends Composite implements MainPageView {

    interface MainPageViewImplUiBinder extends UiBinder<Widget, MainPageViewImpl> {}

    private static MainPageViewImplUiBinder uiBinder = GWT.create(MainPageViewImplUiBinder.class);

    private ActionDelegate presenter;

    private SimpleEventBus eventBus;

    @UiField(provided = true)
    CellTable<User> usersTable;

    public MainPageViewImpl(SimpleEventBus eventBus) {
        this.eventBus = eventBus;

        usersTable = createTable();

        initWidget(uiBinder.createAndBindUi(this));
    }

    private CellTable createTable() {

        CellTable usersTable = new CellTable<User>();

        usersTable.addStyleName(Resources.IMPL.Styles().cellTableStyle());

        // Add a text column to show the name.
        TextColumn<User> nameColumn = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getName();
            }
        };

        // Add a date column to show the birthday.
        DateCell dateCell = new DateCell();
        Column<User, Date> dateColumn = new Column<User, Date>(dateCell) {
            @Override
            public Date getValue(User object) {
                return object.getBirthday();
            }
        };

        // Add a text column to show the address.
        TextColumn<User> addressColumn = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getAddress();
            }
        };

        final SingleSelectionModel<User> selModel = new SingleSelectionModel<User>();

        usersTable.setSelectionModel(selModel);

        usersTable.addColumn(nameColumn, "Name");
        usersTable.addColumn(addressColumn, "Address");
        usersTable.addColumn(dateColumn, "Birthday");

        usersTable.setColumnWidth(nameColumn, 40, Style.Unit.PCT);
        usersTable.setColumnWidth(addressColumn, 40, Style.Unit.PCT);
        usersTable.setColumnWidth(dateColumn, 20, Style.Unit.PCT);

        selModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                User selectedUser = selModel.getSelectedObject();

                presenter.onUserSelected(selectedUser);
            }
        });

        return usersTable;
    }

    @Override
    public void setPresenter(ActionDelegate presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setUser(List<User> users) {
        usersTable.setRowData(users);
    }

    @UiHandler("add")
    public void onAddButtonClicked(ClickEvent event) {
        presenter.onAddButtonClicked();
    }

    @UiHandler("edit")
    public void onEditButtonClicked(ClickEvent event) {
        presenter.onEditButtonClicked();
    }

    @UiHandler("delete")
    public void onDeleteButtonClicked(ClickEvent event) {
        eventBus.fireEvent(new DeleteUserEvent());
    }

}
