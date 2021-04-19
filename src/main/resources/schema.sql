USE ocean_blu;

CREATE TABLE IF NOT EXISTS ocean_blu.user
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(12) NOT NULL,
    `password`   VARCHAR(99) NOT NULL,
    `active`     BOOLEAN     NOT NULL,
    `first_name` VARCHAR(12) NOT NULL,
    `last_name`  VARCHAR(12) NOT NULL,
    `role`       VARCHAR(15) NOT NULL,
    `gender`     VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ocean_blu.transaction
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `product_id`    INT         NOT NULL,
    `user_id`       INT         NOT NULL,
    `rating_id`     INT,
    `date`          DATETIME    NOT NULL,
    `code`          VARCHAR(15) NOT NULL,
    `items`         INT         NOT NULL,
    `current_items` INT         NOT NULL,
    `current_value` FLOAT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ocean_blu.product
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(13) NOT NULL,
    `company`     VARCHAR(10) NOT NULL,
    `price`       FLOAT       NOT NULL,
    `quantity`    INT         NOT NULL,
    `image`       BLOB        NOT NULL,
    `description` TEXT        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ocean_blu.rating
(
    `id`           INT     NOT NULL AUTO_INCREMENT,
    `rate`         INT     NOT NULL,
    `review_left`  BOOLEAN NOT NULL,
    `review_title` VARCHAR(15),
    `review_body`  TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ocean_blu.wishlist
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `user_id`    INT NOT NULL,
    `product_id` INT NOT NULL,
    PRIMARY KEY (id)
);