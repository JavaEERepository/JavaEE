package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestUserDao.class, TestUserInformationDao.class, TestUserService.class })
public class AllTests {

}
