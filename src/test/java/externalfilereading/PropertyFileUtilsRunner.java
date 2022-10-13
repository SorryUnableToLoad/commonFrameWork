package externalfilereading;

public class PropertyFileUtilsRunner {
    public static void main(String[] args) {
        String browser = PropertyFileUtils.readDataFromPropertyFile("browser");
        String favtools = PropertyFileUtils.readDataFromPropertyFile("favtools");
        String loves = PropertyFileUtils.readDataFromPropertyFile("loves");
        System.out.println(browser);
        System.out.println(favtools);
        System.out.println(loves);
    }
}
