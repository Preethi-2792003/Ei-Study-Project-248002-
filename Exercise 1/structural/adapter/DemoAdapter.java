class LegacyPrinter {
    void printText(String text) {
        System.out.println("Legacy printer: " + text);
    }
}
interface Printer {
    void print(String message);
}
class PrinterAdapter implements Printer {
    private LegacyPrinter legacy = new LegacyPrinter();
    public void print(String message) {
        legacy.printText(message);
    }
}
public class DemoAdapter {
    public static void main(String[] args) {
        Printer printer = new PrinterAdapter();
        printer.print("Hello from Adapter Pattern!");
    }
}