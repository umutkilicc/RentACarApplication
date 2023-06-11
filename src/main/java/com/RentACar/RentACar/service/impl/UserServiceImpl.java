package com.RentACar.RentACar.service.impl;

import com.RentACar.RentACar.common.service.impl.BaseService;
import com.RentACar.RentACar.dto.UserDto;
import com.RentACar.RentACar.entity.Role;
import com.RentACar.RentACar.entity.User;
import com.RentACar.RentACar.repository.RoleRepository;
import com.RentACar.RentACar.repository.UserRepository;
import com.RentACar.RentACar.service.UserService;
import com.RentACar.RentACar.service.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService<UserRepository, UserMapper, User, UserDto> implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    protected UserServiceImpl(UserMapper mapper, UserRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper userMapper) {
        super(mapper, repository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    /**
     * @param UserDto
     * @return
     */
    @Transactional
    public UserDto saveUser(UserDto UserDto) {

        return super.save(UserDto);
    }

    @Transactional
    public List<UserDto> saveAllUsers(List<UserDto> UserDtoList) {

        return super.saveAll(UserDtoList);
    }

    /**
     * @return
     */
    @Override
    public List<UserDto> findAll() {

        return super.findAll();
    }

    /**
     * @param uuid
     * @return
     */
    @Override
    public UserDto findById(UUID uuid) {

        return super.findById(uuid);
    }

    /**
     * @param userName
     * @return
     */
    @Override
    public UserDto findByUsername(String userName) {

        return mapper.entityToDto(userRepository.findByUsernameIgnoreCase(userName).orElse(null));
    }

    /**
     * @param UserDto
     */
    @Transactional
    @Override
    public void delete(UserDto UserDto) {

        super.delete(UserDto);
    }

    /**
     * @param uuid
     */
    @Transactional
    @Override
    public void deleteById(UUID uuid) {

        super.deleteById(uuid);
    }

    /**
     * @param UserDtos
     */
    @Transactional
    @Override
    public void deleteAll(List<UserDto> UserDtos) {

        super.deleteAll(UserDtos);
    }

    @Override
    @Transactional
    public void makePayment(int spent) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsernameIgnoreCase(auth.getName())
                .orElseThrow(() -> new IllegalStateException("User doesn't exist!"));

        user.setSpent(user.getSpent() + spent);
//        super.save(mapper.entityToDto(user));
        userRepository.save(user);
    }

    @Override
    public void registerUser(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto = super.save(userDto);
        roleRepository.save(new Role(mapper.dtoToEntity(userDto)));
    }

    @Override
    public void makeAdmin(UUID id) {

        roleRepository.save(new Role(id, "ADMIN"));
    }

    @Override
    public void removeAdmin(UUID id) {

        roleRepository.deleteById(id);
    }

    @Override
    public void enableUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User does not exist"));
        user.setEnable(true);
        userRepository.save(user);
    }

    @Override
    public void disableUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User does not exist"));
        user.setEnable(false);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameIgnoreCase(username).orElse(null);

        if (user == null)
            throw new UsernameNotFoundException("Username not found.");

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        for (Role roles : user.getRoles())
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }
}
