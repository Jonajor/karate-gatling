package runner;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.Assert.*;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@KarateOptions(tags = {"~@ignore"},features = {"src/test/java/movie/parallel/"})
public class ParallelRunner {

    @Test
    public void testParallel() {
        String karateOutputPath = "target/surefire-reports";
        Results results = Runner.parallel(getClass(), 5, karateOutputPath);
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }

    @Test
    public void testRunner() {
        String karateOutputPath = "target/surefire-reports";
        Results karateStats = Runner.parallel(getClass(), 1, karateOutputPath);
        generateReport(karateOutputPath);
        assertEquals("Some scenarios did not pass the tests", 0, karateStats.getFailCount());
    }

    private static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        final List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(jsonFile -> jsonPaths.add(jsonFile.getAbsolutePath()));
        Configuration configuration = new Configuration(new File("target"), "Tasklist Management API");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, configuration);
        reportBuilder.generateReports();
    }
}