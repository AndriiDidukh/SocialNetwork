package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.UserPage;

public interface UserPageRepository extends JpaRepository<UserPage, Integer>, JpaSpecificationExecutor<UserPage> {

	@Query("SELECT u FROM UserPage u WHERE u.id=:id")
	UserPage findOneCountryInited(@Param("id") int id);

	// @Query("SELECT u FROM UserPage u LEFT JOIN FETCH u.gender WHERE u.id=:id")
	// UserPage findOneSexInited(@Param("id") int id);

	// @Query("SELECT u FROM UserPage u LEFT JOIN FETCH u.dayOfBirth WHERE u.id=:id")
	// UserPage findOneDayInited(@Param("id") int id);
	//
	// @Query("SELECT u FROM UserPage u LEFT JOIN FETCH u.mounthOfBirth WHERE u.id=:id")
	// UserPage findOneMounthInited(@Param("id") int id);

	// @Query("SELECT u FROM UserPage u LEFT JOIN FETCH u.yearOfBirth WHERE u.id=:id")
	// UserPage findOneYearInited(@Param("id") int id);

	UserPage findByLogin(String login);

}
