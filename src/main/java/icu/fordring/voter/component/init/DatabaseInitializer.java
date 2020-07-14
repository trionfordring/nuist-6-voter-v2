package icu.fordring.voter.component.init;

import com.google.common.collect.Sets;
import icu.fordring.voter.component.user.UserRegister;
import icu.fordring.voter.dao.RoleDao;
import icu.fordring.voter.dao.TableBuilder;
import icu.fordring.voter.mapper.AuthorityMapper;
import icu.fordring.voter.mapper.DevMapper;
import icu.fordring.voter.mapper.RoleAuthorityMapper;
import icu.fordring.voter.mapper.RoleMapper;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.profile.RoleProfile;
import icu.fordring.voter.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 初始化数据库
 * @ClassName DatabaseInitializer
 * @Author fordring
 * @date 2020.07.07 21:20
 */
@Component
@Slf4j
public class DatabaseInitializer implements Initializer {
    @Resource
    private TableBuilder tableBuilder;
    @Resource
    private UserRegister userRegister;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleProfile roleProfile;
    @Override
    public void init() {
        resetDataBase();
    }

    @Override
    public void after() {
        String pwd = StringUtils.getRandomString(16);
        Role root = roleDao.getRoleByName(roleProfile.getRootRole());
        Role user = roleDao.getRoleByName(roleProfile.getUserRole());
        User rootUser = userRegister.register("root", pwd, Sets.newHashSet(root,user));
        log.info("初始化root用户完成\n" +
                "====================================================================\n" +
                "==  \n" +
                "==  初始化root账户完成\n" +
                "==    账户名:\t{}\n" +
                "==    密 码 :\t{}\n" +
                "==  \n" +
                "====================================================================",rootUser.getUsername(),pwd);
    }

    /**
     * @Author fordring
     * @Description  用于重置数据库
     * @Date 2020/7/7 22:18
     * @Param []
     * @return void
     **/
    public void resetDataBase(){
        long time = System.currentTimeMillis();
        log.warn("==========[现在开始重置数据库]==========");
        try{
            log.warn("  ==========[重置权限表]==========  ");
            tableBuilder.initAuthorityTable();
            log.warn("  ==========[重置角色表]==========  ");
            tableBuilder.initRoleTable();
            log.warn("  =====[重置[角色-权限]关联表]=====  ");
            tableBuilder.initRoleAuthority();
            log.warn("  ==========[重置用户表]==========  ");
            tableBuilder.initUserTable();
            log.warn("  ==========[重置头像表]==========  ");
            tableBuilder.initHeadResourceTable();
            log.warn("  =====[重置[用户-角色]关联表]=====  ");
            tableBuilder.initUserRoleTable();
            log.warn("  ==========[建立会话表]===========  ");
            tableBuilder.initSessionTable();
            log.warn("  ======[建立[remember-me]表]======");
            tableBuilder.initPersistentLoginTable();
            log.warn("  ==========[建立图片表]===========  ");
            tableBuilder.initImageTable();
            log.warn("  ========[建立图片评论表]=========  ");
            tableBuilder.initCommentTable();
            log.warn("  ========[建立图片点赞表]=========  ");
            tableBuilder.initLikeImageTable();
            log.warn("  ==========[建立板块表]===========  ");
            tableBuilder.initPlateTable();
        }catch (RuntimeException e){
            log.error("!=====================================!");
            log.error(" 重置数据库耗时:{}ms",System.currentTimeMillis()-time);
            log.error("!=========[  重置数据库失败  ]=========!");
            throw e;
        }
        log.warn("=======================================");
        log.warn(" 重置数据库耗时:{}ms",System.currentTimeMillis()-time);
        log.warn("==========[  重置数据库成功  ]==========");
    }


}
