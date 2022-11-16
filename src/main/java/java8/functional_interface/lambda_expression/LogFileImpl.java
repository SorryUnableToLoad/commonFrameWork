package java8.functional_interface.lambda_expression;

public class LogFileImpl implements IPrintable{

    @Override
    public void print() {
        System.out.println("Conventional Way of doing things");
    }
}
