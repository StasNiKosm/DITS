CREATE TABLE topic ( topicid SERIAL PRIMARY KEY, description VARCHAR(100), name VARCHAR(20) );
CREATE TABLE test ( testid SERIAL PRIMARY KEY, name VARCHAR(50), description VARCHAR(100), topicid INTEGER REFERENCES topic );
CREATE TABLE question ( questionid SERIAL PRIMARY KEY, description VARCHAR(255  ), testid INTEGER REFERENCES test );
CREATE TABLE literature ( literatureid SERIAL PRIMARY KEY, description VARCHAR(100), questionid INTEGER REFERENCES question );
CREATE TABLE tuser( userid SERIAL PRIMARY KEY, firstName VARCHAR(30), lastName VARCHAR(30), login VARCHAR(30), password VARCHAR(255), role VARCHAR(255));
CREATE TABLE link( linkid SERIAL PRIMARY KEY, link VARCHAR(255), literatureid INTEGER REFERENCES literature);
CREATE TABLE answer( answerid SERIAL PRIMARY KEY, description VARCHAR(255), correct INTEGER, questionid INTEGER REFERENCES question);
CREATE TABLE statistic( statisticid SERIAL PRIMARY KEY, date DATE, correct INTEGER, questionid INTEGER REFERENCES question, userid INTEGER REFERENCES tuser);