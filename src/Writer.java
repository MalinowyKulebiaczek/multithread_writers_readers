import java.util.concurrent.BlockingQueue;

public class Writer implements Producer{
	
	private BlockingQueue<Book> booksToWrite = null;
	private BlockingQueue<Book> bookStorage  = null;
	/**
	 * Konstrukcja prostego konsumenta.
	 * @param b2w kolejka ksiazek do napisania
	 * @param bs kolejka gotowych ksiazek
	 */
	public Writer(BlockingQueue<Book> b2w, BlockingQueue<Book> bs) {
		this.booksToWrite = b2w;
		this.bookStorage = bs;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(booksToWrite.size()>0) {
			produce();
		}
	}

	@Override
	public void produce() {
		// TODO Auto-generated method stub
		try {
			// Thread.yield();
			Book b = booksToWrite.take();
			System.out.println(Thread.currentThread().getName() + ": writing book no.: [" + b.getNumber() + "]");
			sleeep(2000);
			System.out.println(Thread.currentThread().getName() + ": finished book no: [" + b.getNumber() + "]");
			bookStorage.add(b);
			
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
