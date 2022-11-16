package java8.functional_interface;

@FunctionalInterface
public interface IPrintable {
    //interface with only one abstract or unimplemented method.
    //From java-8 interface can have any number of default or static methods,
    //but it should contain only one abstract method to be called as functional interfaces.
    //Using Functional interfaces annotation.
    //----------------------------------------------------------------------------------------//
    //public abstract void print();
    //----------------------------------------------------------------------------------------//
    /*//we can't provide more than one abstract method in functional interface
    public abstract void scanner();*/
    //----------------------------------------------------------------------------------------//

    //we can have default method and static method as well
    default void scan() {
        System.out.println("scanning");
    }

    static void printToConsole() {
        System.out.println("printing on console");

    }

    default void scanAsPdf() {
        System.out.println("scanning as pdf");
    }

    //----------------------------------------------------------------------------------------//
    //public abstract void print(int a);
    //----------------------------------------------------------------------------------------//
    //public abstract void print(String a);
    //----------------------------------------------------------------------------------------//
    //public abstract void print(String a, String b);
    //----------------------------------------------------------------------------------------//
    //public abstract String print();
    //----------------------------------------------------------------------------------------//
    //public abstract String print(String a);
    //----------------------------------------------------------------------------------------//
    public abstract Boolean print(String a);
    //----------------------------------------------------------------------------------------//
}
