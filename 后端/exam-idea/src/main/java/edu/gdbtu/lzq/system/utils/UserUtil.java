package edu.gdbtu.lzq.system.utils;

import com.alibaba.fastjson.JSON;
import edu.gdbtu.lzq.common.helper.JwtHelper;
import edu.gdbtu.lzq.common.utils.BeanUtil;
import edu.gdbtu.lzq.common.utils.WebUtil;
import edu.gdbtu.lzq.model.system.SysUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户信息获取工具类
 * @date 2023/4/27 15:53
 */
public class UserUtil {

    public static String getUserId() {
        HttpServletRequest request = WebUtil.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        String userId = JwtHelper.getUserIds(token);
        return userId;
    }

    public static SysUser getUserInfo() {
        String userId = getUserId();
        RedisTemplate redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
        String result = (String) redisTemplate.opsForValue().get(userId);
        if (result != null) {
            return JSON.parseObject(result, SysUser.class);
        }
        return null;
    }

    public static List<String> getDeptIds() {
        SysUser sysUser = getUserInfo();
        if (sysUser != null) {
            return sysUser.getDeptIds();
        } else {
            return null;
        }
    }

    public static String getDeptId() {
        SysUser sysUser = getUserInfo();
        if (sysUser != null) {
            return sysUser.getDeptId();
        } else {
            return null;
        }
    }
}
