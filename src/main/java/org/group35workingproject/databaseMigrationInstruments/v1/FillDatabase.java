package org.group35workingproject.databaseMigrationInstruments.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.group35workingproject.domain.Role;
import org.group35workingproject.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@Component
public class FillDatabase {
    private final RoleRepository roleRepository;

    public void fillRoleTable(){
        Role admin = new Role();
        admin.setName("Admin");
        roleRepository.save(admin);

        Role user = new Role();
        user.setName("User");
        roleRepository.save(user);
    }
}
