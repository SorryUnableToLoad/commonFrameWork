package java8.functional_interface.lambda_expression;

public class TestRunner {
    public static void main(String[] args) {
        //Conventional Way
        IPrintable obj = new LogFileImpl();
        obj.print();
        //Anonymous Inner Class
        IPrintable obj1 = new IPrintable() {
            @Override
            public void print() {
                System.out.println("From Anonymous Inner Class");
            }
        };
        obj1.print();
        //Functional Programming
        //1.parameter of the abstract method and your lambda expression
        //2.return type
        //3.only one abstract method or unimplemented method
        IPrintable obj2 = () -> System.out.println("I Am A Lambda Expression");
        obj2.print();
    }
}
