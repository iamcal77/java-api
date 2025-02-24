package com.Java.MyJavaApi.Repo;

import com.Java.MyJavaApi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository <User, Long> {
}