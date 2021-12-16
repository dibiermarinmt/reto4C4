
// Mongo package
package com.zorillo.zorrillo.repository.mongo;

import java.util.List;

// Zorrillo imports
import com.zorillo.zorrillo.model.Fragance; // Fragance model

// Mongo Repository
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FraganceMongoRepository extends MongoRepository<Fragance, String> {

    List<Fragance> findByPrice(Double price);

    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<Fragance> findByDescriptionLike(String description);

    /* 
    //Examples

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by email and password
    Optional<User> findByEmailAndPassword(String email, String password);

    /**List<Fragance> findAllByPriceLessThanEqual(Float price);

    @Query("{price: {$lte:?0}}")
    public List<Fragance> findFraganceByPrice(Float price);
    */
}
