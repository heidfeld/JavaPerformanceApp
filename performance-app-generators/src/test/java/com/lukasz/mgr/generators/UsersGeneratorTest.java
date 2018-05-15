package com.lukasz.mgr.generators;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.lukasz.mgr.api.users.User;
import com.lukasz.mgr.generators.control.users.UsersGenerator;

/**
 * Created by Lukasz Karmanski
 */
public class UsersGeneratorTest {

    @Test
    public void usersGeneratorTest() {
        List<User> users = UsersGenerator.generate(10);
        User firstUser = users.get(0);

        Assert.assertNotNull(users);
        Assert.assertEquals(10, users.size());

        Assert.assertNotNull(firstUser.getName());
        Assert.assertNotNull(firstUser.getSurname());
        Assert.assertNotNull(firstUser.getAge());
        Assert.assertNotNull(firstUser.getUuid());
        Assert.assertNotNull(firstUser.getBirthDate());

        Assert.assertTrue(firstUser.getAge() < 200);
    }

}
