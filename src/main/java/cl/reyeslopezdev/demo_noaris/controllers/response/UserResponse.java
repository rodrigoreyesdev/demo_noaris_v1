package cl.reyeslopezdev.demo_noaris.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class UserResponse {
    UUID id;
    Date created;
    Date lastModified;
    Date lastLogin;
    Boolean isActive;
    UUID token;

    public UserResponse (UUID id,Date created,Date lastModified,Date lastLogin,Boolean isActive,UUID token){
        this.id =id;
        this.created=created;
        this.lastModified=lastModified;
        this.lastLogin=lastLogin;
        this.isActive=isActive;
        this.token=token;
    }
}
