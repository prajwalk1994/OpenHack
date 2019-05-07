package edu.sjsu.cmpe275.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.Team;
import edu.sjsu.cmpe275.project.Entity.User;

@Repository
public interface TeamDao extends JpaRepository<Team, Integer>{
}
