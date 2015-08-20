package parse.we.com.xml;

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

                } else if (eventType == XmlPullParser.END_TAG) {

                }
                eventType = xmlPullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            operationStatus = false;
        }

        return operationStatus;
    }

}
