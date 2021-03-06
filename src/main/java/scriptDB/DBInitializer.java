package scriptDB;

import providers.AppContextProvider;
import repository.connection.DataBaseConnectionFactory;
import repository.dao.entities.*;
import repository.managers.lazy.LazyManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DBInitializer {

    public static void main(String[] args) throws SQLException {
            //init();

    }

    public static void createNewTest() {

        Topic topic = new Topic();

        topic.setName("Операционные системы");

        Set<Question> questions = new HashSet<>();

        {
            Test test_1 = new Test();
            test_1.setTopic(topic);
            topic.setTests(Collections.singleton(test_1));

            test_1.setName("Операционные системы - общая теория");
            test_1.setDescription("Тест направлен на закрепление знаний по теме Операционные системы.");
            test_1.setQuestions(questions);

            {
                Question firstQuestion = new Question();
                questions.add(firstQuestion);

                firstQuestion.setTest(test_1);
                firstQuestion.setStatistic(Collections.emptySet());
                firstQuestion.setDescription("Если операционная система может работать только на компьютере с одним процессором, то такая операционная система называется:");

                Literature literature_1 = new Literature();
                literature_1.setQuestion(firstQuestion);
                literature_1.setDescription("Статья \"Операционная система\"");

                Link link = new Link();
                link.setLink("https://ru.wikipedia.org/wiki/%D0%9E%D0%BF%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D0%B0%D1%8F_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0");
                link.setLiterature(literature_1);

                literature_1.setLinks(Collections.singleton(link));
                firstQuestion.setLiterature(Collections.singleton(literature_1));

                {
                    Answer answer_1 = new Answer();
                    answer_1.setDescription("Реального времени");
                    answer_1.setCorrect(0);
                    answer_1.setQuestion(firstQuestion);

                    Answer answer_2 = new Answer();
                    answer_2.setDescription("Однооконной");
                    answer_2.setCorrect(0);
                    answer_2.setQuestion(firstQuestion);

                    Answer answer_3 = new Answer();
                    answer_3.setDescription("Однопроцессорной");
                    answer_3.setCorrect(0);
                    answer_3.setQuestion(firstQuestion);

                    Answer answer_4 = new Answer();
                    answer_4.setDescription("Однопроцессорной");
                    answer_4.setCorrect(0);
                    answer_4.setQuestion(firstQuestion);

                    Answer answer_5 = new Answer();
                    answer_5.setDescription("Однопроцессорной");
                    answer_5.setCorrect(1);
                    answer_5.setQuestion(firstQuestion);

                    firstQuestion.setAnswers(new HashSet<>(Arrays.asList(answer_1, answer_2, answer_3, answer_4, answer_5)));
                }
            }

            {
                Question firstQuestion = new Question();
                questions.add(firstQuestion);

                firstQuestion.setTest(test_1);
                firstQuestion.setStatistic(Collections.emptySet());
                firstQuestion.setDescription("Физические устройства, из которых состоит компьютер, называются:");

                Literature literature_1 = new Literature();
                literature_1.setQuestion(firstQuestion);
                literature_1.setDescription("Статья \"Персональный компьютер\"");

                Link link = new Link();
                link.setLink("https://ru.wikipedia.org/wiki/%D0%9F%D0%B5%D1%80%D1%81%D0%BE%D0%BD%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80");
                link.setLiterature(literature_1);

                literature_1.setLinks(Collections.singleton(link));
                firstQuestion.setLiterature(Collections.singleton(literature_1));

                {
                    Answer answer_1 = new Answer();
                    answer_1.setDescription("Потребляемыми ресурсами");
                    answer_1.setCorrect(0);
                    answer_1.setQuestion(firstQuestion);

                    Answer answer_2 = new Answer();
                    answer_2.setDescription("Логическими ресурсами");
                    answer_2.setCorrect(0);
                    answer_2.setQuestion(firstQuestion);

                    Answer answer_3 = new Answer();
                    answer_3.setDescription("Монопольными ресурсами");
                    answer_3.setCorrect(0);
                    answer_3.setQuestion(firstQuestion);

                    Answer answer_4 = new Answer();
                    answer_4.setDescription("Разделяемыми ресурсами.");
                    answer_4.setCorrect(0);
                    answer_4.setQuestion(firstQuestion);

                    Answer answer_5 = new Answer();
                    answer_5.setDescription("Аппаратными или физическими ресурсами");
                    answer_5.setCorrect(1);
                    answer_5.setQuestion(firstQuestion);

                    Answer answer_6 = new Answer();
                    answer_6.setDescription("Информационными или логическими ресурсами");
                    answer_6.setCorrect(0);
                    answer_6.setQuestion(firstQuestion);

                    firstQuestion.setAnswers(new HashSet<>(Arrays.asList(answer_1, answer_2, answer_3, answer_4, answer_5, answer_6)));
                }
            }

            {
                Question firstQuestion = new Question();
                questions.add(firstQuestion);

                firstQuestion.setTest(test_1);
                firstQuestion.setStatistic(Collections.emptySet());
                firstQuestion.setDescription("Программы и данные, которые хранятся в памяти компьютера, называются:");

                Literature literature_1 = new Literature();
                literature_1.setQuestion(firstQuestion);
                literature_1.setDescription("Статья \"Администрирование данных\"");

                Link link1 = new Link();
                link1.setLink("https://ru.wikipedia.org/wiki/%D0%90%D0%B4%D0%BC%D0%B8%D0%BD%D0%B8%D1%81%D1%82%D1%80%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5_%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85");
                link1.setLiterature(literature_1);

                Link link2 = new Link();
                link2.setLink("https://vk.cc/c3orks");
                link2.setLiterature(literature_1);

                literature_1.setLinks(new HashSet<>(Arrays.asList(link1, link2)));
                firstQuestion.setLiterature(Collections.singleton(literature_1));

                {
                    Answer answer_1 = new Answer();
                    answer_1.setDescription("Хранение данных и программ");
                    answer_1.setCorrect(0);
                    answer_1.setQuestion(firstQuestion);

                    Answer answer_2 = new Answer();
                    answer_2.setDescription("Безопасность работы с компьютером");
                    answer_2.setCorrect(0);
                    answer_2.setQuestion(firstQuestion);

                    Answer answer_3 = new Answer();
                    answer_3.setDescription("Графический интерфейс для пользователя");
                    answer_3.setCorrect(0);
                    answer_3.setQuestion(firstQuestion);

                    Answer answer_4 = new Answer();
                    answer_4.setDescription("Надежность работы компьютера");
                    answer_4.setCorrect(0);
                    answer_4.setQuestion(firstQuestion);

                    Answer answer_5 = new Answer();
                    answer_5.setDescription("Информационными или логическими ресурсами");
                    answer_5.setCorrect(1);
                    answer_5.setQuestion(firstQuestion);

                    firstQuestion.setAnswers(new HashSet<>(Arrays.asList(answer_1, answer_2, answer_3, answer_4, answer_5)));
                }
            }

            {
                Question firstQuestion = new Question();
                questions.add(firstQuestion);

                firstQuestion.setTest(test_1);
                firstQuestion.setStatistic(Collections.emptySet());
                firstQuestion.setDescription("Программа, которая работает под управлением операционной системы, называется:");

                Literature literature_1 = new Literature();
                literature_1.setQuestion(firstQuestion);
                literature_1.setDescription("Статья \"Прикладное программное обеспечение\"");

                Link link = new Link();
                link.setLink("https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D0%B8%D0%BA%D0%BB%D0%B0%D0%B4%D0%BD%D0%BE%D0%B5_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%BD%D0%BE%D0%B5_%D0%BE%D0%B1%D0%B5%D1%81%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D0%B8%D0%B5");
                link.setLiterature(literature_1);

                literature_1.setLinks(Collections.singleton(link));
                firstQuestion.setLiterature(Collections.singleton(literature_1));

                {
                    Answer answer_1 = new Answer();
                    answer_1.setDescription("Исполняемым файлом");
                    answer_1.setCorrect(0);
                    answer_1.setQuestion(firstQuestion);

                    Answer answer_2 = new Answer();
                    answer_2.setDescription("Объектным файлом");
                    answer_2.setCorrect(0);
                    answer_2.setQuestion(firstQuestion);

                    Answer answer_3 = new Answer();
                    answer_3.setDescription("Загрузочным файлом");
                    answer_3.setCorrect(0);
                    answer_3.setQuestion(firstQuestion);

                    Answer answer_4 = new Answer();
                    answer_4.setDescription("Потоком");
                    answer_4.setCorrect(0);
                    answer_4.setQuestion(firstQuestion);

                    Answer answer_5 = new Answer();
                    answer_5.setDescription("Пользовательской программой");
                    answer_5.setCorrect(1);
                    answer_5.setQuestion(firstQuestion);

                    firstQuestion.setAnswers(new HashSet<>(Arrays.asList(answer_1, answer_2, answer_3, answer_4, answer_5)));
                }
            }
        }

        LazyManager<Topic> topicService = (LazyManager<Topic>) AppContextProvider.getAppContext().getBean("topicLazyManager");
        topicService.create(topic);

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



