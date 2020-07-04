import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path forSaveImages = Paths.get("images/");
        Path forSaveMusic = Paths.get("C:/Work directory/Музыка");

        ParserHTML.parseFileToImage("https://lenta.ru/", forSaveImages);
        ParserHTML.parseFileToAudio("https://zaycev.net", forSaveMusic);
        ParserHTML.parseFileToAudio2("https://ruq.hotmo.org/", forSaveMusic);
    }
}
