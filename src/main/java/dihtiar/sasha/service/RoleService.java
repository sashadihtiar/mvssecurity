package dihtiar.sasha.service;

import dihtiar.sasha.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAll();

    void addRole(Role role);

    Role findRoleById(Long id);

    void deleteRole(Long id);
}