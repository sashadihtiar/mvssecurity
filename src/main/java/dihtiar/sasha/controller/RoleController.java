package dihtiar.sasha.controller;

import dihtiar.sasha.model.Role;
import dihtiar.sasha.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRole() {
        List<Role> list = roleService.getAll();
        return list;
    }

    @RequestMapping(value = "/roles/new", method = RequestMethod.POST)
    public Role addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return role;
    }

    @RequestMapping(value = "/roles/findrolebyid", method = RequestMethod.GET)
    public Role findRole(@RequestParam("id") Long id) {
        return roleService.findRoleById(id);
    }

    @RequestMapping(value = "/roles/delete", method = RequestMethod.DELETE)
    public void deleteRole(@RequestBody Long id) {
        roleService.deleteRole(id);
    }

    @RequestMapping(value = "/roles/update", method = RequestMethod.PUT)
    public Role updataRole(@RequestBody Role role) {
        roleService.addRole(role);
        return role;
    }
}