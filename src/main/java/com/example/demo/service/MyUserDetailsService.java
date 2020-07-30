package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.logindetails;
import com.example.demo.logindetailsRepository;


@Service 
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private logindetailsRepository loginrep;
	
	public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{
		
		logindetails user=null;
		List<logindetails> logindet =loginrep.findAll();
		for(logindetails l:logindet) {
			if(l.getName().equals(userName)){
				user=l;
				break;
			}
		}
		
		return new User(user.getName(),user.getPassword(),new ArrayList<>());
	}

}