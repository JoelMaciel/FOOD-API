ALTER TABLE restaurant ADD active TINYINT(1) NOT NULL;
UPDATE restaurant SET active = TRUE;
