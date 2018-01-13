/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import stefan.business.objects.Order;
import stefan.business.objects.OrderItem;

public class MyTreeNode
{

    private Integer orderId;
    private String orderNumber;
    private String orderDate;
    private String position;
    private Integer designId;
    private String designName;
    private String designNumber;
    private String designIdentity;
    private String designClass;
    private Integer orderItemId;
    private Integer quantityOrdered;
    private Integer quantityDelivered;
    private List<MyTreeNode> children = new ArrayList<MyTreeNode>();

	public MyTreeNode() 
	{
	}
	
	public MyTreeNode(OrderItem item) 
	{
		orderId=item.getOrder().getIdOrder();
                orderNumber=item.getOrder().getOrderNumber();
                position=item.getPosition();
                designId=item.getDesignId();
                designName=item.getDesign().getName();
                designNumber=item.getDesign().getDesignNumber();
                designIdentity=item.getDesign().getDesignIdentity();
                designClass=item.getDesign().getClassMark();
                orderItemId=item.getIdOrderItems();
                quantityOrdered=item.getQuantityOrdered();
                quantityDelivered=item.getQuantityDelivered();
                SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
                orderDate=f.format(item.getOrder().getDate());
	}
	
        public MyTreeNode(Order item) 
	{
            orderId=item.getIdOrder();
            orderNumber=item.getOrderNumber();
            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
            orderDate=f.format(item.getDate());
        }
	public List<MyTreeNode> getChildren() 
	{
		return children;
	}
	
    @Override
	public String toString()
	{
		return "MyTreeNode";
	}

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the designId
     */
    public Integer getDesignId() {
        return designId;
    }

    /**
     * @param designId the designId to set
     */
    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    /**
     * @return the designName
     */
    public String getDesignName() {
        return designName;
    }

    /**
     * @param designName the designName to set
     */
    public void setDesignName(String designName) {
        this.designName = designName;
    }

    /**
     * @return the designNumber
     */
    public String getDesignNumber() {
        return designNumber;
    }

    /**
     * @param designNumber the designNumber to set
     */
    public void setDesignNumber(String designNumber) {
        this.designNumber = designNumber;
    }

    /**
     * @return the designIdentity
     */
    public String getDesignIdentity() {
        return designIdentity;
    }

    /**
     * @param designIdentity the designIdentity to set
     */
    public void setDesignIdentity(String designIdentity) {
        this.designIdentity = designIdentity;
    }

    /**
     * @return the designClass
     */
    public String getDesignClass() {
        return designClass;
    }

    /**
     * @param designClass the designClass to set
     */
    public void setDesignClass(String designClass) {
        this.designClass = designClass;
    }

    /**
     * @return the orderItemId
     */
    public Integer getOrderItemId() {
        return orderItemId;
    }

    /**
     * @param orderItemId the orderItemId to set
     */
    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * @return the quantityOrdered
     */
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * @param quantityOrdered the quantityOrdered to set
     */
    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
     * @return the quantityDelivered
     */
    public Integer getQuantityDelivered() {
        return quantityDelivered;
    }

    /**
     * @param quantityDelivered the quantityDelivered to set
     */
    public void setQuantityDelivered(Integer quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<MyTreeNode> children) {
        this.children = children;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}