package com.love.nchu.repository;

import com.love.nchu.demain.UserInfo;
import com.love.nchu.demain.editUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    UserInfo findUserInfoByUsername(String username);
    UserInfo findUserInfoByTel(String tel);
    UserInfo findUserInfoByEmail(String email);

    @Query("select username from UserInfo u where u.username = ?1")
    String isExistUserByUsername(String username);


    @Query("select username from UserInfo u where u.tel = ?1")
    String isExistUserByTel(String tel);


    @Query("select username from UserInfo u where u.email = ?1")
    String isExistUserByEmail(String email);

    @Modifying
    @Query("update UserInfo u set u.tel=:#{#editUserInfo.editTel},u.email=:#{#editUserInfo.editEmail},u.school=:#{#editUserInfo.editSchool},u.self_introduction=:#{#editUserInfo.editSelf_Introduction},u.research_direct=:#{#editUserInfo.editResearch_Direct},u.identity=:#{#editUserInfo.editIdentity} where u.username=:#{#editUserInfo.editUsername}")
    int updateUserInfo(@Param("editUserInfo") editUserInfo editUserInfo);


    @Query("select name from UserInfo u where u.username=?1")
    String getNameByUsername(String username);

    List<UserInfo> findUserInfoByIdentity(String identity);

    @Query("select u.username from UserInfo u where u.identity='student'")
    List<String> getAllStudentUsername();


    @Transactional
    @Modifying
    @Query("delete from UserInfo u where u.username=?1")
    int deleteUserInfoByUsername(String username);
}
