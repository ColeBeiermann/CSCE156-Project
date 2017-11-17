
public class InvoiceNode<Invoice>{
	public Invoice Inv;
	public InvoiceNode<Invoice> nextNode;
	public InvoiceNode<Invoice> lastNode;

	public InvoiceNode(Invoice Inv) {
		this.Inv = Inv;
		this.nextNode = null;
		this.lastNode = null;
	}
	
	
	public InvoiceNode<Invoice> getNextNode() {
		return nextNode;
	}

	public void setNextNode(InvoiceNode<Invoice> nextNode) {
		this.nextNode = nextNode;
	}
	
	public InvoiceNode<Invoice> getLastNode() {
		return lastNode;
	}

	public void setLastNode(InvoiceNode<Invoice> lastNode) {
		this.lastNode = lastNode;
	}
	
	public Invoice getInv() {
		return Inv;
	}

	public void setInv(Invoice Inv) {
		this.Inv = Inv;
	}
	
	

}
