package edu.sjsu.cmpe275.project.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;

	public User addUser(User user) {
		return this.userDao.save(user);
	}

	public Optional<User> getUser(int id) {
		return this.userDao.findById(id);
	}					

	public void deleteUser(int id) {
		this.userDao.deleteById(id);
	}
	
//	public User updateUser(User user) {
//		return this.userDao.merge(user);
//	}
}	
