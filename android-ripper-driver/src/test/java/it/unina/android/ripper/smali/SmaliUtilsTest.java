package it.unina.android.ripper.smali;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;

/**
 *
 * @author Artur
 */
public class SmaliUtilsTest {
    
    public SmaliUtilsTest() {
    }

    @org.junit.Test
    public void testInjectTrace() throws IOException {
        // g
        File testFile = new File("src/test/resources/", "MainActivity.smali");
        File testFileCopy = new File("build/tmp/resources/", "MainActivity.smali");
        FileUtils.copyFile(testFile, testFileCopy);
        
        // w
        SmaliUtils.injectTrace(testFileCopy);
        
        // t
        File expectedFile = new File("src/test/resources/", "MainActivity-expected.smali");
        Assertions.assertThat(testFileCopy).hasSameContentAs(expectedFile);
    }
    
    @org.junit.Test
    public void testInjectDir() throws IOException {
        // g
        File testDir = new File("src/test/resources/dir");
        File testDirCopy = new File("build/tmp/resources/dir-processed");
        FileUtils.copyDirectory(testDir, testDirCopy);
        
        // w
        SmaliUtils.injectTraceOnProject(testDirCopy);
        
        // t
        File expectedFile = new File("src/test/resources/dir-expected");
        assertThatFilesEqual(testDirCopy, expectedFile, "a/MainActivity.smali");
        assertThatFilesEqual(testDirCopy, expectedFile, "b/MainActivity.smali");        
    }

    private void assertThatFilesEqual(File testDirCopy, File expectedFilesDir, String fileName) {
        Assertions.assertThat(new File(testDirCopy, fileName)).hasSameContentAs(new File(expectedFilesDir, fileName));
    }
    
}
