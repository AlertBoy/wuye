package tsu.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tsu.pro.bean.Repair;
import tsu.pro.bean.Info;
import tsu.pro.bean.Owner;
import tsu.pro.bean.Repair;
import tsu.pro.bean.Stuts;
import tsu.pro.service.OwnerService;
import tsu.pro.service.repairService;

@RestController
@RequestMapping(value = "/repair")
public class RepairController {
	@Autowired
 private	repairService repairservice;
	/**
	 * 插入保修信息
	 */
	@RequestMapping(value ="/", method = RequestMethod.POST)
	public Stuts createUser(@ModelAttribute Repair Repair) {
	 return	repairservice.insertRepair(Repair);
	

	}
	/**
	 * 查询保修  
	 * ID
	 */
	@RequestMapping(value ="/repId/{id}", method = RequestMethod.GET)
	public Info<Repair> findbyID(@PathVariable("id")  int RepairID) {
	 return	repairservice.selectById(RepairID);
	
	}
	/**
	 * 查保修列表
	 */
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public Info<Repair> findList() {
	 return	repairservice.findList();
	
	}
     /**
      * 模糊查询保修信息
      */
	@RequestMapping(value ="/{ownerID}", method = RequestMethod.GET)
	public Info<Repair> findlike(@PathVariable("ownerID")  int ownerID) {
	 return	repairservice.findLike(ownerID);
	}

	/**
	 * 模糊查询保修信息
	 */
	@RequestMapping(value ="/status/{status}", method = RequestMethod.GET)
	public Info<Repair> findByStatus(@PathVariable("status")  int status) {
		return	repairservice.findByStatus(status);
	}

	@RequestMapping(value ="/delete/{id}", method = RequestMethod.GET)
	public Stuts deleteByID(@PathVariable("id")  int RepairID) {
	 return	repairservice.deleteByID(RepairID);
	
	}
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public Stuts update(@ModelAttribute Repair Repair) {
	 return	repairservice.update(Repair);
	}
	@RequestMapping(value ="/fix/{RepairID}/{fixerID}", method = RequestMethod.GET)
	public Stuts fix(@PathVariable("RepairID")  int RepairID,@PathVariable("fixerID")  int fixerID) {
		Info<Repair> repairInfo= repairservice.selectById(RepairID);
		Repair repair=	repairInfo.getT();
		repair.setFixerId(fixerID);
		//设置维修状态
		repair.setRepairStates(1);
		Stuts s=repairservice.update(repair);
		s.setMessage("维修单状态设置成功");
		return	s;
	}
	
}