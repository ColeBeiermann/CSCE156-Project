import java.util.Comparator;
import java.util.Iterator;

public class InvoiceList implements Iterable<Invoice>{

	private InvoiceNode<Invoice> start;
	private int size;
	private Comparator<Invoice> comp;
	
	public InvoiceList(Comparator<Invoice> comp) {
		this.head = null;
		this.size = 0;
		this.comp = comp;
		// TODO Auto-generated constructor stub
	}
	
	public Comparator<Invoice> getComp(){
		return comp;
	}

	@Override
	public Iterator<Invoice> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	class IteratorInvoice implements Iterator<Invoice>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Invoice next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void remove() {
			// TODO
		}
		
	}
	
	public void add(Invoice item) {
		
	}

}
