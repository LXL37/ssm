package cn.smbms.dao.role;
import java.util.List;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
	
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListAll();
	int getRoleCount(@Param("roleCode")String roleCode, @Param("roleName")String roleName);

	List<Role> getRoleList(@Param("roleName")String roleName, @Param("roleCode")String roleCode, @Param("from")int currentPageNo, @Param("pageSize")int pageSize);
	public Role getRoleById(@Param("id") Integer id);


	boolean modify(Role role);

	boolean add(Role role);

	boolean deleteRoleById(int parseInt);

}
