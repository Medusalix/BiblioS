package de.medusalix.biblios.controls;

import de.medusalix.biblios.controllers.UpdatableController;
import de.medusalix.biblios.core.Consts;
import de.medusalix.biblios.database.access.BorrowedBooks;
import de.medusalix.biblios.managers.ExceptionManager;
import de.medusalix.biblios.pojos.BorrowedBookTableItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skife.jdbi.v2.exceptions.DBIException;

import java.time.LocalDate;

public class BorrowedBookRow extends TableRow<BorrowedBookTableItem>
{
    private Logger logger = LogManager.getLogger(BorrowedBookRow.class);

    private MenuItem extendBookItem = new MenuItem(Consts.Strings.EXTEND_MENU_ITEM_TEXT, new ImageView(Consts.Images.EXTEND_MENU_ITEM_IMAGE));

    private ContextMenu contextMenu = new ContextMenu(extendBookItem);

    public BorrowedBookRow(UpdatableController controller, BorrowedBooks borrowedBooks)
    {
        setOnDragDetected(event ->
        {
            if (getItem() != null)
            {
                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();

                content.putString(String.valueOf(getItem().getId()));

                dragboard.setContent(content);
            }
        });

        extendBookItem.setOnAction(event ->
        {
            String returnDate = LocalDate.parse(getItem().getReturnDate(), Consts.Misc.DATE_FORMATTER).plusDays(14).format(Consts.Misc.DATE_FORMATTER);

            try
            {
                borrowedBooks.updateReturnDate(getItem().getId(), returnDate);

                logger.info("Borrowed book extended");

                controller.updateData();
            }

            catch (DBIException e)
            {
                ExceptionManager.log(e);
            }
        });
    }

    @Override
    protected void updateItem(BorrowedBookTableItem item, boolean empty)
    {
        super.updateItem(item, empty);

        if (!empty)
            setContextMenu(contextMenu);

        else
            setContextMenu(null);
    }
}