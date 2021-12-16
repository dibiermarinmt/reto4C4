// Mongo package
package com.zorillo.zorrillo.repository.mongo;

import java.util.Date;
import java.util.List;

// Zorrillo imports
import com.zorillo.zorrillo.model.Order; // User model

// Mongo Repository
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<Order, Integer> {
    List<Order> findBySalesManZone(String zone);
    List<Order> findBySalesManId(Integer id);
    List<Order> findByStatusAndSalesManId(String status, Integer id);

    List<Order> findByStatus(String status);
    List<Order> findByRegisterDay(Date registerDay);
    List<Order> findByRegisterDayAndSalesManId(Date registerDay, Integer id);

}
