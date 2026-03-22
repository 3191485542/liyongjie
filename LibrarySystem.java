import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class LibrarySystem {
    private ArrayList<Book> bookList = new ArrayList<>();
    private HashSet<String> genres = new HashSet<>();
    private HashMap<Integer, Book> bookMap = new HashMap<>();

    public void addBook(Book book) {
        bookList.add(book);
        genres.add(book.getGenre());
        bookMap.put(book.getId(), book);
    }

    public void removeBooksByKeyword(String keyword) {
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().contains(keyword)) {
                iterator.remove();
                bookMap.remove(book.getId());
            }
        }
    }

    public void displayStatus() {
        System.out.println("===== Book List (ArrayList) =====");
        for (Book b : bookList) {
            System.out.println(b);
        }

        System.out.println("\n===== Genres (HashSet) =====");
        System.out.println(genres);

        System.out.println("\n===== Book Map (HashMap) =====");
        System.out.println(bookMap);
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        library.addBook(new Book(1, "Java 编程思想", "编程"));
        library.addBook(new Book(2, "红楼梦", "古典文学"));
        library.addBook(new Book(3, "Java 核心技术", "编程"));
        library.addBook(new Book(4, "三体", "科幻"));

        System.out.println("=== 初始状态 ===");
        library.displayStatus();

        library.removeBooksByKeyword("Java");

        System.out.println("\n=== 删除关键字 'Java' 后 ===");
        library.displayStatus();
    }
}