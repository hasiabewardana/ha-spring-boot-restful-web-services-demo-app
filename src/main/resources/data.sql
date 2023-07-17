insert into user_details(id, name, birth_date)
values (1000, 'John Doe', current_date());

insert into user_details(id, name, birth_date)
values (1001, 'David Watson', current_date());

insert into user_details(id, name, birth_date)
values (1002, 'Kane Williams', current_date());

insert into post(id, description, user_id)
values(1, 'I want to master Microservices.', 1000);

insert into post(id, description, user_id)
values(2, 'I want to learn AWS.', 1001);

insert into post(id, description, user_id)
values(3, 'I want to learn Java.', 1001);