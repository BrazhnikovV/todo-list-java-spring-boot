package ru.brazhnikov.todolist.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import ru.brazhnikov.todolist.persistence.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import ru.brazhnikov.todolist.persistence.entity.Privilege;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * AuthorityHelper - класс выбора данных из списков ролей, привилегий, полномочия
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.utils
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
public class AuthorityHelper {

    /**
     * getAuthorities - получить полномочия
     * @param roles - список ролей
     * @return Collection<? extends GrantedAuthority>
     */
    public static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles ) {
        return getGrantedAuthorities( getPrivileges( roles ) );
    }

    /**
     * getPrivileges - получить привилегии
     * @param roles - список ролей
     * @return List<String>
     */
    private static List<String> getPrivileges(Collection<Role> roles ) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for ( Role role : roles ) {
            collection.addAll( role.getPrivileges() );
        }
        for ( Privilege item : collection ) {
            privileges.add( item.getName() );
        }
        return privileges;
    }

    /**
     * getGrantedAuthorities - получить предоставленные полномочия
     * @param privileges - список привилегий
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> getGrantedAuthorities( List<String> privileges ) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for ( String privilege : privileges ) {
            authorities.add( new SimpleGrantedAuthority( privilege ) );
        }
        return authorities;
    }
}
