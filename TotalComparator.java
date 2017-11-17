import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		int x = (inv1.getInvoiceCode()).compareTo(inv2.getInvoiceCode());
		return x;
	}

}
