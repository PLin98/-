package com.rin.sys.service;

import java.util.List;

import com.rin.sys.domain.Menu;
import com.rin.sys.utils.DataGridView;
import com.rin.sys.vo.MenuVo;

/**
 * 菜单管理的服务接口
 * @author Rin
 *
 */
public interface MenuService {

	/**
	 * 查询所有菜单返回
	 * List<Menu>
	 */
	public List<Menu> queryAllMenuForList(MenuVo menuVo);
	
	/**
	 * 根据用户id查询用户的可用菜单
	 */
	public List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

	/**
	 * 查询所有菜单
	 * @param menuVo
	 * @return
	 */
	public DataGridView queryAllMenu(MenuVo menuVo);
	
	/**
	 * 添加菜单
	 * @param menuVo
	 */
	public void addMenu(MenuVo menuVo);

	/**
	 * 修改菜单
	 * @param menuVo
	 */
	public void updateMenu(MenuVo menuVo);

	/**
	 * 根据pid查询菜单数量
	 * @param pid
	 * @return
	 */
	public Integer queryMenuByPid(Integer pid);

	/**
	 * 根据id删除菜单
	 * @param menuVo
	 */
	public void deleteMenu(MenuVo menuVo);
}
