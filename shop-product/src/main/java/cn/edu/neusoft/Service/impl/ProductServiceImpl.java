package cn.edu.neusoft.Service.impl;

import cn.edu.neusoft.Dao.ProductDao;
import cn.edu.neusoft.Service.ProductService;
import cn.edu.neusoft.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//ProductServiceImpl
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer pid) {
        Product product = productDao.selectById(pid);
        return product;
    }
}
