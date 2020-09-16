package auth.models;
import com.tracom.base.BaseEntity;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name=User.USER_FIND_BY_EMAIL_PWD,query="SELECT u FROM User u WHERE u.email=:email and u.password=:pwd")
})
@Entity
@Table(name = "users")
public
class User extends BaseEntity {

    @Transient
    public static final String USER_FIND_BY_EMAIL_PWD="User.findByEmailPwd";

    @Column
    private  String name;
    @Column
    private  String email;
    @Column
    private String password;
    @Transient
    private String confirmPassword;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }





}
