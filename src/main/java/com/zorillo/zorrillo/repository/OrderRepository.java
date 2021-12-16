// Repostory Package
package com.zorillo.zorrillo.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// Utils imports
import java.util.List;
import java.util.Optional;

// Zorrillo imports
import com.zorillo.zorrillo.model.Order;
import com.zorillo.zorrillo.repository.mongo.OrderMongoRepository;

// Spring Boot
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

// Sping Boot repository annotation
@Repository
public class OrderRepository {

    // Sping Boot autowired annotation for instances 
    @Autowired
    OrderMongoRepository mongo;

    @Autowired
    private MongoTemplate mongoTemplate;

    // Store or update an Order
    public Order saveOrder(Order order) {
        return mongo.save(order);
    }

    // Return a list with all Orders
    public List<Order> findAllOrders() {
        return (List<Order>) mongo.findAll();
    }

    // Find order by id
    public Optional<Order> findOrderById(Integer id){
       return mongo.findById(id);
    }

    // Find orders by salesman zone
    public List<Order> findOrdersBySalesManZone(String zone) {
        return mongo.findBySalesManZone(zone);
    }

    // Find orders by salesman id
    public List<Order> findOrdersBySalesManId(Integer id) {
        return mongo.findBySalesManId(id);
    }

    // Find orders by state and salesman id
    public List<Order> findOrdersByStatusAndSalesManId(String status, Integer id) {
        return mongo.findByStatusAndSalesManId(status, id);
    }

    /*Find orders by RegisterDay
    public List<Order> findOrdersByRegisterDay(Date registerDay) {
        return mongo.findByRegisterDay(registerDay);
    }*/

    /*Find orders by status
    public List<Order> findOrdersByStatus(String status) {
        return mongo.findByStatus(status);
    }*/

    // Find orders by register date and salesman id
    public List<Order> findOrdersByRegisterDayAndSalesManId(String registerDay, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(registerDay, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(registerDay, dtf).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(id);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }   

    /* Delete user by id
    public void deleteUser(User user) {
        mongo.delete(user);
    }*/
}
