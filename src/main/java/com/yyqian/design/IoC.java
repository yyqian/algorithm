package com.yyqian.design;

import java.util.HashMap;
import java.util.Map;

public class IoC {
    public static class Container {
        private  static Map<Class, Object> dict = new HashMap<>();

        public static void register(Class clazz, Object object) {
            dict.put(clazz, object);
        }

        @SuppressWarnings("unchecked")
        public static <T> T getBean(Class clazz) {
            return (T) dict.get(clazz);
        }
    }

    public interface UserDao {
        int count();
    }

    public static class UserDaoImpl implements UserDao {
        @Override
        public int count() {
            return 9;
        }
    }

    public static class UserService {
        private UserDao userDao = Container.getBean(UserDao.class);
        public int count() {
            return userDao.count();
        }
    }

    public static void main(String[] args) {
        // init
        Container.register(UserDao.class, new UserDaoImpl());
        Container.register(UserService.class, new UserService());
        // use
        UserService userService = Container.getBean(UserService.class);
        System.out.println(userService.count());
    }
}
