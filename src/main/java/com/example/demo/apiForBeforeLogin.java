package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;

@RestController
@CrossOrigin
public class apiForBeforeLogin {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Autowired
	private MyUserDetailsService userDetailsService;

	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private logindetailsRepository loginrep;
	
	@Autowired
	private qadetailsRepository qarep;
	
	@RequestMapping("/")
	public String getHello() {
		return "HELLO";
	}
	
	@RequestMapping("/login")
	public String checklogindetails(@RequestParam String name,@RequestParam String password) throws Exception {
		
		int temp=0;
		List<logindetails> logindet =loginrep.findAll();
		logindetails user=null;
		
		for(logindetails l:logindet) {
			if((l.getName().equals(name))&&(l.getPassword().equals(password))) {
				user=l;
				temp=1;
				break;
				
			}
		}
		
		if(temp==0) {
			return "F";
		}
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getName());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		
		return jwt;
	}
	
	@RequestMapping("/userexist")
	public boolean finduserexist(@RequestParam String name) {
		
		List<logindetails> logindet =loginrep.findAll();
		for(logindetails l:logindet) {
			if(l.getName().equals(name)){
				System.out.println("False");
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(path="/signin" ,method=RequestMethod.POST)
	public String setnewuser(@RequestBody logindetails user) throws Exception {
		
		loginrep.save(user);
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getName());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		
		return jwt;
		
	}

}
