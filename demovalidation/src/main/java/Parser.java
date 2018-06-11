import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Parser {
    //method to read URL path given by user
    public String urlPath() {
        System.out.println("Please enter your JSON URL : ");

        //https://git.io/vpg9V
        //https://git.io/vpg95

        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        System.out.println("Retrieving data from URL ...");
        return url;
    }

    //bool method to parse json file and return whether the file is valid or not
    public boolean run(File schemaFile) throws Exception {

            //JSONArray of the given URL
            JSONArray jsonArray = new JSONArray(IOUtils.toString(new URL(urlPath()), Charset.forName("UTF-8")));
            int errorCounter = 0;
            int nullCounterWP = 0;
            int nullCounterNM = 0;
            int nullCounterATC = 0;
            int nullCounterSP = 0;
            int nullCounterD = 0;
            int nullCounterC = 0;

            System.out.println("Processing your json file ...");

            for (Object object : jsonArray) {
                //create jsonObject
                JSONObject jsonData = (JSONObject) object;
                //System.out.println(jsonData);

                //to count number of occurrences of null values for each key in jsonObject

                if (jsonData.isNull("web_pages")) {
                    nullCounterWP++;
                }
                if (jsonData.isNull("name")) {
                    nullCounterNM++;
                }
                if (jsonData.isNull("alpha_two_code")) {
                    nullCounterATC++;
                }
                if (jsonData.isNull("state-province")) {
                    nullCounterSP++;
                }
                if (jsonData.isNull("domains")) {
                    nullCounterD++;
                }
                if (jsonData.isNull("country")) {
                    nullCounterC++;
                }


                if (ValidationUtils.isJsonValid(FileUtils.readFileToString(schemaFile), jsonData.toString())) {
                    //if the jsonObject is valid do nothing
                } else {
                    //otherwise increment the error counter and print in which jsonObject it was exactly
                    errorCounter++;
                    System.out.println("Error found at : " + jsonData);
                }
            }

            //number of jsonObjects parsed and number of errors found
            System.out.println("Total number of objects parsed : " + jsonArray.length());
            System.out.println(errorCounter + " Errors were found !");
            //bunch of sout to print the number of null values for each key in the jsonObject (if I understood it well)
            System.out.println("Null occurrences for Web pages : " + nullCounterWP);
            System.out.println("Null occurrences for Name : " + nullCounterNM);
            System.out.println("Null occurrences for Alpha two code : " + nullCounterATC);
            System.out.println("Null occurrences for State province : " + nullCounterSP);
            System.out.println("Null occurrences for Domains : " + nullCounterD);
            System.out.println("Null occurrences for Country : " + nullCounterC);

            //if errors were found during the loop then the JSON is not valid
            if (errorCounter > 0) {
                //File is valid to schema
                return true;
            } else {
                //otherwise the JSON is valid to schema
                return false;
            }


    }


    // this method is to print result of json validation
    public void printResult(){

        File schemaFile = new File("src\\main\\resources\\schema.json");

        try {
            if (run(schemaFile) == true) {
                System.out.println("JSON file is NOT valid !\n");
            }
            else {
                System.out.println("JSON file is valid !");
            }
        } catch(Exception e){
            System.out.println("Error found :" + e.getMessage());
        }
    }
}
