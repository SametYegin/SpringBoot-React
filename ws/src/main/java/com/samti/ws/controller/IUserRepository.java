package com.samti.ws.controller;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	@Query("select u from User u where u.email = :email")
	User findByEmail(@Param("email") String email); //name olarak springdata otomatik query yazmıyor.
	//queryi elle yazınca bile data gelmiyor. Bu sorunu araştır.
	//(email için unique olma özelliği sağlandı. signup için çözüldü. login için sorun devam ediyor.)
	
    

}
