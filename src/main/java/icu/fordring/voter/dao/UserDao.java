package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.UserMapper;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName UserDao
 * @Author fordring
 * @date 2020.07.03 20:58
 */
@Slf4j
@Component
public class UserDao {
    @Resource
    protected UserMapper userMapper;
    public User findUserByUsername(String name){
        Long t = System.currentTimeMillis();
        User user = userMapper.selectByName(name);
        log.info("数据库查找用户[username='{}'])    +{}ms",name,System.currentTimeMillis()-t);
        if(user==null)log.error("数据库查找用户[username='{}'])失败，用户不存在",name);
        return user;
    }

    /**
     * @Author fordring
     * @Description  插入一个用户的完整信息
     * @Date 2020/7/8 12:31
     * @Param [user]
     * @return void
     **/
    @Transactional
    public void insertUser(User user){
        Long t = System.currentTimeMillis();
        if(user.getId()==null)user.setId(StringUtils.UUID());
        int ans = userMapper.insert(user);
        log.info("数据库新建用户[username='{}'])    +{}ms",user.getUsername(),System.currentTimeMillis()-t);
        if(ans!=1){
            log.error("数据库新建用户[username='{}'])失败",user.getUsername());
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"新建用户失败");
        }
    }
    
    /**
     * @Author fordring
     * @Description  判断用户是否存在
     * @Date 2020/7/6 17:18
     * @Param [name]
     * @return boolean
     **/
    public boolean exist(String name){
        return userMapper.countByName(name)>0;
    }
}
