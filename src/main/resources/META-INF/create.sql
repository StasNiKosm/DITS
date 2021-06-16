CREATE TABLE topic ( topicid SERIAL PRIMARY KEY, description VARCHAR(100), name VARCHAR(20) );
CREATE TABLE test ( testid SERIAL PRIMARY KEY, name VARCHAR(20), description VARCHAR(100), topicid INTEGER REFERENCES topic );
CREATE TABLE question ( questionid SERIAL PRIMARY KEY, description VARCHAR(100), testid INTEGER REFERENCES test );
CREATE TABLE literature ( literatureid SERIAL PRIMARY KEY, description VARCHAR(100), questionid INTEGER REFERENCES question );