import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Catalog {
    private List<Book> books;

    public Catalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
            book.displayIssueHistory();
        }
    }

    public static class Book {
        private String title;
        private String author;
        private List<IssueHistory> issueHistories;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.issueHistories = new ArrayList<>();
        }

        public void addIssueHistory(String readerName, Date issueDate, Date returnDate) {
            issueHistories.add(new IssueHistory(readerName, issueDate, returnDate));
        }

        public void displayIssueHistory() {
            if (issueHistories.isEmpty()) {
                System.out.println("История выдач отсутствует.");
            } else {
                System.out.println("История выдач книги: " + title);
                for (IssueHistory history : issueHistories) {
                    System.out.println(history);
                }
            }
        }

        @Override
        public String toString() {
            return "Книга: " + title + ", Автор: " + author;
        }

        private static class IssueHistory {
            private String readerName;
            private Date issueDate;
            private Date returnDate;

            public IssueHistory(String readerName, Date issueDate, Date returnDate) {
                this.readerName = readerName;
                this.issueDate = issueDate;
                this.returnDate = returnDate;
            }

            @Override
            public String toString() {
                return "Читатель: " + readerName + ", Дата выдачи: " + issueDate + ", Дата возврата: " + returnDate;
            }
        }
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        Book book1 = new Book("Мастер и Маргарита", "Михаил Булгаков");
        Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский");

        catalog.addBook(book1);
        catalog.addBook(book2);

        book1.addIssueHistory("Иван Иванов", new Date(2024, 8, 1), new Date(2024, 8, 15));
        book1.addIssueHistory("Петр Петров", new Date(2024, 9, 1), new Date(2024, 9, 10));

        book2.addIssueHistory("Мария Сергеева", new Date(2024, 7, 1), new Date(2024, 7, 15));

        catalog.displayBooks();
    }
}
