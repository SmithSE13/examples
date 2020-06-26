import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
       FilesCopier fc = new FilesCopier();
       go(fc);
    }

    private static void go(FilesCopier filesCopier) {
        System.out.println("Откуда копировать файлы? : ");
        Path filePathFromCopy = filesCopier.getEnter();
        System.out.println("Куда : ");
        Path filePathForCopy = filesCopier.getEnter();
        System.out.println("Копирую отсюда: \"" + filePathFromCopy.toString() +
                "\" вот сюда: \"" + filePathForCopy.toString() + "\"");

        filesCopier.copy(filePathFromCopy, filePathForCopy);
    }
}
