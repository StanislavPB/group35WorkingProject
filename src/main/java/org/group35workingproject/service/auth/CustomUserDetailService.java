package org.group35workingproject.service.auth;

import org.group35workingproject.repository.ManagerRepository;
import org.group35workingproject.security.ManagerUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    public CustomUserDetailService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return managerRepository.findByManagerName(username)
                .map(ManagerUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Manager with name " + username + " not found"));
    }
}
