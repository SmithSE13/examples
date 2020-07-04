import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParserHTML {
    private static Document document;
    private static URL url;
    private static URLConnection connection;
    private static List<String> listLinksImages = new ArrayList<>();
    private static List<String> listLinksAudio = new ArrayList<>();

    public static void parseFileToImage(String addressSite, Path folderForSave) {
        try {
            Files.createDirectories(folderForSave);
            document = Jsoup.connect(addressSite).get();
            Elements linksImages = document.select("img");
            Path nameFile = null;

            for(int i = 0; i < linksImages.size(); i++) {
                url = new URL(linksImages.get(i).attr("abs:src"));
                try {
                    nameFile = Paths.get(url.toString().replaceFirst("\\p{Punct}", "")).getFileName();
                } catch (Exception ex) {
                    nameFile = folderForSave.resolve("Ошибка обработки имени");
                    ex.printStackTrace();
                }
                listLinksImages.add(url.toString());
                connection = url.openConnection();
                System.out.println(url + " " + nameFile);
                try (InputStream inputStream = connection.getInputStream()) {
                    Files.copy(inputStream, folderForSave.resolve(nameFile));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Files.write(folderForSave.resolve("Список ссылок картинок.txt"), listLinksImages);
            System.out.println("Все картинки скачаны");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void parseFileToAudio(String addressSite, Path folderForSave) {
        try {
            Files.createDirectories(folderForSave);
            document = Jsoup.connect(addressSite).get();
            Elements linksAudio = document.select("[href$=.json]");
            String fileName;

            for(Element e : linksAudio) {
                fileName = e.attr("title").replaceAll("Скачать трек", "")
                        .replaceAll("\\p{Punct}", " ");
                url = new URL(e.attr("abs:href"));
                listLinksAudio.add(url.toString());
                connection = url.openConnection();
                System.out.println(url + " " + fileName);
                try (InputStream inputStream = connection.getInputStream()) {
                    Files.copy(inputStream, folderForSave.resolve(fileName + ".mp3"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Files.write(folderForSave.resolve("Список ссылок песен.txt"), listLinksAudio);
            System.out.println("Все треки скачаны");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void parseFileToAudio2(String addressSite, Path folderForSave) {
        try {
            Files.createDirectories(folderForSave);
            document = Jsoup.connect(addressSite).get();
            Elements linksAudio = document.select("[href$=.mp3]");
            Path fileName;
            for(Element e : linksAudio) {
                url = new URL(e.attr("abs:href"));
                fileName = (Paths.get(url.toString().replaceFirst("\\p{Punct}", ""))).getFileName();
                listLinksAudio.add(url.toString());
                connection = url.openConnection();
                System.out.println(url + " " + fileName);
                try (InputStream inputStream = connection.getInputStream()) {
                    Files.copy(inputStream, folderForSave.resolve(fileName));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Files.write(folderForSave.resolve("Список ссылок песен.txt"), listLinksAudio);
            System.out.println("Все треки скачаны");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
