import org.apache.logging.log4j.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParserHTML {
    private URL url;
    private URLConnection connection;
    private final String ADDRESS_SITE;
    private List<String> listLinks = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Marker EXCEPTION = MarkerManager.getMarker("EXCEPTION");

    public ParserHTML(String siteAddress) {
        ADDRESS_SITE = siteAddress;
    }

    public List<String> parseFile(Path folderForSave, String cssQuery,
                                  String attributeKey, boolean changeNameFile,
                                  String attributeKeyForName, String regexExpressionForChange)
    {
        try {
            Files.createDirectories(folderForSave);

            getResources(getElements(cssQuery), attributeKey,
                    folderForSave, changeNameFile,
                    attributeKeyForName, regexExpressionForChange);

            Files.write(folderForSave.resolve(ADDRESS_SITE.replaceAll("\\p{Punct}", "_") + ".txt"), listLinks);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.info(EXCEPTION, "{}", ex.getStackTrace());
        }
        return listLinks;
    }

    private List<String> getResources(Elements linksElements, String attributeKey,
                                      Path folderForSave, boolean changeNameFile,
                                      String attributeKeyForName, String regexExpressionForChange)
    {
        int amountFiles = linksElements.size();
        Path nameFile;
        System.out.println("Будет скачано " + amountFiles + " файлов");
        for (int i = 0; i < amountFiles; ) {
            try {
                Element linkElement = linksElements.get(i);
                url = new URL(linkElement.attr(attributeKey));
                if (changeNameFile) {
                    nameFile = getNameFile(linkElement, attributeKeyForName, regexExpressionForChange);
                } else {
                    nameFile = getNameFile(url);
                }
                connection = url.openConnection();
            } catch (Exception ex) {
                nameFile = folderForSave.resolve("Ошибка обработки имени");
                ex.printStackTrace();
                LOGGER.info(EXCEPTION, "{}", ex.getStackTrace());
            }
            listLinks.add(url.toString());
            downloadFile(nameFile, folderForSave);
            System.out.println("Скачивание " + ++i + " файла из " + amountFiles + " завершено");
        }
        System.out.println("Загрузка данных с сайта " + ADDRESS_SITE + " завершена");
        return listLinks;
    }

    private Elements getElements(String cssQuery) throws IOException {
        Document document = Jsoup.connect(ADDRESS_SITE).get();
        return document.select(cssQuery);
    }

    private void downloadFile(Path nameFile, Path folderForSave) {
        try (InputStream inputStream = connection.getInputStream()) {
            Files.copy(inputStream, folderForSave.resolve(nameFile));
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.info(EXCEPTION, "{}", ex.getStackTrace());
        }
        System.out.println(url + " " + nameFile);
    }

    private Path getNameFile(URL url) {
        return Paths.get(url.toString().replaceFirst("\\p{Punct}", "")).getFileName();
    }

    private Path getNameFile(Element element, String attributeKey, String regexExpressionForChange) {
        return Paths.get(element.attr(attributeKey).replaceAll(regexExpressionForChange, "")
                .replaceAll("\\p{Punct}", " ") + ".mp3");
    }
}