package ru.brazhnikov.todolist.config;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import ru.brazhnikov.todolist.persistence.entity.Authority;
import ru.brazhnikov.todolist.persistence.entity.Role;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.context.ApplicationListener;
import ru.brazhnikov.todolist.persistence.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.brazhnikov.todolist.persistence.repositories.AuthorityRepository;
import ru.brazhnikov.todolist.persistence.repositories.RoleRepository;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import ru.brazhnikov.todolist.persistence.repositories.PrivilegeRepository;

/**
 * InitialDataLoader - класс ранней настройки привилегий и ролей в системе,
 * если требуемых ролей или привелегий не существует, то они будут созданы
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.config
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * @access private
     * @var String defaultName - имя пользователя который создается по умолчанию
     */
    private String defaultName = "admin";

    /**
     * @access private
     * @var UserRepository userRepository - репозиторий пользователя
     */
    @Autowired
    private UserRepository userRepository;

    /**
     *  @access private
     *  @var RoleRepository roleRepository - репозиторий роли пользователя
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     *  @access private
     *  @var PrivilegeRepository privilegeRepository - репозиторий привелегий
     */
    @Autowired
    private PrivilegeRepository privilegeRepository;

    /**
     *  @access private
     *  @var BCryptPasswordEncoder passwordEncoder - кодировщик пароля(должен совпадать
     *  с бином объявленном в классе TodoListApplication)
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *  @access private
     *  @var BCryptPasswordEncoder passwordEncoder - кодировщик пароля(должен совпадать
     *  с бином объявленном в классе TodoListApplication)
     */
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * onApplicationEvent -
     * @param event
     */
    @Override
    @Transactional
    public void onApplicationEvent( ContextRefreshedEvent event ) {

        Optional<User> optionalUser = this.userRepository.getUserByUsername("admin");
        if ( !optionalUser.isPresent() ) {

            Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
            List<Privilege> adminPrivileges = Arrays.asList( readPrivilege, writePrivilege );

            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges );
            createRoleIfNotFound("ROLE_USER", Arrays.asList( readPrivilege ) );
            createRoleIfNotFound("ROLE_MANAGER", Arrays.asList( readPrivilege ) );

            Role adminRole = this.roleRepository.findByName("ROLE_ADMIN" );
            User user = this.createDefaultUserIfNotFound( adminRole );
            this.createAuthorityIfNotFound( user.getUsername(), adminRole.getName() );
        }
    }

    /**
     * createPrivilegeIfNotFound - добавить привелегию если такой не существует
     * @param name - название привелегии
     * @return Privilege
     */
    @Transactional
    public Privilege createPrivilegeIfNotFound( String name ) {

        Privilege privilege = this.privilegeRepository.findByName( name );
        if ( privilege == null ) {
             privilege = new Privilege( name );
             this.privilegeRepository.save( privilege );
        }
        return privilege;
    }

    /**
     * createRoleIfNotFound - добавить роль если такой не существует
     * @param name - название привелегии
     * @return Role
     */
    @Transactional
    public Role createRoleIfNotFound( String name, Collection<Privilege> privileges ) {

        Role role = this.roleRepository.findByName( name );
        if ( role == null ) {
             role = new Role( name );
             role.setPrivileges( privileges );
             this.roleRepository.save( role );
        }
        return role;
    }

    /**
     * createAuthorityIfNotFound - добавить
     * @param name - имя пользователя
     * @param role - название роли
     * @return Authority
     */
    @Transactional
    public Authority createAuthorityIfNotFound( String name, String role ) {

        Authority authority = this.authorityRepository.findByUsername( name );
        if ( authority == null ) {
            authority = new Authority( name, role );
            this.authorityRepository.save( authority );
        }
        return authority;
    }

    /**
     * createDefaultUserIfNotFound - добавить
     * @return User
     * @param adminRole
     */
    @Transactional
    public User createDefaultUserIfNotFound(Role adminRole) {

        User user = new User();
        user.setUsername( this.defaultName );
        user.setFirstName( "Vasya" );
        user.setLastName( "Brazhnikov" );
        user.setPassword( this.passwordEncoder.encode( this.defaultName ) );
        user.setEmail( "admin@test.com" );
        user.setRoles( Arrays.asList( adminRole ) );
        user.setEnabled( true );
        this.userRepository.save(user);

        return user;
    }
}
