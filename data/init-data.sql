-- INVESTMENT_RATE_STATUS
INSERT INTO investment_rate_status(description, updated_date) VALUES ('VIGENTE', NOW());
INSERT INTO investment_rate_status(description, updated_date) VALUES ('DESATUALIZADA', NOW());

-- CUSTOMER_GENDER
INSERT INTO customer_gender(description) VALUES ('MASCULINO');
INSERT INTO customer_gender(description) VALUES ('FEMININO');