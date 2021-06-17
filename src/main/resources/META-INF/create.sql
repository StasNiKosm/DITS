CREATE TABLE topic ( topicid SERIAL PRIMARY KEY, description VARCHAR(100), name VARCHAR(20) );
CREATE TABLE test ( testid SERIAL PRIMARY KEY, name VARCHAR(20), description VARCHAR(100), topicid INTEGER REFERENCES topic );
CREATE TABLE question ( questionid SERIAL PRIMARY KEY, description VARCHAR(100), testid INTEGER REFERENCES test );
CREATE TABLE literature ( literatureid SERIAL PRIMARY KEY, description VARCHAR(100), questionid INTEGER REFERENCES question );
CREATE TABLE tuser ( userid SERIAL PRIMARY KEY, firstName VARCHAR(30), lastName VARCHAR(30), login VARCHAR(30), password VARCHAR(255), role VARCHAR(255));