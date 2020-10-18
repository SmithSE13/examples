import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.NotValidLineFromFileException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentsList {
    private static final int INDEX_ITEM_NAME = 0;
    private static final int INDEX_ITEM_AGE = 1;
    private static final int INDEX_ITEM_COURSES = 2;
    private static final int LENGTH_MASSIVE_ITEM_LINE = 3;

    public static List<Student> parseFile(String pathFileParse) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(pathFileParse));
        String[] line;
        List<Student> students = new ArrayList<>();
        while((line = reader.readNext()) != null) {
            try {
                if (line.length != LENGTH_MASSIVE_ITEM_LINE) {
                    throw new NotValidLineFromFileException();
                }
                Student student = new Student();
                student.setName(line[INDEX_ITEM_NAME]);
                student.setAge(Integer.parseInt(line[INDEX_ITEM_AGE]));
                student.setCourses(parseItemCourses(line[INDEX_ITEM_COURSES]));
                students.add(student);
            } catch (NotValidLineFromFileException ex) {
                ex.printStackTrace();
            }
        }
        reader.close();
        return students;
    }

    private static List<Course> parseItemCourses(String listCourses) {
        listCourses = listCourses.replaceAll("\"", "");
        String[] items = listCourses.split(",");
        List<Course> courses = new ArrayList<>();
        Arrays.stream(items).forEach(item -> courses.add(new Course(item)));
        return courses;
    }
}
