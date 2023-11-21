package com.works.services;

import com.works.entities.Customer;
import com.works.entities.Role;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements UserDetailsService {

    final CustomerRepository customerRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase( username );
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new User(
                    customer.getUsername(),
                    customer.getPassword(),
                    customer.getEnable(),
                    true,
                    true,
                    true,
                    parseRole(customer.getRoles())
            );
        }
        throw new UsernameNotFoundException("Username not found!");
    }

    private Collection<? extends GrantedAuthority> parseRole(List<Role> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        for ( Role item : roles ) {
            list.add( new SimpleGrantedAuthority( item.getName() ));
        }
        return list;
    }

    public ResponseEntity register(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(customer.getUsername());
        if (optionalCustomer.isPresent()) {
            return new ResponseEntity("Username Fail!", HttpStatus.BAD_REQUEST);
        }else {
            customer.setPassword( passwordEncoder.encode(customer.getPassword() ) );
            customerRepository.save(customer);
            return new ResponseEntity(customer, HttpStatus.OK);
        }
    }


}
