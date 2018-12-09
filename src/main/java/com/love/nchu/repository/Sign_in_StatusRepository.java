package com.love.nchu.repository;

import com.love.nchu.demain.Sign_in_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface Sign_in_StatusRepository extends JpaRepository<Sign_in_Status,String> {

    @Query("select t from  Sign_in_Status  t where t.username=?1 and t.date=?2")
    Sign_in_Status findSign_in_StatusByUsernameAndDateEquals(String username, String date);

    @Modifying
    @Query("update Sign_in_Status t set t=?1 where t.username=?2 and t.date=?3 ")
    int singin(String time,String username,String date);

    @Query("select t from  Sign_in_Status  t where  t.date=?1")
    List<Sign_in_Status> getSign_in_StatusByDate(String date);



   // @Query(value="select new weekShow( s.username,(select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?1), (select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?2), (select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?3),(select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?4),(select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?5),(select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?6), (select c from Sign_in_Status s1 where s1.username = s.username and s1.date=?7)) from Sign_in_Status s  group by s.username")
  //  List<weekShow> getWeekShow(String monday,String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday);
}
//,nativeQuery = true