package com.rbevans;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpSession;

/**
 *
 * @author evansrb1
 */
public class OrderManager {

    private Vector<Order> orders = new Vector<Order>();
    private int orderNumber = 0;
    private static final String ORDER_ID = "orderID";
    private Object lock = new Object();

    public OrderManager() {
    }
    
    public final int cartSize (HttpSession session) {
        return getOrder(session).size();
    }
    
    public final String getItem(int i, HttpSession session) {
        return getOrder(session).getItem(i);
    }
    
    public final Order getOrder(HttpSession session) {
        Object orderNumObj = session.getAttribute(ORDER_ID);
        if (orderNumObj != null && orderNumObj instanceof Integer) {
            int number = ((Integer) orderNumObj).intValue();
            Order order = getOrder(number);
            return order;
        } else {
            return null;
        }
    }
    
    private final Order getOrder(int orderNum) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).hasOrderNum(orderNum)) {
                return orders.get(i);
            }
        }
        return null;
    }

    private Order makeOrder(int possibleOrderNumber, HttpSession session) {
        Order order = new Order(possibleOrderNumber);
        System.out.println("make order: setting orderNumber in session to " + possibleOrderNumber);
        session.setAttribute(ORDER_ID, new Integer(possibleOrderNumber));
        orders.add(order);
        orderNumber++;
        return order;
    }
    
    public void putInCart(String item, HttpSession session) {
        synchronized(lock) {
            int possibleOrderNumber = orderNumber;
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }
            Order order = null;
            // first get or set up cart and order number
            if (item != null && item.length() > 0) {
                Object orderNumObj = session.getAttribute(ORDER_ID);
                // both orderNumObj = null for both
                if (orderNumObj != null && orderNumObj instanceof Integer) {
                    possibleOrderNumber = ((Integer) orderNumObj).intValue();
                    order = getOrder(possibleOrderNumber);
                    if (order == null) {
                        // make a new number, and increment the number
                        order = makeOrder(possibleOrderNumber, session);
                    }
                } else {
                    // so, we'll make an order object for each
                    order = makeOrder(possibleOrderNumber, session);
                }
            }
            order.add(item);
        }
    }
}
