DROP TABLE client_note_for_wishes;
DROP TABLE book_review_edited;
DROP TABLE receiver_contact;
DROP TABLE card_payment;
DROP TABLE cash_payment;
DROP TABLE edit_review_permission;
DROP TABLE card_returned_funds_payment;
DROP TABLE delivery_item;
DROP TABLE book_review_request_for_publication_cancel;
DROP TABLE deliveryman_contact;
DROP TABLE delivery;
DROP TABLE delivery_status;
DROP TABLE delivery_request;
DROP TABLE delivery_request_status;
DROP TABLE author_book;
DROP TABLE author;
DROP TABLE book_review_request_for_publication_characteristic;
DROP TABLE book_review_characteristic;
DROP TABLE book_review_request_for_publication_criterion_score;
DROP TABLE book_review_score_criterion;
DROP TABLE cash_returned_funds_payment;
DROP TABLE returned_funds;
DROP TABLE returned_funds_method;
DROP TABLE returned_funds_status;
DROP TABLE book_review_request_for_checking_review_requirement;
DROP TABLE book_review_requirement;
DROP TABLE cash_on_delivery_payment;
DROP TABLE payment_info;
DROP TABLE payment_status;
DROP TABLE payment_method;
DROP TABLE book_genre;
DROP TABLE genre;
DROP TABLE book_review_final;
DROP TABLE book_review_comment;
DROP TABLE book_file;
DROP TABLE `file`;
DROP TABLE book_review_with_tag;
DROP TABLE book_review_tag;
DROP TABLE book_review_tag_category;
DROP TABLE book_review;
DROP TABLE book_review_request_for_publication;
DROP TABLE book_review_request_for_publication_status;
DROP TABLE book_review_request_for_checking;
DROP TABLE book_review_draft;
DROP TABLE book_review_request_for_checking_status;
DROP TABLE buyer_contact;
DROP TABLE order_item;
DROP TABLE cart_item;
DROP TABLE book;
DROP TABLE publisher;
DROP TABLE cart;
DROP TABLE courier_delivery;
DROP TABLE nova_poshta_delivery;
DROP TABLE nova_poshta_delivery_status;
DROP TABLE shop_delivery;
DROP TABLE shop_delivery_status;
DROP TABLE delivery_info;
DROP TABLE delivery_method;
DROP TABLE `order`;
DROP TABLE `client`;
DROP TABLE `user`;
DROP TABLE `role`;
DROP TABLE order_status;


CREATE TABLE author
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    first_name            VARCHAR(60) NULL,
    last_name             VARCHAR(60) NULL,
    pseudonym             VARCHAR(60) NULL,
    PRIMARY KEY (id)
);


CREATE TABLE author_book
(
    book_id               BIGINT NOT NULL,
    author_id             BIGINT NOT NULL,
    PRIMARY KEY (book_id,author_id)
);


CREATE TABLE book
(
    paper_quantity        INTEGER NOT NULL
        CONSTRAINT  book_paper_quantity CHECK (paper_quantity > 0),
    title                 VARCHAR(250) NOT NULL
        CONSTRAINT  book_title CHECK (title != ""),
    `description`           MEDIUMTEXT NOT NULL
        CONSTRAINT  book_description CHECK (`description` != ""),
    isbn                  VARCHAR(18) NOT NULL
        CONSTRAINT  book_isbn CHECK (isbn != ""),
    hidden                boolean NOT NULL DEFAULT 0,
    price                 DECIMAL(30,8) NOT NULL DEFAULT 0
        CONSTRAINT  book_price CHECK (price >=0),
    quantity              FLOAT NOT NULL DEFAULT 0
        CONSTRAINT  book_quantity CHECK (quantity >=0),
    article               VARCHAR(25) NOT NULL
        CONSTRAINT  book_article CHECK (article != ""),
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    publisher_id          BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unique_isbn ON book
    (
     isbn
        );


CREATE UNIQUE INDEX unique_article ON book
    (
     article
        );


CREATE INDEX XIE2book ON book
    (
     title,
     publisher_id
        );


CREATE TABLE book_file
(
    book_id               BIGINT NOT NULL,
    file_id               BIGINT NOT NULL,
    PRIMARY KEY (file_id)
);


CREATE TABLE book_genre
(
    book_id               BIGINT NOT NULL,
    genre_id              BIGINT NOT NULL,
    PRIMARY KEY (book_id,genre_id)
);


CREATE TABLE book_review
(
    content               VARCHAR(7200) NOT NULL
        CONSTRAINT  book_review_content CHECK (content != ""),
    client_pseudonym      VARCHAR(60) NULL,
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    book_id               BIGINT NULL,
    request_for_publication_id  BIGINT NULL,
    book_review_rating    FLOAT NOT NULL DEFAULT 0
        CONSTRAINT  book_review_book_review_rating CHECK (book_review_rating >= 0),
    client_id             BIGINT NULL,
    PRIMARY KEY (id)
);


CREATE INDEX XIE1book_review_pseudonym ON book_review
    (
     client_pseudonym
        );


CREATE TABLE book_review_characteristic
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(100) NOT NULL
        CONSTRAINT  book_review_characteristic_name CHECK (`name` != ""),
    PRIMARY KEY (id)
);


