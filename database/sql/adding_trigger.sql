DELIMITER
$$
CREATE TRIGGER minus_book_quantity
    BEFORE INSERT
    ON order_item
    FOR EACH ROW
BEGIN
    DECLARE oldQuantity INT;

    SELECT quantity
    INTO oldQuantity
    FROM book
    WHERE book.id = NEW.book_id;

    IF oldQuantity - NEW.quantity >= 0 THEN
        UPDATE book
        SET book.quantity = oldQuantity - NEW.quantity
        WHERE book.id = NEW.book_id;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The quantity of book can`t be negative';
    END IF;
END
$$
DELIMITER ;

DELIMITER
$$
CREATE TRIGGER change_book_quantity
    BEFORE UPDATE
    ON order_item
    FOR EACH ROW
BEGIN
    DECLARE oldQuantity INT;

    SELECT quantity
    INTO oldQuantity
    FROM book
    WHERE book.id = OLD.book_id;

    IF oldQuantity - (NEW.quantity - OLD.quantity) >= 0 THEN
        UPDATE book
        SET book.quantity = oldQuantity - (NEW.quantity - OLD.quantity)
        WHERE book.id = NEW.book_id;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The quantity of book can`t be negative';
    END IF;
END
$$
DELIMITER ;

