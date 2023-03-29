package com.main.online_clothing_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.online_clothing_store.models.RoleAdminUser;
import com.main.online_clothing_store.models.composite_primary_keys.RoleAdminUserId;

@Repository
public interface RoleAdminUserRepository extends JpaRepository<RoleAdminUser, RoleAdminUserId> {
    public void deleteByIdAdminUserIdAndIdRoleId(Integer adminUserId, Integer roleId);
}
