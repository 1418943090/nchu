//package com.love.nchu.repository;
//
//import com.love.nchu.vo.PerVo;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface PermissionRepository extends JpaRepository<PerVo,String> {
//
//
//    @Query("select  new PerVo(u.username,u.registry_date,u1.accountNonLocked) from UserInfo u ,User u1 where u.username = u1.username and u1.role='student' and u1.enabled='1' ")
//    List<PerVo> getPerVo();
//
//}
