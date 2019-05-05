package edu.sjsu.cmpe275.project.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
//	@Query("SELECT a FROM user a where a.email=?1") 
	ArrayList<User> findUserByEmail(String email);
	
}
