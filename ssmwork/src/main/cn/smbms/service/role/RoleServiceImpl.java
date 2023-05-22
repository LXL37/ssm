package cn.smbms.service.role;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;

@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleMapper roleMapper;
	


	@Override
	public int getRoleCount(String roleCode, String roleName) {
		return roleMapper.getRoleCount(roleCode,roleName);
	}

	@Override
	public List<Role> getRoleList(String roleCode, String roleName, int currentPageNo, int pageSize) {
		currentPageNo = (currentPageNo-1)*pageSize;
		return roleMapper.getRoleList(roleCode,roleName,currentPageNo,pageSize);
	}

	@Override
	public List<Role> getRoleList()  {
		return roleMapper.getRoleListAll();
	}

	@Override
	public Role getRoleById(int parseInt) {
		return roleMapper.getRoleById(parseInt);
	}

	@Override
	public boolean modify(Role role) {
		return roleMapper.modify(role);
	}

	@Override
	public boolean smbmsdeleteroleById(int parseInt) {
		return roleMapper.deleteRoleById(parseInt);
	}

	@Override
	public boolean add(Role role) {
		return roleMapper.add(role);
	}

}
