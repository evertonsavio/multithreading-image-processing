package dev.evertonsavio.app.reflections;

public class BookApp
{
    public static void main(String[] args)
    {
        Book book = new Book("Alice", (short) 200);
        Print<Book> bookPrinter = new Printer<>(book);
        bookPrinter.print(new String[]{"getBookName", "getNumberOfPages"});

        Car car = new Car("Fiat", "Ferrari F50", "Red");
        Print<Car> carPrinter = new Printer<>(car);
        carPrinter.print(new String[]{"getBrand", "getModel", "getColor"});
    }
}
