package dihtiar.sasha.service;

import dihtiar.sasha.model.Role;
import dihtiar.sasha.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    @Transactional
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}