package sdet.externalfilereading.propertyfile;

import org.aeonbits.owner.ConfigFactory;
import sdet.externalfilereading.propertyfile.OwnerConfig;

public class OwnerConfigRunner {
    public static void main(String[] args) {
        OwnerConfig config = ConfigFactory.create(OwnerConfig.class);
        System.out.println(config.favtools());
        System.out.println(config.browser());
        System.out.println(config.loves());

    }
}
