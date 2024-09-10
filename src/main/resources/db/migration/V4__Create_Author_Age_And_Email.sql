ALTER TABLE authors
ADD COLUMN age INT,
ADD COLUMN email VARCHAR(255);

UPDATE authors
SET age = FLOOR(RAND()*80) + 20;

UPDATE authors
SET email = CONCAT(
            LOWER(name), '@gmail.com'
            );