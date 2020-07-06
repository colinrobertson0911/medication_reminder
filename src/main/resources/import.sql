insert into users(userId, username, password, firstname, lastname, email) values (USER_SEQ.nextval, 'admin1', '$2a$12$ax483KXnei/I7jStkI.xwe/EMXubNNN6/fhqzmXIRe3nY8pfhbEfq', 'Administrator', 'Administrator', 'admin@email.com');
insert into users(userId, username, password, firstname, lastname, email) values (USER_SEQ.nextval, 'patient1', '$2a$12$ax483KXnei/I7jStkI.xwe/EMXubNNN6/fhqzmXIRe3nY8pfhbEfq', 'Peter', 'Smith', 'peter@email.com');

insert into patient(userId, weight, height, age) values (2, '80kg', '180cm', 40);

insert into role(roleId, roleName) values (ROLE_SEQ.nextval, 'ROLE_ADMIN');
insert into role(roleId, roleName) values (ROLE_SEQ.nextval, 'ROLE_PATIENT');

insert into user_role(userId, roleId) values (1,1);
insert into user_role(userId, roleId) values (2,2);

insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Paracetamol', '250mg', 'Sore Head', 7, 4, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 2, 6, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Amlodipine', '10mg', 'High Blood Pressure', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 1, 30, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Candesartan', '8mg', 'High Blood Pressure', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 1, 30, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Cholecalciferol', '800units', 'Calcium D Deficiency', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 2, 30, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Mirtazapine', '30mg', 'Depression', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 2, 30, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Trientine', '300mg', 'Wilsons Disease', 7, 2, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 4, 100, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'iBuprofen', '200mg', 'Inflammation ', 7, 2, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 4, 32, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Meloxicam', '30mg', 'Inflammation', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 1, 30, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Levothyroxine', '50mg', 'Hyper Thyroid ', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 1, 56, 0);
insert into MEDICATION (MEDICATIONID, NAME, DOSAGE, CONDITION, TIMESAWEEK, TIMESADAY, TIMETOTAKE, QUANTITY, PILLSLEFT, REFILL) values (MEDICATION_SEQ.NEXTVAL, 'Levothyroxine', '100mg', 'Hyper Thyroid', 7, 1, parsedatetime('17 Mar 2012 09:00', 'dd MMM yyyy hh:mm'), 1, 56, 0);

insert into patient_medication(userId, medicationId) values (2,1);
insert into patient_medication(userId, medicationId) values (2,3);
insert into patient_medication(userId, medicationId) values (2,5);
insert into patient_medication(userId, medicationId) values (2,7);
insert into patient_medication(userId, medicationId) values (2,9);
insert into patient_medication(userId, medicationId) values (2,10);

