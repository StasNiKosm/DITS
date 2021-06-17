INSERT INTO topic (topicid, description, name) values (1, 'desc_topic_1' , 'name_topic_1'), (2, 'desc_topic_2' , 'name_topic_2'), (3, 'desc_topic_3' , 'name_topic_3'), (4, 'desc_topic_4' , 'name_topic_4'), (5, 'desc_topic_5' , 'name_topic_5');
INSERT INTO test (testid, description, name, topicid) values (1, 'desc_test_1' , 'name_test_1', 2), (2, 'desc_test_2' , 'name_test_2', 1), (3, 'desc_test_3' , 'name_test_3', 2), (4, 'desc_test_4' , 'name_test_4', 4), (5, 'desc_test_5' , 'name_test_5', 3);
INSERT INTO question (questionid, description, testid) values (1, 'desc_question_1' , 2), (2, 'desc_question_2' , 2), (3, 'desc_question_3' , 1), (4, 'desc_question_4' , 1), (5, 'desc_question_5' , 3), (6, 'desc_question_6' , 5);
INSERT INTO literature (literatureid, description, questionid) values (1, 'desc_literature_1' , 1), (2, 'desc_literature_2' , 3), (3, 'desc_literature_3' , 2), (4, 'desc_literature_4' , 4), (5, 'desc_literature_5' , 5);
INSERT INTO tuser (userid, firstName, lastName, login, password, role) values (1, 'Vlad', 'Polhovsky', 'vlad', '1234', 'ROLE_ADMIN');