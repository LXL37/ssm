package cn.smbms.service.role;

import java.util.List;

import cn.smbms.pojo.Role;

public interface RoleService {
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	

    int getRoleCount(String roleCode, String roleName);

	List<Role> getRoleList(String roleCode, String roleName, int currentPageNo, int pageSize);
	List<Role> getRoleList();

	Role getRoleById(int parseInt);

	boolean modify(Role role);

	boolean smbmsdeleteroleById(int parseInt);

	boolean add(Role role);
}