CREATE INDEX XIE1review_characteristic_name ON book_review_characteristic
    (
     `name`
        );


CREATE TABLE book_review_comment
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `comment`               VARCHAR(200) NOT NULL
        CONSTRAINT  book_review_comment_comment CHECK (`comment` != ""),
    book_review_id        BIGINT NOT NULL,
    client_id             BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE book_review_draft
(
    content               VARCHAR(7200) NOT NULL
        CONSTRAINT  book_review_draft_content CHECK (content != ""),
    title                 VARCHAR(20) NOT NULL
        CONSTRAINT  book_review_draft_title CHECK (title != ""),
    pseudonym             VARCHAR(20) NULL
        CONSTRAINT  book_review_draft_pseudonym CHECK (pseudonym != ""),
    book_id               BIGINT NULL,
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    client_id             BIGINT NOT NULL,
    created_at            TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);


CREATE INDEX XIE1book_review_draft_title ON book_review_draft
    (
     title
        );


CREATE TABLE book_review_edited
(
    edited_content_of_review  VARCHAR(7200) NOT NULL
        CONSTRAINT  book_review_edited_edited_content_of_review CHECK (edited_content_of_review != ""),
    check_request_id      BIGINT NOT NULL,
    PRIMARY KEY (check_request_id)
);


CREATE TABLE book_review_final
(
    final_content_of_review  VARCHAR(7200) NOT NULL
        CONSTRAINT  book_review_final_final_content_of_review CHECK (final_content_of_review != ""),
    id                    BIGINT NOT NULL ,
    PRIMARY KEY (id)
);


CREATE TABLE book_review_request_for_checking
(
    `comment`             VARCHAR(300) NOT NULL
        CONSTRAINT  book_review_request_for_checking_comment CHECK (`comment` != "" ),
    book_review_request_for_checking_status_id  BIGINT NOT NULL,
    editor_id             BIGINT NULL,
    book_review_draft_id  BIGINT NOT NULL,
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);


CREATE UNIQUE INDEX XAK1book_review_request_for_checking_draft_id ON book_review_request_for_checking
    (
     book_review_draft_id
        );


CREATE INDEX XAK2book_review_request_for_checking_status_id ON book_review_request_for_checking
    (
     book_review_request_for_checking_status_id
        );


CREATE TABLE book_review_request_for_checking_review_requirement
(
    review_request_for_checking_id  BIGINT NOT NULL,
    requirement_id        BIGINT NOT NULL DEFAULT 0,
    meeting_requirement   boolean NOT NULL DEFAULT TRUE,
    PRIMARY KEY (review_request_for_checking_id,requirement_id)
);


CREATE TABLE book_review_request_for_checking_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                 VARCHAR(60) NOT NULL
        CONSTRAINT  book_review_request_for_checking_status_name CHECK (name != ""),
    PRIMARY KEY (id)
);


