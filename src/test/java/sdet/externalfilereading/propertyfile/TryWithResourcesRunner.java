package sdet.externalfilereading.propertyfile;

import sdet.externalfilereading.propertyfile.ConfigProperties;
import sdet.externalfilereading.propertyfile.TryWithResources;

public class TryWithResourcesRunner {
    public static void main(String[] args) {
        String browser = TryWithResources.get("browser");
        String favtools = TryWithResources.get("favtools");
        String loves=TryWithResources.get("loves");
        System.out.println(browser);
        System.out.println(favtools);
        System.out.println(loves);

        System.out.println("=========================");

        String browsers = TryWithResources.get(ConfigProperties.BROWSER);
        String favtoolss = TryWithResources.get(ConfigProperties.FAVTOOLS);
        String lovess=TryWithResources.get(ConfigProperties.LOVES);
        System.out.println(browsers);
        System.out.println(favtoolss);
        System.out.println(lovess);


    }

}
