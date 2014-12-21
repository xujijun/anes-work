package com.xjj.anes.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.bean.sys.RoleVo;
import com.xjj.anes.dao.sys.RoleDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.entity.sys.RoleMenu;
import com.xjj.anes.entity.sys.RolePermission;
import com.xjj.anes.service.sys.RoleService;
import com.xjj.anes.utils.MyUtil;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource 
	RoleDao roleDao;

	@Override
	public ResultBean getRoleList() {
		ResultBean rb = new ResultBean();
		Map<String, String> roleMap = new LinkedHashMap<String, String>();
		List<Role> roleList = roleDao.selectAllValid();
		
		for(Role role : roleList){
			roleMap.put(role.getId(), role.getName());
		}
		rb.setData(roleMap);
		return rb;
	}

	@Override
	public ResultBean search(Pager<Role> pager, Role role) {
		ResultBean rb = new ResultBean();
		
		PagerEntity pagerEntity = new PagerEntity(role, pager.getOffset(), pager.getPageSize());
		pager.setDataSize(roleDao.count(pagerEntity));
		if (pager.getDataSize() > 0) {
			if (pager.getDataSize() < pagerEntity.getOffset()) {
				pagerEntity.setOffset(0);
			}
			pager.setBeanList(roleDao.search(pagerEntity));
		}
		
		rb.setData(pager);
		return rb;
	}

	@Override
	public ResultBean insert(Role role) {
		ResultBean rb = new ResultBean();
		int i = roleDao.insert(role);
		if (i <= 0)
		{
			rb.setSuccess(false);
			rb.setMessage("保存失败");
		}
		rb.setMessage("保存成功");
		
		return rb;
	}

	@Override
	public ResultBean update(Role role) {
		ResultBean rb = new ResultBean();
		int i = roleDao.update(role);
		if (i <= 0)	{
			rb.setSuccess(false);
			rb.setMessage("修改失败");
		}
		return rb;
	}

	@Override
	public ResultBean delete(String id) {
		ResultBean rb = new ResultBean();
		roleDao.deleteRoleAndRel(id);
		return rb;
	}

	@Override
	public ResultBean getRoleMenus(String roleId) {
		Role role = null;
		if (!StringUtils.isEmpty(roleId))
			role = roleDao.selectById(roleId);
		ResultBean rb = new ResultBean();
		if (role != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", role.getId());
			map.put("name", role.getName());
			map.put("allMenus", roleDao.getSystemMenus());
			map.put("roleMenus", roleDao.selectMenuIdsByRoleId(roleId));
			map.put("rolePermissions", roleDao.selectPermissionIdsByRoleId(roleId));

			rb.setData(map);
		} else {
			rb.setSuccess(false);
			rb.setMessage("角色不存在");
		}
		return rb;
	}

	@Override
	public ResultBean selectById(String id) {
		ResultBean rb = new ResultBean();
		rb.setData(roleDao.selectById(id));
		return rb;
	}

	@Override
	public ResultBean saveRoleMenus(RoleVo roleVo) {
		ResultBean rb = new ResultBean();
		
		// 先删除此角色所拥有的菜单和权限关联表数据
		roleDao.deleteRelByRoleId(roleVo.getId());
		
		// 构造角色菜单对象列表
		List<RoleMenu> roleMenus = null;
		String[] menuIds = roleVo.getMenuIds();
		if (menuIds != null) {
			roleMenus = new ArrayList<RoleMenu>();
			for (int i = 0; i < menuIds.length; i++) {
				if (!StringUtils.isEmpty(menuIds[i])) {
					roleMenus.add(new RoleMenu(roleVo.getId(), menuIds[i]));
				}
			}
		}
		// 保存角色菜单
		if (roleMenus != null && !roleMenus.isEmpty()) {
			for(RoleMenu roleMenu : roleMenus) {
				roleMenu.setId(MyUtil.generateUUID());
				roleDao.insertRoleMenu(roleMenu);
			}
		}

		// 构造角色权限对象列表
		List<RolePermission> rolePermissons = null;
		String[] permissionIds = roleVo.getPermissionIds();
		if (permissionIds != null) {
			rolePermissons = new ArrayList<RolePermission>();
			for (String permissinId : permissionIds) {
				if (!StringUtils.isEmpty(permissinId)) {
					rolePermissons.add(new RolePermission(roleVo.getId(), permissinId));
				}

			}
		}
		if (rolePermissons != null && !rolePermissons.isEmpty()) {
			for(RolePermission rolePermisson : rolePermissons){
				rolePermisson.setId(MyUtil.generateUUID());
				roleDao.insertRolePermission(rolePermisson);
			}
		}
		
		return rb;
	}

}
