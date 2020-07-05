import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path forSaveImages = Paths.get("images/");
        Path forSaveMusic = Paths.get("C:/Work directory/Музыка");

        new ParserHTML("https://lenta.ru/")
                .parseFile(forSaveImages, "img", "abs:src", "Картинки");
//        new ParserHTML("https://muzofond.fm/")
//                .parseFile(forSaveMusic, "li[data-url]", "data-url", "Музыка");
        new ParserHTML("https://w1.musify.club/")
                .parseFile(forSaveMusic, "div[data-url$=.mp3]", "abs:data-url", "Музыка");
        new ParserHTML("https://ruq.hotmo.org/")
                .parseFile(forSaveMusic,"[href$=.mp3]", "abs:href", "Музыка");
        new ParserHTML("https://zaycev.net")
                .parseFileSpecialForZaycevNet(forSaveMusic,"[href$=.json]", "abs:href", "Музыка");
    }
}
