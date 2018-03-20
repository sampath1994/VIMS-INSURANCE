package com.vims_v3.repository;

import com.vims_v3.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by promod on 12/1/2017.
 */
@Repository
public interface UsersRepo extends CrudRepository<User,Integer>{
    @Query(value="SELECT u FROM User u WHERE u.name=?1 and u.enabled = 1")
      public User findUserByUsername(String name);

    @Query(value="SELECT u FROM User u WHERE u.firstname=?1")
    List<User> findUsersByFirstname(String firstname);

    @Query(value="SELECT u FROM User u WHERE u.lastname=?1")
    List<User> findUsersByLastname(String lastname);

    @Query(value="SELECT u FROM User u WHERE u.role=?1")
    List<User> findUsersByRole(String role);

    @Query(value="SELECT u FROM User u WHERE u.name=?1")
    public User findUserByOnlyUsername(String name);
}


