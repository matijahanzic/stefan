package stefanpresentationlayer;

import java.util.Collections;
import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import stefan.business.objects.OrderOrderNumberComparator;
import stefan.business.objects.OrderPositionComparator;
import stefan.business.objects.Order;
import stefan.business.objects.OrderItem;

public class MyTreeTableModel extends AbstractTreeTableModel 
{
	private MyTreeNode myroot=new MyTreeNode();
	
	public MyTreeTableModel(List<Order> order)
	{
            Collections.sort(order,new OrderOrderNumberComparator());
            for (Order o : order) {
                MyTreeNode node=new MyTreeNode(o);
                List<OrderItem> lista = o.getOrderitemsList();
                Collections.sort(lista,new OrderPositionComparator());
                for (OrderItem oi : lista) {
                    MyTreeNode orderItem=new MyTreeNode(oi);
                    node.getChildren().add(orderItem);
                }
                myroot.getChildren().add(node);
            }
		
	}

	public static final int INTERNAL_EXTERNAL_COLUMN_INDEX = 2;

	@Override
	public int getColumnCount() 
	{
		return 11;
	}
	
	@Override
	public String getColumnName( int column )
	{
		switch( column )
		{
		case 0: return "Broj Narudžbe";
                case 1: return "Firma";
				case 2: return "Interno/Vanjsko";
                case 3: return "Datum Narudžbe";
                case 4: return "Datum Isporuke";
		case 5: return "Pozicija";
		case 6: return "Naziv Nacrta";
                case 7: return "Broj Nacrta";
                case 8: return "Identitet Nacrta";
                case 9: return "Klasa Nacrta";
                case 10: return "Naručeno";
                case 11: return "Isporučeno";
		default: return "Unknown";
		}
	}

	@Override
	public Object getValueAt( Object node, int column ) 
	{		
		MyTreeNode treenode = ( MyTreeNode )node;
		String intExtVal = "";
		if (!treenode.getChildren().isEmpty())
			intExtVal = treenode.getIsBusinessPartnerExternal() ? "V" : "I";

		switch( column )
		{
		case 0: return treenode.getOrderNumber();
                case 1: return treenode.getBusinessPartnerName();
				case 2: return intExtVal;
                case 3: return treenode.getOrderDate();
                case 4: return treenode.getOrderShippingDate();
		case 5: return treenode.getPosition();
		case 6: return treenode.getDesignName();
                case 7: return treenode.getDesignNumber();
                case 8: return treenode.getDesignIdentity();
                case 9: return treenode.getDesignClass();
                case 10: return treenode.getQuantityOrdered();
                case 11: return treenode.getQuantityDelivered();
                            
		default: return "Unknown";
		}
	}

	@Override
	public Object getChild( Object node, int index ) 
	{
		MyTreeNode treenode = ( MyTreeNode )node;
		return treenode.getChildren().get( index );
	}

	@Override
	public int getChildCount( Object parent ) 
	{
		MyTreeNode treenode = ( MyTreeNode )parent;
		return treenode.getChildren().size();
	}

	@Override
	public int getIndexOfChild( Object parent, Object child ) 
	{
		MyTreeNode treenode = ( MyTreeNode )parent;
		for( int i=0; i>treenode.getChildren().size(); i++ )
		{
			if( treenode.getChildren().get( i ) == child )
			{
				return i;
			}
		}

		return 0;
	}
	
    @Override
	 public boolean isLeaf( Object node )
	 {
		 MyTreeNode treenode = ( MyTreeNode )node;
		 if( treenode.getChildren().size() > 0 )
		 {
			 return false;
		 }
		 return true;
	 }
	 
	 @Override
	 public Object getRoot()
	 {
		 return myroot;
	 }
}