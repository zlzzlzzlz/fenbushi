package cn.edu.neusoft.Service;

import cn.edu.neusoft.Dao.ProductDao;
import cn.edu.neusoft.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ProductService {
    Product getProductById(Integer pid);
}
