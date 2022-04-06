package com.fitbitdataalocator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Iterator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Conner Lavineway March 2022
 *
 * This program will scrape The Roling Stones "500 greatest albums of all time" from 2020
 * and return a ordered text file to be read by other programs
 */
public class fitbitreader
{
    final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    final LocalDate TODAY = LocalDate.now();
    //date first joined
    final LocalDate FIRST_DAY = LocalDate.of(2017, 8, 4);
    final long DAYS_BETWEEN = Duration.between(FIRST_DAY, TODAY).toDays();

    private LocalDate checkDate = FIRST_DAY;
    public void run() throws IOException
    {
        String mylink = "https://www.fitbit.com";
        File outputFile = new File("Output.txt");
        Long daysSince = 0L;
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8)); 
        /* 
            set writer to use UTF 8 Character encoding instead of ASCII
            ASCII wont properly write all text to file, spotify cant read ASCII unkown character
        */
        /*
        while(daysSince < DAYS_BETWEEN)
        {
            checkDate = FIRST_DAY.plusDays(daysSince);

            String date = FORMAT.format(checkDate);

            writer.add(checkSite(mylink, date));

            daysSince++;
        }
        */
        writer.append(checkSite(mylink, FORMAT.format(FIRST_DAY)) + "\n");
        writer.close();
    }

    private String checkSite(String site, String subPage) throws IOException
    {
        String checkLink = site + subPage;

        Connection connection = Jsoup.connect(checkLink); //connect to page
        connection.userAgent("Mozilla/5.0"); 
        /*
            without setting user agent the site will flag as a bot
            and deny access 
        */

        Document doc = connection.get(); //get page as a document
        
        Elements text = doc.select("div.tile small unflipped restingHeartRate > div.summary"); //get dives wiht the heartrate monitor

        
        String out = text.text();
        
       return out.toString();
    }
}
