package files;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class WriteFilesTest {

  @Test
  void shouldWriteToTmpFile() throws IOException {
    String content = "Hello World !!";
    File tempFile = File.createTempFile("aaa", ".txt");
    Files.write(tempFile.toPath(), content.getBytes());
    assertThat(Files.readAllLines(tempFile.toPath())).contains(content);
  }

}
