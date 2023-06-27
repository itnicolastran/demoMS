package com.demoMS.user.service.UserService.repositories;

import com.demoMS.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {
}
