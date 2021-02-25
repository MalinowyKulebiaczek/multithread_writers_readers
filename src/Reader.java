import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Reader implements Consumer{
	
	//private int readBooks;
	private List<Integer> readBooks = new ArrayList<Integer>();
	private int B;
	private BlockingQueue<Book> bookStorage  = null;
	
	public Reader(int B, BlockingQueue<Book> bs) {
		//this.readBooks = rb;
		this.bookStorage = bs;
		this.B = B;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(readBooks.size()<B) {
			consume();
		}
	}

	@Override
	public void consume() {
		// TODO Auto-generated method stub
		try {			
			Book b = bookStorage.take();
			
			//sprawdzenie czy dany czytelnik czytal juz te ksiazke
			if(readBooks.contains(b.getNumber())) {		//jesli tak - zwroc ksiazke od razu
				bookStorage.add(b);
			} else {									//jesli nie - czytaj, dodaj do swojej listy przeczytanych i potem zwroc
				System.out.println(Thread.currentThread().getName() + ": reading book no.: [" + b.getNumber() + "]");
				sleeep(2000);
				System.out.println(Thread.currentThread().getName() + ": finished reading book no.: [" + b.getNumber() + "]");
				readBooks.add(b.getNumber());
				bookStorage.add(b);
			}	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sleeep(long t) {
		try {
			Thread.sleep((long) (t * Math.random()));
		} catch (InterruptedException ie) {
		}
	}

}
