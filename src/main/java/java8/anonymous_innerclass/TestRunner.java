package java8.anonymous_innerclass;

public class TestRunner {
    public static void main(String[] args) {
        IPrintable obj = new ConsolePrinterImpl();
        obj.print();
        IPrintable obj1 = new IPrintable() {

            @Override
            public void print() {
                System.out.println("From Anonymous Inner Class");
            }
        };
        IPrintable obj2 = new IPrintable() {

            @Override
            public void print() {
                System.out.println("From Anonymous Inner Class Second");
            }
        };
        obj2.print();
        obj1.print();
    }
}
