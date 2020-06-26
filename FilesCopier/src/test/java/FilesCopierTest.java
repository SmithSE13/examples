import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.*;

public class FilesCopierTest extends TestCase {
    private FilesCopier filesCopier;
    private Path pathFromCopy;
    private Path pathForCopy;
    private Path pathForCopyThrowException;

    @Override
    protected void setUp() {
        filesCopier = new FilesCopier();
        pathFromCopy = Paths.get("fromCopy");
        pathForCopy = Paths.get("forCopy");
        pathForCopyThrowException = Paths.get("forCopy2");
    }

    public void testDoCopyFiles0() throws IOException {
        filesCopier.copy(pathFromCopy, pathForCopy);
        assertEquals(getLength(pathFromCopy), getLength(pathForCopy));
    }

    public void testDoCopyFiles1() {
        //ожидаем исключение FileAlreadyExistsException

        filesCopier.copy(pathFromCopy, pathForCopyThrowException);
    }

    public long getLength(Path pathDirectory) throws IOException {
        return Files.walk(pathDirectory)
                .map(path -> path.toFile().length())
                .reduce(Long::sum)
                .get();
    }

    @Override
    protected void tearDown() throws IOException {
        Files.walk(pathForCopy)
                .forEach(path -> path.toFile().deleteOnExit());
        filesCopier = null;
        pathFromCopy = null;
        pathForCopy = null;
        pathForCopyThrowException = null;
    }
}
