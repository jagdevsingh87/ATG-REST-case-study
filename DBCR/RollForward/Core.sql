-- DDL for customer review functionality

SET ECHO ON;
SET DEFINE OFF;

CREATE TABLE customer_reviews (
	customer_id 	VARCHAR(254)	NOT NULL,
	product_id 		VARCHAR(254)	NOT NULL,
	comment 		VARCHAR(254)	NOT NULL,
	PRIMARY KEY(customer_id, product_id)
);

CREATE TABLE user_reviews (
	id 			VARCHAR(254)	NOT NULL REFERENCES dps_user(id),
	review 		VARCHAR(254)	NOT NULL REFERENCES customer_reviews(customerId),
	PRIMARY KEY(id, review)
);

COMMIT;


