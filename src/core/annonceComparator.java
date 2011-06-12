package core;

import java.util.Comparator;
import java.util.Date;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import entities.Annonce;

public class annonceComparator implements Comparator<Annonce> {

	private String sord;
	private String sidx;
	
	public annonceComparator(String so, String si) {
		sord = so;
		sidx = si;
	}
	
	@Override
	public int compare(Annonce o1, Annonce o2) {
		int res = 0;
		try {
			Object expr = Ognl.parseExpression(sidx);
		    OgnlContext ctx = new OgnlContext();
		    Comparable value1 = (Comparable) Ognl.getValue(expr, ctx, o1);
		    Comparable value2 = (Comparable) Ognl.getValue(expr, ctx, o2);
		    
		    System.out.println("Value1 : "+value1+"Value2 : "+value2);
		    
		    if (sord.equals("asc"))
		    	res = value1.compareTo(value2);
		    else if (sord.equals("desc"))
		    	res = value2.compareTo(value1);
		    
		} catch (OgnlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
