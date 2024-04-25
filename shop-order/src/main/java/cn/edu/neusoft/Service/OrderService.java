package cn.edu.neusoft.Service;

import cn.edu.neusoft.model.Order;

public interface OrderService {
    void addOrder(Order order);

    Order queryOrderById(Long oid);
    void updateOrderStatus(Long oid);

}