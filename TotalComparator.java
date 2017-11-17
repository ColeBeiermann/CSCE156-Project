import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		double Total1 = 0;
		double Total2 = 0;
		inv1.getProductList();
		for (Product a: inv1.getProductList()) {
			Total1 += a.getSubtotal();
		}
		for (Product a: inv2.getProductList()) {
			Total2 += a.getSubtotal();
		}
		
		double x = (Total1 - Total2);
		if (x>0) {
			return 1;
		}
		else if (x<0) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
