-- DDL Roll back for customer review functionality

SET ECHO ON;
SET DEFINE OFF;

drop table user_reviews;
drop table customer_reviews;

COMMIT;