CREATE TABLE book_review_request_for_publication
(
    comment_admin         VARCHAR(300) NULL,
    check_request_id      BIGINT NOT NULL,
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    editor_id             BIGINT NULL,
    admin_id              BIGINT NULL,
    request_for_publication_status_id  BIGINT NOT NULL,
    final_score           INTEGER NOT NULL DEFAULT 0
        CONSTRAINT  book_review_request_for_publication_final_score CHECK (final_score >= 0),
    bonuses               INTEGER NOT NULL DEFAULT 0
        CONSTRAINT  book_review_request_for_publication_bonuses CHECK (bonuses >= 0),
    creation_date         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    comment_editor        VARCHAR(300) NULL,
    PRIMARY KEY (id)
);


CREATE INDEX XIE1request_for_publication_date ON book_review_request_for_publication
    (
     creation_date
        );


CREATE INDEX XIE2request_for_publication ON book_review_request_for_publication
    (
     editor_id
        );


CREATE INDEX XIE3request_for_publication_admin ON book_review_request_for_publication
    (
     admin_id
        );


CREATE TABLE book_review_request_for_publication_cancel
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    reason                VARCHAR(200) NOT NULL
        CONSTRAINT  book_review_request_for_publication_cancel_reason CHECK (reason != ""),
    book_review_request_for_publication_id  BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE book_review_request_for_publication_characteristic
(
    request_for_publication_id  BIGINT NOT NULL,
    review_characteristic_id  BIGINT NOT NULL,
    PRIMARY KEY (request_for_publication_id,review_characteristic_id)
);


CREATE TABLE book_review_request_for_publication_criterion_score
(
    request_for_publication_id  BIGINT NOT NULL,
    review_score_criterion_id  BIGINT NOT NULL,
    score_value           INTEGER NOT NULL DEFAULT 0
        CONSTRAINT  book_review_request_for_publication_criterion_score_score_value CHECK (score_value >= 0),
    PRIMARY KEY (request_for_publication_id,review_score_criterion_id)
);


CREATE TABLE book_review_request_for_publication_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `status`                VARCHAR(20) NOT NULL
        CONSTRAINT  book_review_request_for_publication_status CHECK (status != ""),
    PRIMARY KEY (id)
);


CREATE TABLE book_review_requirement
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    title                 VARCHAR(60) NOT NULL
        CONSTRAINT  book_review_requirement_title CHECK (title != ""),
    `description`           VARCHAR(300) NOT NULL
        CONSTRAINT  book_review_requirement_description CHECK (`description` != ""),
    PRIMARY KEY (id)
);


CREATE UNIQUE INDEX XAK1book_review_requirement_description ON book_review_requirement
    (
     description
        );


CREATE TABLE book_review_score_criterion
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(60) NOT NULL
        CONSTRAINT  book_review_score_criterion_name CHECK (`name` != ""),
    description           VARCHAR(100) NULL,
    PRIMARY KEY (id)
);


CREATE INDEX XIE1review_score_criterion_name ON book_review_score_criterion
    (
     `name`
        );


CREATE TABLE book_review_tag
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(100) NOT NULL
        CONSTRAINT  book_review_tag_name CHECK (`name` != ""),
    description           VARCHAR(60) NULL,
    book_review_tag_category_id  BIGINT NULL,
    PRIMARY KEY (id)
);


CREATE INDEX XIE1book_review_tag_name ON book_review_tag
    (
     `name`
        );


CREATE TABLE book_review_tag_category
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(60) NOT NULL
        CONSTRAINT  book_review_tag_category_name CHECK (`name` != ""),
    PRIMARY KEY (id)
);


CREATE INDEX XIE1book_review_tag_category_name ON book_review_tag_category
    (
     `name`
        );


CREATE TABLE book_review_with_tag
(
    book_review_id        BIGINT NOT NULL,
    book_review_tag_id    BIGINT NOT NULL,
    PRIMARY KEY (book_review_id,book_review_tag_id)
);


