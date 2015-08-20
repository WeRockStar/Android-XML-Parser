package parse.we.com.xml;

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Kotchaphan Muangsan on 20/8/2558.
 */
public class ParseApplications {
    private String data;
    private ArrayList<Application> applicationArrayList;

    public ParseApplications(String xmlData) {
        data = xmlData;
        applicationArrayList = new ArrayList<Application>();
    }

    public ArrayList<Application> getApplicationArrayList() {
        return applicationArrayList;
    }

    public boolean process() {
        boolean operationStatus = true;

        Application currentRecord = null;
        //check exist tag
        boolean isEntry = false;
        String textValue = "";

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = factory.newPullParser();

            xmlPullParser.setInput(new StringReader(this.data));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xmlPullParser.getName();
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.END_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (tagName.equalsIgnoreCase("entry")) {
                        isEntry = true;
                        currentRecord = new Application();
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    textValue = xmlPullParser.getText();

                } else if (eventType == XmlPullParser.END_TAG) {
                    //in entry tag
                    if (isEntry) {
                        if (tagName.equalsIgnoreCase("entry")) {
                            applicationArrayList.add(currentRecord);
                            isEntry = false;
                        }
                        if (tagName.equalsIgnoreCase("name")) {
                            currentRecord.setName(textValue);
                        } else if (tagName.equalsIgnoreCase("artist")) {
                            currentRecord.setArtist(textValue);
                        } else if (tagName.equalsIgnoreCase("releaseDate")) {
                            currentRecord.setReleaseDate(textValue);
                        }
                    }
                }
                //move next tag
                eventType = xmlPullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            operationStatus = false;
        }
        for (Application app : applicationArrayList) {
            Log.d("DEBUG Name", app.getName());
            Log.d("DEBUG Artist", app.getArtist());
            Log.d("DEBUG ReleaseDate", app.getReleaseDate());
        }

        return operationStatus;
    }

}
