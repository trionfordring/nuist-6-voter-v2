package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description user表数据库接口
 * @ClassName UserMapper
 * @Author fordring
 * @date 2020.07.03 21:05
 */
@Mapper
public interface UserMapper {
    /**
     * @Author fordring
     * @Description  通过id查找用户，如果用户不存在，返回null
     * @Date 2020/7/4 15:52
     * @Param [id]
     * @return icu.fordring.voter.pojo.User
     **/
    User selectById(@Param("id") String id);
    /**
     * @Author fordring
     * @Description  通过name查找用户
     * @Date 2020/7/5 15:09
     * @Param [name]
     * @return icu.fordring.voter.pojo.User
     **/
    User selectByName(@Param("name") String name);
    /**
     * @Author fordring
     * @Description  更新除了权限以外的用户信息
     * @Date 2020/7/5 14:01
     * @Param [user]
     * @return icu.fordring.voter.pojo.User
     **/
    int updateById(User user);
    /**
     * @Author fordring
     * @Description  向数据库添加一个用户（包括它的角色）
     * @Date 2020/7/8 12:27
     * @Param [user]
     * @return int
     **/
    int insert(User user);
    /**
     * @Author fordring
     * @Description  查询数据库中有多少个名为#{name}的用户
     * @Date 2020/7/6 15:36
     * @Param [name]
     * @return int
     **/
    int countByName(@Param("name") String name);
}
