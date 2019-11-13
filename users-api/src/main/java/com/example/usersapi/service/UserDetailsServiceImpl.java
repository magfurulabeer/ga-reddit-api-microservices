//package com.example.usersapi.service;
//
//import com.example.usersapi.model.User;
//import com.example.usersapi.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.getUserByUsername(username);
//
//        if(user==null)
//            throw new UsernameNotFoundException("Unknown user: " +username);
//
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()),
//                true, true, true, true, authorities);
//    }
//}
