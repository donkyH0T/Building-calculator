package com.example.simbirgo.security.services;

import com.example.simbirgo.entity.ERole;
import com.example.simbirgo.entity.Role;
import com.example.simbirgo.entity.User;
import com.example.simbirgo.dto.request.LoginRequest;
import com.example.simbirgo.dto.request.SignupRequest;
import com.example.simbirgo.dto.request.UpdateRequest;
import com.example.simbirgo.dto.response.JwtResponse;
import com.example.simbirgo.dto.response.MessageResponse;
import com.example.simbirgo.repository.RoleRepository;
import com.example.simbirgo.repository.UserRepository;
import com.example.simbirgo.security.jwt.JwtUtils;
import com.example.simbirgo.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TokenService tokenService;




    public ResponseEntity<?> me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user=userRepository.findByEmail(currentUserName).get();
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<?> signIn(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    }


    public ResponseEntity<?> signUp(SignupRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already taken!"));
        }
        User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<Role> roles=new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_MANAGER).get());
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String token = request.getHeader("Authorization").replace("Bearer ", "");
            tokenService.revokeToken(token);
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return ResponseEntity.ok(new MessageResponse("User signed out successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User not authenticated"));
        }
    }

    public ResponseEntity<?> update(UpdateRequest updateRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).get();
        if(userRepository.existsByUsername(updateRequest.getUsername())){
            return ResponseEntity.badRequest().build();
        }
        user.setUsername(updateRequest.getUsername());
        user.setPassword(encoder.encode(updateRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }
}
