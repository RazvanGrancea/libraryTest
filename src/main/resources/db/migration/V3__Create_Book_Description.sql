ALTER TABLE books ADD COLUMN description VARCHAR(500);
SET @i = 0;
UPDATE books
SET description = CONCAT ('Text dumy ', (@i := @i + 1));
