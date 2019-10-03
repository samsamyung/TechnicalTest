use propellerhead;

INSERT INTO customer
(name, status, creationDateTime, phone, address1, address2, address3, email)
VALUES('Sam', 0, 123, '12341234', 'aaa', 'bbb', 'ccc', 'sam@abc.com');

INSERT INTO customer
(name, status, creationDateTime, phone, address1, address2, address3, email)
VALUES('David', 0, 123, '45674567', 'address1', 'address2', 'address3', 'david@aaa.com');


INSERT INTO note
(customerId, content, creationDateTime)
VALUES(1, 'This note is for Sam', 123);

INSERT INTO note
(customerId, content, creationDateTime)
VALUES(2, 'This note is for David', 123);
