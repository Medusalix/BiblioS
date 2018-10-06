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
import de.medusalix.biblios.database.objects.BorrowedBook;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

public class BorrowedBookExceededCell extends TableCell<BorrowedBook, Boolean>
{
    private ImageView imageView = new ImageView();

    @Override
    protected void updateItem(Boolean item, boolean empty)
    {
        super.updateItem(item, empty);

        if (empty)
        {
            setGraphic(null);

            return;
        }

        setGraphic(imageView);

        imageView.setImage(item ? Consts.Images.EXCEEDED : Consts.Images.NOT_EXCEEDED);
    }
}
