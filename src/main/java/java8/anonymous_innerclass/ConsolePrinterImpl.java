package java8.anonymous_innerclass;

public class ConsolePrinterImpl implements IPrintable{

    @Override
    public void print() {
        System.out.println("Printing On Console");
    }
}
