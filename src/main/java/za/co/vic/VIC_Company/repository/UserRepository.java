package za.co.vic.VIC_Company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.vic.VIC_Company.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
