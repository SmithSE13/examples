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

    public List<String> parseFile(Path folderForSave, String cssQuery, String attributeKey, String comment) {
        try {
            Files.createDirectories(folderForSave);
            getResources(getElements(cssQuery),attributeKey, folderForSave);
            Files.write(folderForSave.resolve(ADDRESS_SITE.replaceAll("\\p{Punct}", "_") + ".txt"), listLinks);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.info(EXCEPTION, "{}", ex.getStackTrace());
        }
        return listLinks;
    }

    private List<String> getResources(Elements linksElements, String attributeKey, Path folderForSave) {
        Path nameFile;
        int amountFiles = linksElements.size();
        int count = 0;
        System.out.println("Будет скачано " + linksElements.size() + " файлов");
        for (Element linkElement : linksElements) {
            count++;
            try {
                url = new URL(linkElement.attr(attributeKey));
                nameFile = Paths.get(url.toString().replaceFirst("\\p{Punct}", "")).getFileName();
                connection = url.openConnection();
            } catch (Exception ex) {
                nameFile = folderForSave.resolve("Ошибка обработки имени");
                ex.printStackTrace();
                LOGGER.info(EXCEPTION, "{}", ex.getStackTrace());
            }
            listLinks.add(url.toString());
            downloadFile(nameFile, folderForSave);
            System.out.println("Скачивание " + count + " файла из " + amountFiles + " завершено");
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
//*************** Специально для Zaycev.net, чтобы сохранить с нормальным названием ***************

    public List<String> parseFileSpecialForZaycevNet(Path folderForSave, String cssQuery, String attributeKey, String comment) {
        try {
            Files.createDirectories(folderForSave);
            getResourcesSpecialForZaycevNet(getElements(cssQuery),attributeKey, folderForSave);
            Files.write(folderForSave.resolve(ADDRESS_SITE.replaceAll("\\p{Punct}", "_") + ".txt"), listLinks);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.info(EXCEPTION, "{}", ex.getStackTrace());
        }
        return listLinks;
    }

    private List<String> getResourcesSpecialForZaycevNet(Elements linksElements, String attributeKey, Path folderForSave) {
        Path nameFile;
        int amountFiles = linksElements.size();
        int count = 0;
        for (Element linkElement : linksElements) {
            count++;
            try {
                url = new URL(linkElement.attr(attributeKey));
                nameFile = Paths.get(linkElement.attr("title").replaceAll("Скачать трек", "")
                        .replaceAll("\\p{Punct}", " ") + ".mp3");
                connection = url.openConnection();
            } catch (Exception ex) {
                nameFile = folderForSave.resolve("Ошибка обработки имени");
                ex.printStackTrace();
                LOGGER.info(EXCEPTION, "{}",ex.getStackTrace());
            }
            listLinks.add(url.toString());
            downloadFile(nameFile, folderForSave);
            System.out.println("Скачивание " + count + " файла из " + amountFiles + " завершено");
        }
        System.out.println("Загрузка данных с сайта " + ADDRESS_SITE + " завершена");
        return listLinks;
    }
}
