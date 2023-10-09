package com.service;

import java.util.List;
import java.util.Optional;

import com.advices.AdminException;
import com.entity.Admin;

public interface AdminService {

	Optional<Admin> getAdminByName(String adminName) throws AdminException;

	public Admin createAdmin(Admin admin);

	Admin updateAdmin(int AdminId, Admin updatedAdmin) throws AdminException;

	List<Admin> getAllAdmin() throws Throwable;

	Optional<Admin> getAdminById(int AdminId) throws Throwable;

	Optional<Admin> deleteAdmin(int AdminId) throws Throwable;
}
