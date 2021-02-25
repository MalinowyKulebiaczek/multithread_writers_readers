import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class Zadanie2 {
	
	public Zadanie2(int B, int W, int R) throws InterruptedException {
		
		System.out.println("Number of writers: " + W);
		System.out.println("Number of books: " + B);
		System.out.println("Number of readers: " + R);
		
		BlockingQueue<Book> booksToWrite = new ArrayBlockingQueue<>(B);
		BlockingQueue<Book> bookStorage = new ArrayBlockingQueue<>(B);
		
		//dodanie ksiazek do listy "do napisania"
		for(int i=0; i<B; i++) {
			Book book = new Book(i);
			booksToWrite.add(book);
			//new Thread(w, threadName).start();
		}
		
		//stworzenie czytelnikow
		String readerName;
		for(int i=0; i<R; i++) {
			readerName = "Reader"+i;
			Reader r = new Reader(B, bookStorage);
			new Thread(r, readerName).start();
		}
		
		//stworzenie pisarzy
		String writerName;
		for(int i=0; i<W; i++) {
			writerName = "Writer"+i;
			Writer w = new Writer(booksToWrite, bookStorage);
			new Thread(w, writerName).start();
		}
		
	}

	/**
	 * @param args nie używane.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		int B = 4; //liczba ksiazek do napisania
		int W = 3; //liczba pisarzy
		int R = 2; //liczba czytelnikow
		
		new Zadanie2(B, W, R);
	}
}
