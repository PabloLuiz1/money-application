-- INVESTMENT_RATE_STATUS
INSERT INTO investment_rate_status(description, updated_date) VALUES ('VIGENTE', NOW());
INSERT INTO investment_rate_status(description, updated_date) VALUES ('DESATUALIZADA', NOW());

-- CUSTOMER_GENDER
INSERT INTO customer_gender(description) VALUES ('MASCULINO');
INSERT INTO customer_gender(description) VALUES ('FEMININO');

-- CUSTOMER
INSERT INTO customer(birth_date, cpf, email, name, gender_id)
VALUES ('1999-08-31', '12345678900', 'pabloluiz@gmail.com', 'Pablo Luiz Ribeiro', 1);

--CUSTOMER_ACCOUNT
INSERT INTO public.customer_account(balance, customer_id)
VALUES (100.0, 1);

--INVESTMENT_RATE
INSERT INTO public.investment_rate(description, effective_date, percentage, updated_date, status_id)
VALUES ('CDI', '2022-05-19', 0.047, NOW(), 1);

