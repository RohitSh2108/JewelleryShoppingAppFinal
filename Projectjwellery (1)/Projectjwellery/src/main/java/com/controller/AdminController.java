package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advices.AdminException;
import com.entity.Admin;
import com.serviceImpl.AdminServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
    private AdminServiceImpl adminServiceImpl;

    @GetMapping("/getalladmins")
    public List<Admin> getAllAdmin() throws Throwable {
        return adminServiceImpl.getAllAdmin();
    }

    @GetMapping("/getadminbyid/{AdminId}")
    public Optional<Admin> getAdminById(@PathVariable int AdminId) throws Throwable {
        return adminServiceImpl.getAdminById(AdminId);
    }
    
    @GetMapping("/getadminbyname/{AdminName}")
    public Optional<Admin> getAdminByName(@PathVariable String AdminName) throws AdminException {
        return adminServiceImpl.getAdminByName(AdminName);
    }
    
	/*
	 * @GetMapping("/byLocation/{location}") public List<Admin>
	 * getAdminByLocation(@PathVariable String location) throws AdminException {
	 * return adminService.getAdminByLocation(location); }
	 */
    
    @PostMapping("/addAdmin")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
        return adminServiceImpl.createAdmin(admin);
    }

    @PutMapping("/updateadminbyid/{AdminId}")
    public Admin updateAdmin(@PathVariable int AdminId,@Valid @RequestBody Admin updatedAdmin) throws AdminException {
        return adminServiceImpl.updateAdmin(AdminId, updatedAdmin);
    }

    @DeleteMapping("/deleteadminbyid/{AdminId}")
    public void deleteAdmin(@PathVariable int AdminId) throws Throwable {
        adminServiceImpl.deleteAdmin(AdminId);
    }
}
