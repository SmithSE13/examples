import com.opencsv.CSVReader;
import exceptions.NotValidLineFromFileException;
import org.apache.logging.log4j.*;

import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ParseTablesFiles {

    private static final Path FILE_TO_PARSE = Paths.get("FileToParse/movementList.csv");
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Marker WRONG_LINE = MarkerManager.getMarker("WRONG_LINE");

    private List<Movement> movementList;

    private static final int NUMBER_ITEM_DESCRIPTION_OPERATION = 5;
    private static final int NUMBER_ITEM_DATE_OPERATION = 3;
    private static final int NUMBER_ITEM_COMING = 6;
    private static final int NUMBER_ITEM_EXPENDITURE = 7;
    private static final int LENGTH_MASSIVE_ITEM_LINE = 8;

    public void parseFile() {
        try {
            CSVReader reader = new CSVReader(new FileReader(FILE_TO_PARSE.toString()), ',');
            movementList = new ArrayList<>();
            String[] line;
            reader.readNext();

            while((line = reader.readNext()) != null) {
                if (line.length != LENGTH_MASSIVE_ITEM_LINE) {
                    LOGGER.info(WRONG_LINE, "{}", line);
                    throw new NotValidLineFromFileException();
                }
                Movement movement = new Movement();
                movement.setDateOperation(line[NUMBER_ITEM_DATE_OPERATION]);
                movement.setNameOrganisationOperation(getNameOperation(line[NUMBER_ITEM_DESCRIPTION_OPERATION]));
                movement.setComing(changeCommaOnPoint(line[NUMBER_ITEM_COMING]));
                movement.setExpenditure(changeCommaOnPoint(line[NUMBER_ITEM_EXPENDITURE]));
                movementList.add(movement);
            }
            reader.close();
        } catch (Exception ex) {
            LOGGER.info(WRONG_LINE, "{}", ex.getStackTrace());
            ex.printStackTrace();
        }
    }

    private String changeCommaOnPoint(String line) {
        String string = line;
        string = string.replaceAll(",", ".");
        return string;
    }

    public BigDecimal getCommonComing() {
        return movementList.stream()
                .map(Movement::getComing)
                .reduce(BigDecimal::add)
                .get();
    }

    public BigDecimal getCommonExpenditure() {
        return movementList.stream()
                .map(Movement::getExpenditure)
                .reduce(BigDecimal::add)
                .get();
    }

    public void printListExpenditure() {
        Map<String, BigDecimal> expendituresOnOrganisations = new TreeMap<>();
        movementList.forEach(item ->
                    expendituresOnOrganisations.put(item.getNameOrganisationOperation(), item.getExpenditure()));

        for(Map.Entry<String, BigDecimal> entry : expendituresOnOrganisations.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            System.out.printf("%s : %s руб.\n", key, value);
        }
    }

    private String getNameOperation(String descriptionOperation) {
        int numberItemNameOperation = 1;
        descriptionOperation = descriptionOperation.replaceAll("\\\\", "/");
        String[] massiveItemLine = descriptionOperation.split("\\s{3,}");
        String[] massiveNameShop = massiveItemLine[numberItemNameOperation].split("/");
        return massiveNameShop[massiveNameShop.length - 1].trim();
    }
}

