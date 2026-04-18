package Lesson11_Adapter_Design_Pattern;

interface IReport {
    String getJsonData();
}

class XmlDataProviderAdapter implements IReport {
    private XmlDataProvider xmlDataProvider;

    public XmlDataProviderAdapter(XmlDataProvider xmlDataProvider) {
        this.xmlDataProvider = xmlDataProvider;
    }

    @Override
    public String getJsonData() {
        String xmlData = xmlDataProvider.getXmlData();
        // Simple conversion from XML to JSON (for demonstration purposes)
        String jsonData = xmlData.replaceAll("<data>", "{")
                .replaceAll("</data>", "}")
                .replaceAll("<name>(.*?)</name>", "\"name\":\"$1\"")
                .replaceAll("<age>(.*?)</age>", "\"age\":$1");
        return jsonData;
    }
}

class XmlDataProvider {
    public String getXmlData() {
        return "<data><name>John Doe</name><age>30</age></data>";
    }
}

public class AdapterDesignPattern {
    public static void main(String[] args) {
        XmlDataProvider xmlDataProvider = new XmlDataProvider();
        IReport report = new XmlDataProviderAdapter(xmlDataProvider);
        System.out.println(report.getJsonData());
    }
}
