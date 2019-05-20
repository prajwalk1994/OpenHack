package edu.sjsu.cmpe275.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.Hackathon;

@Repository
public interface HackathonDao extends JpaRepository<Hackathon, Integer>{
	
	@Query(value = "select * from hackathon h where h.status = 'Final'", nativeQuery = true)
	public List<Hackathon> findAllFinalHackathons();
}
