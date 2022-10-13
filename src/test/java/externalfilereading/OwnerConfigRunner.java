package externalfilereading;

import org.aeonbits.owner.ConfigFactory;

public class OwnerConfigRunner {
    public static void main(String[] args) {
        OwnerConfig config = ConfigFactory.create(OwnerConfig.class);
        System.out.println(config.favtools());
        System.out.println(config.browser());
        System.out.println(config.loves());

    }
}
