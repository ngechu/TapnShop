package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import auth.models.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@Remote
@Stateless
public class UserBean {
    @PersistenceContext
    private EntityManager em;
    public User createAccount(User user)throws Exception{
        if(user==null)
            throw new Exception("No user details");
        if(StringUtils.isBlank(user.getEmail())||StringUtils.isBlank(user.getPassword()))
            throw new Exception("Invalid user details!!!");
        if(user.getId()==0 && StringUtils.isBlank(user.getConfirmPassword()))
            throw new Exception("Please provide Confirm password");
        if(user.getId()==0 && !user.getPassword().equals(user.getConfirmPassword()))
            throw new Exception("Password and Confirm password do not match");

        return em.merge(user);
    }
    @SuppressWarnings({"unchecked"})
    public User authenticate(User user) throws Exception {
        if(user==null||StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword()))
            throw new Exception("Invalid user details");
        List<User>users=em.createNamedQuery(User.USER_FIND_BY_EMAIL_PWD)
                .setParameter("email",user.getEmail())
                .setParameter("password",user.getPassword()).getResultList();
        if(users.isEmpty())
            throw new Exception("Invalid username and password");
        return users.get(0);
    }



}
