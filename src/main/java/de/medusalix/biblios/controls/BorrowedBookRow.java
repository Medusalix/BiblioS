/*
 * Copyright (C) 2016 Medusalix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.medusalix.biblios.controls;

import de.medusalix.biblios.Consts;
import de.medusalix.biblios.controllers.MainWindowController;
import de.medusalix.biblios.database.objects.BorrowedBook;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class BorrowedBookRow extends TableRow<BorrowedBook>
{
    private MenuItem extendBookItem = new MenuItem(
        Consts.Strings.EXTEND_MENU_ITEM_TEXT,
        new ImageView(Consts.Images.EXTEND_MENU_ITEM)
    );

    private ContextMenu contextMenu = new ContextMenu(extendBookItem);

    public BorrowedBookRow(MainWindowController controller)
    {
        setOnDragDetected(event ->
        {
            if (getItem() == null)
            {
                return;
            }

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();

            // Content is necessary for it to work
            content.putString("");

            dragboard.setContent(content);
            dragboard.setDragView(Consts.Images.RETURN_BOOK);
        });

        extendBookItem.setOnAction(event -> controller.extendBorrowedBook(getItem()));
    }

    @Override
    protected void updateItem(BorrowedBook item, boolean empty)
    {
        super.updateItem(item, empty);

        if (empty)
        {
            setContextMenu(null);

            return;
        }

        setContextMenu(contextMenu);
    }
}
