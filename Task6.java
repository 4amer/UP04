import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private List<Book> books;

    public Main() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) throws InvalidBookException {
        if (book == null || book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            throw new InvalidBookException("Недопустимое значение книги.");
        }
        books.add(book);
    }

    public void displayBooks() throws IOException {
        if (books.isEmpty()) {
            throw new IOException("Каталог пуст, книг нет.");
        }
        for (Book book : books) {
            System.out.println(book);
            try {
                book.displayIssueHistory();
            } catch (IssueHistoryNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static class Book {
        private String title;
        private String author;
        private List<IssueHistory> issueHistories;

        public Book(String title, String author) throws InvalidBookException {
            if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
                throw new InvalidBookException("Название книги и автор не могут быть пустыми.");
            }
            this.title = title;
            this.author = author;
            this.issueHistories = new ArrayList<>();
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public void addIssueHistory(String readerName, Date issueDate, Date returnDate) throws InvalidIssueHistoryException {
            if (issueDate.after(returnDate)) {
                throw new InvalidIssueHistoryException("Дата выдачи не может быть позже даты возврата.");
            }
            issueHistories.add(new IssueHistory(readerName, issueDate, returnDate));
        }

        public void displayIssueHistory() throws IssueHistoryNotFoundException {
            if (issueHistories.isEmpty()) {
                throw new IssueHistoryNotFoundException("История выдач отсутствует для книги: " + title);
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
        try {
            Main catalog = new Main();

            Book book1 = new Book("Мастер и Маргарита", "Михаил Булгаков");
            Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский");

            catalog.addBook(book1);
            catalog.addBook(book2);

            book1.addIssueHistory("Иван Иванов", new Date(124, 8, 1), new Date(124, 8, 15));
            book1.addIssueHistory("Петр Петров", new Date(124, 9, 1), new Date(124, 9, 10));

            book2.addIssueHistory("Мария Сергеева", new Date(124, 7, 1), new Date(124, 7, 15));

            catalog.displayBooks();
        } catch (InvalidBookException | InvalidIssueHistoryException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Недостаточно памяти для обработки: " + e.getMessage());
        }
    }
}

// Классы пользовательских исключений

class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message);
    }
}

class InvalidIssueHistoryException extends Exception {
    public InvalidIssueHistoryException(String message) {
        super(message);
    }
}

class IssueHistoryNotFoundException extends Exception {
    public IssueHistoryNotFoundException(String message) {
        super(message);
    }
}
