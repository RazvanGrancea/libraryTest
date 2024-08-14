CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id BIGINT,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
        REFERENCES authors(id)
        ON DELETE SET NULL
);