CREATE TABLE buyer_contact
(
    first_name            VARCHAR(60) NOT NULL
        CONSTRAINT  buyer_contact_first_name CHECK (first_name != ""),
    last_name             VARCHAR(60) NOT NULL
        CONSTRAINT  buyer_contact_last_name CHECK (last_name != ""),
    middle_name           VARCHAR(60) NOT NULL
        CONSTRAINT  buyer_contact_middle_name CHECK (middle_name != ""),
    email                 VARCHAR(100) NOT NULL
        CONSTRAINT  buyer_contact_email CHECK (email != ""),
    telephone_number      VARCHAR(25) NOT NULL
        CONSTRAINT  buyer_contact_telephone_number CHECK (telephone_number != ""),
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE card_payment
(
    sender_account        VARCHAR(40) NOT NULL,
    receiver_account      VARCHAR(40) NOT NULL,
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE card_returned_funds_payment
(
    sender_account        VARCHAR(40) NOT NULL,
    receiver_account      VARCHAR(40) NOT NULL,
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE cart
(
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE cart_item
(
    quantity              FLOAT NOT NULL DEFAULT 0
        CONSTRAINT  cart_item_quantity CHECK (quantity >=0),
    cart_id               BIGINT NOT NULL,
    book_id               BIGINT NOT NULL,
    PRIMARY KEY (cart_id,book_id)
);


CREATE TABLE cash_on_delivery_payment
(
    `change`               DECIMAL(30,8) NOT NULL DEFAULT 0
        CONSTRAINT  cash_on_delivery_payment_change CHECK (`change` >= 0),
    amount_received       DECIMAL(30,8) NOT NULL
        CONSTRAINT  cash_on_delivery_payment_amount_received CHECK (amount_received >=0),
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE cash_payment
(
    `change`                DECIMAL(30,8) NOT NULL DEFAULT 0
        CONSTRAINT  cash_payment_change CHECK (cash_payment.`change` >= 0),
    amount_received       DECIMAL(30,8) NOT NULL
        CONSTRAINT  cash_payment_amount_received CHECK (amount_received >=0),
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE cash_returned_funds_payment
(
    amount_returned          DECIMAL(30,8) NOT NULL
        CONSTRAINT  cash_returned_funds_payment_amount_given CHECK (amount_returned >= 0),
    `change`               DECIMAL(30,8) NOT NULL DEFAULT 0
        CONSTRAINT  cash_returned_funds_payment_change CHECK (cash_returned_funds_payment.`change` >= 0),
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE `client`
(
    bonuses               INTEGER NOT NULL DEFAULT 0
        CONSTRAINT  client_bonuses CHECK (bonuses >= 0),
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE client_note_for_wishes
(
    wish_description      MEDIUMTEXT NOT NULL,
    manager_id            BIGINT NOT NULL,
    id_request            BIGINT NOT NULL,
    PRIMARY KEY (id_request)
);


CREATE TABLE courier_delivery
(
    address               VARCHAR(100) NOT NULL,
    price                 DECIMAL(30,8) NOT NULL DEFAULT 0,
    id                    BIGINT NOT NULL ,
    PRIMARY KEY (id)
);


CREATE TABLE delivery
(
    description_for_status  MEDIUMTEXT NOT NULL,
    courier_id            BIGINT NOT NULL,
    id                    BIGINT NOT NULL ,
    delivery_status_id    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE delivery_info
(
    id                    BIGINT NOT NULL,
    delivery_method_id    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE delivery_item
(
    price                 DECIMAL(30,8) NOT NULL
        CONSTRAINT  delivery_item_price CHECK (delivery_item.price >=0),
    quantity              FLOAT NOT NULL
        CONSTRAINT  delivery_item_quantity CHECK (quantity >=0),
    book_title            VARCHAR(250) NOT NULL
        CONSTRAINT  delivery_item_book_title CHECK (book_title  != ""),
    book_id               BIGINT NOT NULL,
    delivery_id           BIGINT NOT NULL,
    PRIMARY KEY (book_id,delivery_id)
);


CREATE TABLE delivery_method
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    name                  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE delivery_request
(
    description_for_status  MEDIUMTEXT NOT NULL,
    manager_id            BIGINT NOT NULL,
    courier_id            BIGINT NULL,
    order_id              BIGINT NOT NULL,
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    delivery_request_status_id  BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE delivery_request_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    request_delivery_status  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE delivery_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    courier_delivery_status  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE deliveryman_contact
(
    telephone_number      VARCHAR(25) NOT NULL
        CONSTRAINT  deliveryman_contact_telephone_number CHECK (deliveryman_contact.telephone_number != ""),
    id                    BIGINT NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE edit_review_permission
(
    is_permited           boolean NOT NULL,
    review_draft_id       BIGINT NOT NULL,
    PRIMARY KEY (review_draft_id)
);


CREATE TABLE `file`
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    name                  VARCHAR(200) NOT NULL,
    extension             VARCHAR(10) NOT NULL,
    relative_path         VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unique_relative_path ON `file`
    (
     relative_path
        );


CREATE TABLE genre
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    name                  VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE nova_poshta_delivery
(
    town_address          VARCHAR(100) NOT NULL,
    departure_number      INTEGER NOT NULL,
    waybill               DECIMAL(30,8) NOT NULL DEFAULT 0,
    invoice_number 		  VARCHAR(40) NOT NULL UNIQUE,
    id                    BIGINT NOT NULL ,
    nova_poshta_delivery_status  BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE nova_poshta_delivery_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    delivery_status       VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE `order`
(
    order_status_id       BIGINT NOT NULL,
    created_datetime      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    client_id             BIGINT NULL,
    manager_id            BIGINT NULL,
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    description           VARCHAR(400) NULL,
    used_bonuses          INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);


CREATE TABLE order_item
(
    price                 DECIMAL(30,8) NOT NULL
        CONSTRAINT  order_item_price CHECK (price >=0),
    title                 VARCHAR(250) NOT NULL
        CONSTRAINT  order_item_title CHECK (title != ""),
    quantity              FLOAT NOT NULL DEFAULT 0
        CONSTRAINT  order_item_quantity CHECK (quantity >=0),
    order_id              BIGINT NOT NULL,
    book_id               BIGINT NOT NULL,
    PRIMARY KEY (order_id,book_id)
);


CREATE TABLE order_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    status                VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE payment_info
(
    total_amount          DECIMAL(30,8) NOT NULL
        CONSTRAINT  payment_info_total_amount CHECK (total_amount >= 0),
    description           VARCHAR(300) NULL,
    id                    BIGINT NOT NULL,
    payment_status_id     BIGINT NOT NULL,
    payment_method_id     BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE payment_method
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE payment_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    status                VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE publisher
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    name                  VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE receiver_contact
(
    first_name            VARCHAR(60) NOT NULL
        CONSTRAINT  receiver_contact_first_name CHECK (first_name != ""),
    last_name             VARCHAR(60) NOT NULL
        CONSTRAINT  receiver_contact_last_name CHECK (last_name != ""),
    middle_name           VARCHAR(60) NOT NULL
        CONSTRAINT  receiver_contact_middle_name CHECK (middle_name != ""),
    email                 VARCHAR(100) NOT NULL
        CONSTRAINT  receiver_contact_email CHECK (email != ""),
    telephone_number      VARCHAR(25) NOT NULL
        CONSTRAINT  receiver_contact_telephone_number CHECK (telephone_number != ""),
    id                    BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE returned_funds
(
    description           VARCHAR(300) NOT NULL,
    returned_amount       DECIMAL(30,8) NOT NULL
        CONSTRAINT  returned_funds_returned_amount CHECK (returned_amount >= 0),
    id                    BIGINT NOT NULL,
    returned_funds_status_id  BIGINT NOT NULL,
    returned_funds_method_id  BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE returned_funds_method
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    name                  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE returned_funds_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    status                VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE `role`
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE shop_delivery
(
    address               VARCHAR(100) NOT NULL,
    id                    BIGINT NOT NULL,
    shop_delivery_status  BIGINT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE shop_delivery_status
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    delivery_status       VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE `user`
(
    username              VARCHAR(100) NOT NULL
        CONSTRAINT  user_username CHECK (username != ""),
    password              VARCHAR(128) NOT NULL,
    first_name            VARCHAR(60) NOT NULL
        CONSTRAINT  user_first_name CHECK (first_name != ""),
    last_name             VARCHAR(60) NOT NULL
        CONSTRAINT  user_last_name CHECK (last_name != ""),
    middle_name           VARCHAR(60) NOT NULL
        CONSTRAINT  user_middle_name CHECK (middle_name != ""),
    telephone_number      VARCHAR(25) NOT NULL
        CONSTRAINT  user_telephone_number CHECK (telephone_number != ""),
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    role_id               BIGINT NOT NULL,
    invalid               boolean NOT NULL DEFAULT 0,
    blocked               boolean NOT NULL DEFAULT 0,
    created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unique_username ON `user`
    (
     username
        );


ALTER TABLE author_book
    ADD FOREIGN KEY One_book_can_be_writtenby_many_authors (book_id) REFERENCES book(id);

ALTER TABLE author_book
    ADD FOREIGN KEY One_author_can_writemany_books (author_id) REFERENCES author(id);


ALTER TABLE book
    ADD FOREIGN KEY R_149 (publisher_id) REFERENCES publisher(id);


ALTER TABLE book_file
    ADD FOREIGN KEY R_161 (file_id) REFERENCES `file`(id);

ALTER TABLE book_file
    ADD FOREIGN KEY R_188 (book_id) REFERENCES book(id)
        ON DELETE CASCADE;


ALTER TABLE book_genre
    ADD FOREIGN KEY One_book_can_havemany_genres (book_id) REFERENCES book(id);

ALTER TABLE book_genre
    ADD FOREIGN KEY One_genre_can_bein_many_books (genre_id) REFERENCES genre(id);


ALTER TABLE book_review
    ADD FOREIGN KEY R_136 (book_id) REFERENCES book(id);

ALTER TABLE book_review
    ADD FOREIGN KEY R_215 (request_for_publication_id) REFERENCES book_review_request_for_publication(id);

ALTER TABLE book_review
    ADD FOREIGN KEY R_327 (client_id) REFERENCES `client`(id);


ALTER TABLE book_review_comment
    ADD FOREIGN KEY R_329 (book_review_id) REFERENCES book_review(id)
        ON DELETE CASCADE;

ALTER TABLE book_review_comment
    ADD FOREIGN KEY R_330 (client_id) REFERENCES `client`(id)
        ON DELETE CASCADE;


ALTER TABLE book_review_draft
    ADD FOREIGN KEY R_201 (client_id) REFERENCES `client`(id)
        ON DELETE CASCADE;

ALTER TABLE book_review_draft
    ADD FOREIGN KEY R_204 (book_id) REFERENCES book(id);


ALTER TABLE book_review_edited
    ADD FOREIGN KEY R_205 (check_request_id) REFERENCES book_review_request_for_checking(id)
        ON DELETE CASCADE;


ALTER TABLE book_review_final
    ADD FOREIGN KEY R_200 (id) REFERENCES book_review_request_for_publication(id)
        ON DELETE CASCADE;


ALTER TABLE book_review_request_for_checking
    ADD FOREIGN KEY R_20 (book_review_request_for_checking_status_id) REFERENCES book_review_request_for_checking_status(id);

ALTER TABLE book_review_request_for_checking
    ADD FOREIGN KEY R_184 (editor_id) REFERENCES `user`(id);

ALTER TABLE book_review_request_for_checking
    ADD FOREIGN KEY R_207 (book_review_draft_id) REFERENCES book_review_draft(id);


ALTER TABLE book_review_request_for_checking_review_requirement
    ADD FOREIGN KEY Rewiew_request_for_checking_contains_several_requirements (review_request_for_checking_id) REFERENCES book_review_request_for_checking(id)
        ON DELETE CASCADE;

ALTER TABLE book_review_request_for_checking_review_requirement
    ADD FOREIGN KEY Review_requirement_is_used_by_several_requests_for_checking (requirement_id) REFERENCES book_review_requirement(id);


ALTER TABLE book_review_request_for_publication
    ADD FOREIGN KEY R_324 (editor_id) REFERENCES `user`(id);

ALTER TABLE book_review_request_for_publication
    ADD FOREIGN KEY R_325 (admin_id) REFERENCES `user`(id);

ALTER TABLE book_review_request_for_publication
    ADD FOREIGN KEY R_333 (request_for_publication_status_id) REFERENCES book_review_request_for_publication_status(id);

ALTER TABLE book_review_request_for_publication
    ADD FOREIGN KEY R_208 (check_request_id) REFERENCES book_review_request_for_checking(id);


ALTER TABLE book_review_request_for_publication_cancel
    ADD FOREIGN KEY R_359 (book_review_request_for_publication_id) REFERENCES book_review_request_for_publication(id)
        ON DELETE CASCADE;


ALTER TABLE book_review_request_for_publication_characteristic
    ADD FOREIGN KEY Request_for_publication_cancontain_many_charachteristics (request_for_publication_id) REFERENCES book_review_request_for_publication(id)
        ON DELETE CASCADE;

ALTER TABLE book_review_request_for_publication_characteristic
    ADD FOREIGN KEY Characteristic_can_definemany_publication_requests (review_characteristic_id) REFERENCES book_review_characteristic(id);


ALTER TABLE book_review_request_for_publication_criterion_score
    ADD FOREIGN KEY Request_for_publication_can_be_evaluated_by_many_criteria (request_for_publication_id) REFERENCES book_review_request_for_publication(id)
        ON DELETE CASCADE;

ALTER TABLE book_review_request_for_publication_criterion_score
    ADD FOREIGN KEY Criteria_can_evaluate_manyrequests_for_publication (review_score_criterion_id) REFERENCES book_review_score_criterion(id);


ALTER TABLE book_review_tag
    ADD FOREIGN KEY R_331 (book_review_tag_category_id) REFERENCES book_review_tag_category(id);


ALTER TABLE book_review_with_tag
    ADD FOREIGN KEY One_book_review_can_have_many_special_notes (book_review_id) REFERENCES book_review(id)
        ON DELETE CASCADE;

ALTER TABLE book_review_with_tag
    ADD FOREIGN KEY Several_special_notes_can_define_one_book_review (book_review_tag_id) REFERENCES book_review_tag(id);


ALTER TABLE buyer_contact
    ADD FOREIGN KEY R_223 (id) REFERENCES `order`(id)
        ON DELETE CASCADE;


ALTER TABLE card_payment
    ADD FOREIGN KEY (id) REFERENCES payment_info(id)
        ON DELETE CASCADE;


ALTER TABLE card_returned_funds_payment
    ADD FOREIGN KEY (id) REFERENCES returned_funds(id)
        ON DELETE CASCADE;


ALTER TABLE cart
    ADD FOREIGN KEY R_10 (id) REFERENCES `client`(id)
        ON DELETE CASCADE;


ALTER TABLE cart_item
    ADD FOREIGN KEY Cart_has_many_books (cart_id) REFERENCES cart(id)
        ON DELETE CASCADE;

ALTER TABLE cart_item
    ADD FOREIGN KEY R_186 (book_id) REFERENCES book(id);


ALTER TABLE cash_on_delivery_payment
    ADD FOREIGN KEY (id) REFERENCES payment_info(id)
        ON DELETE CASCADE;


ALTER TABLE cash_payment
    ADD FOREIGN KEY (id) REFERENCES payment_info(id)
        ON DELETE CASCADE;


ALTER TABLE cash_returned_funds_payment
    ADD FOREIGN KEY (id) REFERENCES returned_funds(id)
        ON DELETE CASCADE;


ALTER TABLE `client`
    ADD FOREIGN KEY (id) REFERENCES `user`(id);


ALTER TABLE client_note_for_wishes
    ADD FOREIGN KEY R_171 (id_request) REFERENCES delivery_request(id)
        ON DELETE CASCADE;

ALTER TABLE client_note_for_wishes
    ADD FOREIGN KEY R_265 (manager_id) REFERENCES `user`(id);


ALTER TABLE courier_delivery
    ADD FOREIGN KEY (id) REFERENCES delivery_info(id)
        ON DELETE CASCADE;


ALTER TABLE delivery
    ADD FOREIGN KEY R_259 (id) REFERENCES delivery_request(id)
        ON DELETE CASCADE;

ALTER TABLE delivery
    ADD FOREIGN KEY R_260 (delivery_status_id) REFERENCES delivery_status(id);

ALTER TABLE delivery
    ADD FOREIGN KEY R_264 (courier_id) REFERENCES `user`(id);


ALTER TABLE delivery_info
    ADD FOREIGN KEY R_17 (id) REFERENCES `order`(id)
        ON DELETE CASCADE;

ALTER TABLE delivery_info
    ADD FOREIGN KEY R_20 (delivery_method_id) REFERENCES delivery_method(id);


ALTER TABLE delivery_item
    ADD FOREIGN KEY R_267 (book_id) REFERENCES book(id);

ALTER TABLE delivery_item
    ADD FOREIGN KEY R_268 (delivery_id) REFERENCES delivery(id)
        ON DELETE CASCADE;


ALTER TABLE delivery_request
    ADD FOREIGN KEY R_172 (delivery_request_status_id) REFERENCES delivery_request_status(id);

ALTER TABLE delivery_request
    ADD FOREIGN KEY R_263 (manager_id) REFERENCES `user`(id);

ALTER TABLE delivery_request
    ADD FOREIGN KEY R_266 (order_id) REFERENCES `order`(id)
        ON DELETE CASCADE;

ALTER TABLE delivery_request
    ADD FOREIGN KEY R_269 (courier_id) REFERENCES `user`(id);


ALTER TABLE deliveryman_contact
    ADD FOREIGN KEY R_270 (id) REFERENCES delivery(id)
        ON DELETE CASCADE;


ALTER TABLE edit_review_permission
    ADD FOREIGN KEY R_356 (review_draft_id) REFERENCES book_review_draft(id)
        ON DELETE CASCADE;


ALTER TABLE nova_poshta_delivery
    ADD FOREIGN KEY (id) REFERENCES delivery_info(id)
        ON DELETE CASCADE;

ALTER TABLE nova_poshta_delivery
    ADD FOREIGN KEY R_275 (nova_poshta_delivery_status) REFERENCES nova_poshta_delivery_status(id);


ALTER TABLE `order`
    ADD FOREIGN KEY R_75 (client_id) REFERENCES `client`(id);

ALTER TABLE `order`
    ADD FOREIGN KEY R_7 (order_status_id) REFERENCES order_status(id);

ALTER TABLE `order`
    ADD FOREIGN KEY R_179 (manager_id) REFERENCES `user`(id);


ALTER TABLE order_item
    ADD FOREIGN KEY One_order_can_havemany_books (order_id) REFERENCES `order`(id)
        ON DELETE CASCADE;

ALTER TABLE order_item
    ADD FOREIGN KEY R_187 (book_id) REFERENCES book(id);


ALTER TABLE payment_info
    ADD FOREIGN KEY R_16 (id) REFERENCES `order`(id)
        ON DELETE CASCADE;

ALTER TABLE payment_info
    ADD FOREIGN KEY R_48 (payment_status_id) REFERENCES payment_status(id);

ALTER TABLE payment_info
    ADD FOREIGN KEY R_47 (payment_method_id) REFERENCES payment_method(id);


ALTER TABLE receiver_contact
    ADD FOREIGN KEY R_224 (id) REFERENCES `order`(id)
        ON DELETE CASCADE;


ALTER TABLE returned_funds
    ADD FOREIGN KEY R_243 (id) REFERENCES payment_info(id)
        ON DELETE CASCADE;

ALTER TABLE returned_funds
    ADD FOREIGN KEY R_245 (returned_funds_status_id) REFERENCES returned_funds_status(id);

ALTER TABLE returned_funds
    ADD FOREIGN KEY R_246 (returned_funds_method_id) REFERENCES returned_funds_method(id);


ALTER TABLE shop_delivery
    ADD FOREIGN KEY (id) REFERENCES delivery_info(id)
        ON DELETE CASCADE;

ALTER TABLE shop_delivery
    ADD FOREIGN KEY R_276 (shop_delivery_status) REFERENCES shop_delivery_status(id);


ALTER TABLE `user`
    ADD FOREIGN KEY R_178 (role_id) REFERENCES role(id);

