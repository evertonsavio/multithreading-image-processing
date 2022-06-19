package dev.evertonsavio.app.reflections;

public class Book
{
    private final String bookName;
    private final Short numberOfPages;

    public Book(String bookName, Short numberOfPages)
    {
        this.bookName = bookName;
        this.numberOfPages = numberOfPages;
    }

    public String getBookName()
    {
        return bookName;
    }

    public Short getNumberOfPages()
    {
        return numberOfPages;
    }
}