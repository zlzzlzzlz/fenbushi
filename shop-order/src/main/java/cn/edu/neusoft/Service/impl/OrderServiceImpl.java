package cn.edu.neusoft.Service.impl;

import cn.edu.neusoft.Dao.OrderDao;
import cn.edu.neusoft.Service.OrderService;
import cn.edu.neusoft.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void addOrder(Order order) {
        orderDao.insert(order);
    }
    public Order queryOrderById(Long oid) {
        return orderDao.selectById(oid);
    }
    @Override
    public void updateOrderStatus(Long oid) {
        Order order = new Order();
        order.setOid(oid);
        order.setStatus(1);
        orderDao.updateById(order);
    }

}