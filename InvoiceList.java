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
		
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);
		
		if (start == null) {
			start = newInvoiceNode;
			size++;
		}
		if (size == 1) {
			if (item.compareTo(start.Inv) < 0) {
				start.nextNode = start;
				start = newInvoiceNode;
				start.nextNode.lastNode = start;
			}
			else {
				start.nextNode = newInvoiceNode;
				start.nextNode.lastNode = start;
			}
			size++;
		}
		if(size > 1) {
			InvoiceNode<Invoice> current = start;
			while (current.nextNode != null) {
				if (item.compareTo(current.Inv) < 0) {
					InvoiceNode<Invoice> holder = current;
					while (current != null) {
						if (current.nextNode != null) {
							current = current.nextNode;
						}
						else {
							while ((current.Inv).compareTo(holder.Inv) >= 0) {
								current.nextNode = current;
								current.nextNode.lastNode = (current.lastNode);
								current = current.lastNode;
							}
							newInvoiceNode.nextNode = current.nextNode;
							newInvoiceNode.lastNode = current.lastNode;
							current = newInvoiceNode;
							current.nextNode.lastNode = newInvoiceNode;
							current.lastNode.nextNode = newInvoiceNode;
							
							//IN WORKS, Not sure if right
							
						}
					}
						
				}
				else {
					current = current.nextNode;
				}
				
			}
			size++;
		}
	}

}
