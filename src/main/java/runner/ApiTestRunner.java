package runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ApiTestRunner {
    protected static final String JSON = "application/json";
    protected final static String DEFAULT_USER_TOKEN = readTokenFromFile("access_key");
    protected static String IP_ADDRESS = getIpAddress();
    private static final String IP_ADDRESS_CHECKER_URL = "http://myexternalip.com/raw";

    private static String getIpAddress() {
        URL ipAddress;
        try {
            ipAddress = new URL(IP_ADDRESS_CHECKER_URL);
            return new BufferedReader(new InputStreamReader(ipAddress.openStream()))
                    .readLine();
        } catch (MalformedURLException e) {
            throw new AssertionError("Incorrect url :" + IP_ADDRESS_CHECKER_URL, e);
        } catch (IOException e) {
            throw new AssertionError("Can not read the data from: " + IP_ADDRESS_CHECKER_URL, e);
        }
    }

    private static String readTokenFromFile(String filename) {
        try {
            var accessKeyFile = new File(Objects.requireNonNull(ApiTestRunner.class.getClassLoader()
                    .getResource("access_key")
                    .getFile()));
            return Files.readString(accessKeyFile.toPath(), UTF_8);
        } catch (Exception e) {
            throw new AssertionError("Failed to read token file " + filename, e);
        }
    }
}
