package com.github.paohaijiao.test.xml;

import com.github.paohaijiao.test.xml.model.CreateResult;
import com.github.paohaijiao.test.xml.model.User;
import com.github.paohaijiao.xml.param.Param;

public interface UserApi {
    User getUserById(@Param("id") String id, @Param("token") String token);

    CreateResult createUser(@Param("userJson") String userJson, @Param("token") String token);

    Boolean deleteUser(@Param("id") String id, @Param("token") String token);
}
