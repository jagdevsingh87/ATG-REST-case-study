-- DDL for customer review functionality

SET ECHO ON;
SET DEFINE OFF;

CREATE TABLE product_reviews (
	product_id 		VARCHAR(254)	NOT NULL REFERENCES dcs_product(product_id),
	review 			VARCHAR(254)	NOT NULL REFERENCES customer_reviews(customerId),
	PRIMARY KEY(product_id, review)
);

COMMIT;