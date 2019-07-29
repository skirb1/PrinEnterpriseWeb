package com.rbevans;

import java.util.ArrayList;

/**
 *
 * @author evansrb1
 */
public class Order {
    private final int orderNumber;
    private final ArrayList<String> items = new ArrayList<String>();
    
    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    
    public void add(String item) {
        items.add(item);
    }
    
    public int size() {
        return items.size();
    }
    
    public String getItem(int index) {
        return items.get(index);
    }
    
    public boolean hasOrderNum(int orderNum) {
        return orderNumber == orderNum;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + orderNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderNumber != other.orderNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", items=" + items + "]";
	}
}
