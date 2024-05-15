package com.Lnn.util;

import com.Lnn.domain.entity.User;

public class LoginUserUtil {

    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();


    public static void setUser(User user) {
        threadLocal.set(user);
    }


    public static User getUser() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }
}
