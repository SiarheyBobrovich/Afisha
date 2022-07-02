package by.it_academy.afisha;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class URLConnectionTest {

    @Test
    public void test() throws IOException {
        URL url = new URL("http://localhost/api/v1/afisha/event/CONCERTS");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

//        connection.setDoOutput(true);
//
//        OutputStream os = connection.getOutputStream();
//        os.write(("" +
//                "{\n" +
//                "  \"title\": \"Some title\",\n" +
//                "  \"description\": \"description\",\n" +
//                "  \"dt_event\": 0,\n" +
//                "  \"dt_end_of_sale\": 0,\n" +
//                "  \"status\": \"DRAFT\",\n" +
//                "  \"currency\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" +
//                "}").getBytes());
//        os.flush();
//        os.close();

        int responseCode = connection.getResponseCode();

        System.out.println("GET Response Code :: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        System.out.println(response.toString());

    }
}
