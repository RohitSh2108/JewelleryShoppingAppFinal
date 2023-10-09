package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.advices.AdminException;
import com.entity.Admin;
import com.repository.AdminRepository;
import com.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	 @Autowired
	    private AdminRepository adminRepository;

	    @Override
	    public Optional<Admin> getAdminByName(String adminName) throws AdminException {
	        Optional<Admin> admin = adminRepository.findByAdminName(adminName);
	        if (admin.isEmpty()) {
	            throw new AdminException("Admin not found");
	        }
	        return admin;
	    }
	 
		/*
		 * public List<Admin> getAdminByLocation(String location) throws AdminException
		 * { List<Admin> admins = adminRepository.findByLocation(location); if
		 * (admins.isEmpty()) { throw new
		 * AdminException("No Admin found in the given location"); } return admins; }
		 */

	    @Override
	    public Admin createAdmin(Admin admin) {
	        return adminRepository.save(admin);
	    }
	    
	    @Override
	    public Admin updateAdmin(int AdminId, Admin updatedAdmin) throws AdminException {
	        if (!adminRepository.existsById(AdminId)) {
	            throw new AdminException("Admin not found");
	        }
	        updatedAdmin.setUserId(AdminId);
	        return adminRepository.save(updatedAdmin);
	    }
	    
	    @Override
	    public List<Admin> getAllAdmin() throws Throwable{
			List<Admin> admins = adminRepository.findAll();  
		    if (admins.isEmpty()) {
		        throw new AdminException("No Admin found");
		    }
		    else {
		    return admins;
		}
		}
	    
	    @Override
	    public Optional<Admin> getAdminById(int AdminId) throws Throwable{
	    	Optional<Admin> admins = adminRepository.findById(AdminId);
			if(!admins.isEmpty()) {
			return admins;
		}
			else {
				throw new AdminException("Admin with "+ AdminId +" Not Found");
			}
		}
	    
	    @Override
	    public Optional<Admin> deleteAdmin(int AdminId) throws Throwable{
	    	Optional<Admin> admins = adminRepository.findById(AdminId);
			if(!admins.isEmpty()) {
			return admins;
		}
			else {
				throw new AdminException("Admin with "+ AdminId +" Not Found");
			}
		}

}
