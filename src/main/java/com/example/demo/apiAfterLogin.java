package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JwtUtil;

@RestController
@CrossOrigin
public class apiAfterLogin {
	
	
	
	@Autowired
	private logindetailsRepository loginrep;
	
	@Autowired
	private qadetailsRepository qarep;
	
	@Autowired 
	private questiondetailsRepository questionrep;
	
	@RequestMapping("/welcome")
	public String welcome() {	
		return "Welcome";
	}
	
	
	
	@RequestMapping(path="/login/addqa",method=RequestMethod.GET)
	public qadetails addqa() {
		
		String question="What is quora?";
		String answeredby="akila";
		String ans="Quora is a website where users post questions via Threads and other Quora users answer them – it’s essentially Yahoo Answers and an Internet forum rolled into a social network,says Ryan Stewart on Webris. It’s been open to the public since 2010 and reports having “over 200 million monthly unique visitors.";
		List<answerdetails> answers = new ArrayList<answerdetails>();
		
		answerdetails ad=new answerdetails(answeredby,ans);
		answers.add(ad);
		
		answeredby="Mohan";
		ans="Quora is a question-answer based forum. It is not your father's almanac. She's not quick with the pro- quos.\n" + 
				"Quora has been recently haunted by profiles that do not really exist. If you notice you're answering questions and the profile is empty- well just deal with it man.\n" + 
				"Quora is monitored by R2D2 looking asshole spam spotting bots. They're on the prowl full time. Day and night I'm afraid. The bots don't sleep. They hand out Quora's parking too honest tickets. It's known around these parts as SPAM.\n" + 
				"Quora is a platform for all kinds of great folks. Some are even famous. Of course no one on Quora is actually the person that they claim they are. Get used to it.";
		
		ad=new answerdetails(answeredby,ans);
		answers.add(ad);
		
		String questionedby="akila";
		qadetails qa=new qadetails(question,answers,questionedby);
		qarep.save(qa);
		
		return qa;
	}
	
	@RequestMapping(path="/addquestion" ,method=RequestMethod.POST)
	public String addquestion(@RequestBody questiondetails question) {
		
		questionrep.save(question);
		return "question";
	}
	
	@RequestMapping("/login/qa")
	public List<qadetails> getqa() {
		
		List<qadetails> q=qarep.findAll();
		
		return q;
	}
	
	
	@RequestMapping("/getanswer")
	public List<answerdetails> getanswerforaquestion(@RequestParam String question){
		String qu=question.toLowerCase();
		String qu1;
		
		List<answerdetails> answers=new ArrayList<answerdetails>();
		List<qadetails> q=qarep.findAll();
		for(qadetails questions:q) {
			qu1=(questions.getQuestion()).toLowerCase();
			if(qu.equals(qu1)) {
				System.out.println(questions.getAnswers());
				answers=questions.getAnswers();
				break;
			}
		}
		
		return answers;
	}
	
	/*@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}*/
	
	/*@RequestMapping("/login")
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
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Autowired
	private MyUserDetailsService userDetailsService;

	
	@Autowired
	private JwtUtil jwtTokenUtil;

	*/

	
	
}
























