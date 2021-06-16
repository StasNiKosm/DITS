package scriptDB;

import dao.DataBaseConnectionFactory;

import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    public static void main(String[] args) throws SQLException {
        init();
    }

    public static void init() throws SQLException {
        Statement statement = DataBaseConnectionFactory.getConnection().createStatement();
        dropTables(statement);
        creatTableTopic(statement);
        creatTableTest(statement);
        creatTableQuestion(statement);
        creatTableAnswer(statement);
        creatTableRole(statement);
        creatTableTuser(statement);
        creatTableStatistic(statement);
        creatTableLiterature(statement);
        creatTableLink(statement);
        statement.close();
    }


    private static void creatTableTopic(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE topic (" +
                        "topicid INTEGER PRIMARY KEY NOT NULL," +
                        "description VARCHAR(100)," +
                        "name VARCHAR(20)" +
                        ")");
    }

    private static void creatTableTest(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE test (" +
                        "testid INTEGER PRIMARY KEY NOT NULL," +
                        "name VARCHAR(20)," +
                        "description VARCHAR(100)," +
                        "topicid INTEGER REFERENCES topic ON DELETE CASCADE" +
                        ")");
    }

    private static void creatTableQuestion(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE question (" +
                "questionid INTEGER PRIMARY KEY NOT NULL," +
                "description VARCHAR(100)," +
                "testid INTEGER REFERENCES test ON DELETE CASCADE" +
                ")");
    }

    private static void creatTableAnswer(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE answer (" +
                "answerid INTEGER PRIMARY KEY NOT NULL," +
                "description VARCHAR(100)," +
                "correct INTEGER," +
                "questionid INTEGER REFERENCES question ON DELETE CASCADE" +
                ")");
    }

    private static void creatTableRole(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE role (" +
                "roleid INTEGER PRIMARY KEY NOT NULL," +
                "tuser INTEGER," +
                "admin INTEGER" +
                ")");
    }

    private static void creatTableTuser(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE tuser (" +
                "tuserid INTEGER PRIMARY KEY NOT NULL," +
                "firstname VARCHAR(20)," +
                "lastname VARCHAR(20)," +
                "login VARCHAR(20)," +
                "password INTEGER," +
                "roleid INTEGER REFERENCES role ON DELETE CASCADE" +
                ")");
    }

    private static void creatTableStatistic(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE statistic (" +
                "statisticid INTEGER PRIMARY KEY NOT NULL," +
                "date DATE," +
                "correct INTEGER," +
                "tuserid INTEGER REFERENCES tuser ON DELETE CASCADE," +
                "questionid INTEGER REFERENCES question ON DELETE CASCADE" +
                ")");
    }

    private static void creatTableLiterature(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE literature (" +
                "literatureid INTEGER PRIMARY KEY NOT NULL," +
                "description VARCHAR(100)," +
                "questionid INTEGER REFERENCES question ON DELETE CASCADE" +
                ")");
    }

    private static void creatTableLink(Statement statement) throws SQLException {
         statement.execute("CREATE TABLE link (" +
                "linkid INTEGER PRIMARY KEY NOT NULL," +
                "link VARCHAR(100)," +
                "literatureid INTEGER REFERENCES literature ON DELETE CASCADE" +
                ")");
    }

    private static void dropTables(Statement statement) throws SQLException{
        statement.execute("DROP TABLE IF EXISTS topic, test, question, answer, role, tuser, statistic, literature, link; ");
    }

}
