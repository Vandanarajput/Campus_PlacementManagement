package com.PlacementManagementSystem.Placement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlacementManagementSystem.Placement.model.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Long> {

    @Query("from User where email = :email and password = :password")
    User LoginUser(@Param("email") String email, @Param("password") String password);

    User findByEmail(String email);

    //  NEW METHOD: To fetch only students
    List<User> findByRole(String role);

    long countByRole(String role);

}
