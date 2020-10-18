import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    private static final String PATH_FILE_PARSE = "forParse/mongo.csv";
    private static MongoCollection<Document> collection;

    public static void main(String[] args) {
        try {
            List<Student> students = StudentsList.parseFile(PATH_FILE_PARSE);
            createMongoDB();
            students.forEach(Main::addMongoDB);

            System.out.println("Всего студентов: " + collection.countDocuments());
            BsonDocument query0 = BsonDocument.parse("{age: {$gt: 40}}");
            System.out.println("Студентов старше 40: " + collection.countDocuments(query0));
            BsonDocument query1 = BsonDocument.parse("{age: 1}");
            collection.find().sort(query1).limit(1).forEach((Consumer<Document>) item ->
                    System.out.println("Самого молодого студента зовут: " + item.get("name")));
            BsonDocument query2 = BsonDocument.parse("{age: -1}");
            collection.find().sort(query2).limit(1).forEach(((Consumer<Document>) item ->
                    System.out.println("Список курсов и имя самого старого студента: " + item.get("courses") + " "
                            + item.get("name"))));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void createMongoDB() {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        collection = database.getCollection("Students");

        // Удалим из нее все документы
        collection.drop();
    }

    private static void addMongoDB(Student student) {
        List<String> courses_name = new ArrayList<>();
        student.getCourses().forEach(course -> courses_name.add(course.getName()));

        // Создадим первый документ
        Document firstDocument = new Document()
                .append("name", student.getName())
                .append("age", student.getAge())
                .append("courses", courses_name);

        // Вставляем документ в коллекцию
        collection.insertOne(firstDocument);
    }
}
