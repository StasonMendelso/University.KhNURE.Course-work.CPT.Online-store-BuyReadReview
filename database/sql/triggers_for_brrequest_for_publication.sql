# set final_content_of_review
DELIMITER //

DROP TRIGGER IF EXISTS trg_insert_book_review_final //

CREATE TRIGGER trg_insert_book_review_final
    AFTER INSERT ON book_review_request_for_publication
    FOR EACH ROW
BEGIN
    DECLARE edited_content VARCHAR(7200);

    SELECT edited_content_of_review INTO edited_content
    FROM book_review_edited
    WHERE check_request_id = NEW.check_request_id;

    INSERT INTO book_review_final (final_content_of_review, id)
    VALUES (edited_content, NEW.id);
END //
DELIMITER ;

# set status for review_request for publication
DELIMITER //

DROP TRIGGER IF EXISTS trg_set_default_status_id //

CREATE TRIGGER trg_set_default_status_id
    BEFORE INSERT ON book_review_request_for_publication
    FOR EACH ROW
BEGIN
    SET NEW.request_for_publication_status_id = 1;
END //

DELIMITER ;

# calculating final score + bonuses of review
DELIMITER //
CREATE TRIGGER calculate_final_score_and_bonuses
    AFTER INSERT ON book_review_request_for_publication_criterion_score
    FOR EACH ROW
BEGIN
    UPDATE book_review_request_for_publication
    SET final_score = (SELECT SUM(score_value) FROM book_review_request_for_publication_criterion_score WHERE request_for_publication_id = NEW.request_for_publication_id),
        bonuses = (SELECT SUM(score_value) FROM book_review_request_for_publication_criterion_score WHERE request_for_publication_id = NEW.request_for_publication_id) * 0.1
    WHERE id = NEW.request_for_publication_id;
END //
DELIMITER ;

# set state after publication of review
DELIMITER //
CREATE TRIGGER set_book_review_request_published
    AFTER INSERT
    ON book_review
    FOR EACH ROW
BEGIN
    UPDATE book_review_request_for_publication
    SET request_for_publication_status_id = (SELECT id
                                             FROM book_review_request_for_publication_status
                                             WHERE book_review_request_for_publication_status.status = 'Опубліковано')
    WHERE book_review_request_for_publication.id = NEW.request_for_publication_id;
END //
DELIMITER ;

# start automatically add data to book_review
# set client to review
DELIMITER //

DROP TRIGGER IF EXISTS set_client_id_trigger //

CREATE TRIGGER set_client_id_trigger
    BEFORE INSERT ON book_review
    FOR EACH ROW
BEGIN
    DECLARE request_client_id BIGINT;
    DECLARE request_check_id BIGINT;
    DECLARE request_draft_id BIGINT;

    SELECT check_request_id INTO request_check_id
    FROM book_review_request_for_publication
    WHERE id = NEW.request_for_publication_id;

    SELECT book_review_draft_id INTO request_draft_id
    FROM book_review_request_for_checking
    WHERE id = request_check_id;

    SELECT client_id INTO request_client_id
    FROM book_review_draft
    WHERE id = request_draft_id;

    SET NEW.client_id = request_client_id;
END //

DELIMITER ;

# set content to book_review
DELIMITER //

DROP TRIGGER IF EXISTS set_book_review_content_trigger //

CREATE TRIGGER set_book_review_content_trigger
    BEFORE INSERT ON book_review
    FOR EACH ROW
BEGIN
    DECLARE review_content VARCHAR(7200);

    -- Get the final_content_of_review from book_review_final
    SELECT final_content_of_review INTO review_content
    FROM book_review_final
    WHERE id = NEW.request_for_publication_id;

    -- Set the content in the new row being inserted only if it is null
    IF NEW.content IS NULL THEN
        SET NEW.content = review_content;
END IF;
END //

DELIMITER ;

# set pseudonym to book_review
DELIMITER //

DROP TRIGGER IF EXISTS set_client_pseudonym_trigger //

CREATE TRIGGER set_client_pseudonym_trigger
    BEFORE INSERT ON book_review
    FOR EACH ROW
BEGIN
    DECLARE request_client_pseudonym VARCHAR(60);
    DECLARE request_check_id BIGINT;
    DECLARE request_draft_id BIGINT;

    SELECT check_request_id INTO request_check_id
    FROM book_review_request_for_publication
    WHERE id = NEW.request_for_publication_id;

    SELECT book_review_draft_id INTO request_draft_id
    FROM book_review_request_for_checking
    WHERE id = request_check_id;

    SELECT pseudonym INTO request_client_pseudonym
    FROM book_review_draft
    WHERE id = request_draft_id;

    SET NEW.client_pseudonym = request_client_pseudonym;
END //

DELIMITER ;

# set title to book_review
DELIMITER //

DROP TRIGGER IF EXISTS set_title_trigger //

CREATE TRIGGER set_title_trigger
    BEFORE INSERT ON book_review
    FOR EACH ROW
BEGIN
    DECLARE request_title  VARCHAR(20);
    DECLARE request_check_id BIGINT;
    DECLARE request_draft_id BIGINT;

    SELECT check_request_id INTO request_check_id
    FROM book_review_request_for_publication
    WHERE id = NEW.request_for_publication_id;

    SELECT book_review_draft_id INTO request_draft_id
    FROM book_review_request_for_checking
    WHERE id = request_check_id;

    SELECT title INTO request_title
    FROM book_review_draft
    WHERE id = request_draft_id;

    SET NEW.review_title = request_title;
END //

DELIMITER ;

# set book_id to review
DELIMITER //

DROP TRIGGER IF EXISTS set_book_id_trigger //

CREATE TRIGGER set_book_id_trigger
    BEFORE INSERT ON book_review
    FOR EACH ROW
BEGIN
    DECLARE request_book_id BIGINT;
    DECLARE request_check_id BIGINT;
    DECLARE request_draft_id BIGINT;

    SELECT check_request_id INTO request_check_id
    FROM book_review_request_for_publication
    WHERE id = NEW.request_for_publication_id;

    SELECT book_review_draft_id INTO request_draft_id
    FROM book_review_request_for_checking
    WHERE id = request_check_id;

    SELECT book_id INTO request_book_id
    FROM book_review_draft
    WHERE id = request_draft_id;

    SET NEW.book_id = request_book_id;
END //

DELIMITER ;
# end automatically add data to book_review