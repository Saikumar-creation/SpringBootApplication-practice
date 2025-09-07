package com.employeecrudspringboot.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.employeecrudspringboot.controller.EmployeeController;
import com.employeecrudspringboot.dto.DashboardDto;
import com.employeecrudspringboot.dto.EmployeeDto;
import com.employeecrudspringboot.entity.EmployeeEntity;
import com.employeecrudspringboot.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@Service("EmployeeService")
@Primary
//@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeService implements IEmployeeService, InitializingBean, DisposableBean {
	
	private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());
	 

	// Construction dependency injection
	private final EmployeeRepository employeeReposiory;
	private final ModelMapper modelMapper;

	public EmployeeService(EmployeeRepository employeeReposiory, ModelMapper modelMapper) {
		super();
		logger.info("constructor from EmployeeService");
		this.employeeReposiory = employeeReposiory;
		this.modelMapper = modelMapper;
	}

	// Field dependency injection
//	@Autowired
//	EmployeeRepository employeeReposiory;
//
//	@Autowired
//	private ModelMapper modelMapper;
	@Override
	public void saveEmployee(EmployeeDto objEmployeeDto) {
		EmployeeEntity entity;

		if (objEmployeeDto.getId() != null) {
			entity = employeeReposiory.findById(objEmployeeDto.getId().intValue())
					.orElseThrow(() -> new RuntimeException("Employee not found"));
			modelMapper.map(objEmployeeDto, entity);
		} else {
			entity = modelMapper.map(objEmployeeDto, EmployeeEntity.class);
		}
		employeeReposiory.save(entity);
	}

	@Override
	public DashboardDto findall() {
		logger.info("findall from EmployeeService :" + this.hashCode());
		DashboardDto objDashboardDto = new DashboardDto();
		List<EmployeeEntity> liEmployeeEntity = employeeReposiory.findAll();
		List<EmployeeDto> liEmployeeDto = liEmployeeEntity.stream()
				.map(entity -> modelMapper.map(entity, EmployeeDto.class)).collect(Collectors.toList());
		List<EmployeeDto> liActiveEmployess = liEmployeeDto.stream()
				.filter(active -> active.getStatus().equalsIgnoreCase("Active")).collect(Collectors.toList());
		List<EmployeeDto> linActiveEmployess = liEmployeeDto.stream()
				.filter(active -> active.getStatus().equalsIgnoreCase("InActive")).collect(Collectors.toList());
		objDashboardDto.setTotalEmployees(Long.valueOf(liActiveEmployess.size() + linActiveEmployess.size()));
		objDashboardDto.setTotalActiveEmployees(Long.valueOf(liActiveEmployess.size()));
		objDashboardDto.setTotalInactiveEmployees(Long.valueOf(linActiveEmployess.size()));
		objDashboardDto.setEmployeeDto(liEmployeeDto);
		return objDashboardDto;
	}

	@Override
	public void deleteEmployeeById(Integer id) {
		// employeeReposiory.deleteById(id);
		employeeReposiory.deleteEmployeeById(id);
	}

	@Override
	public EmployeeDto getEmployeeById(Integer id) {
		// employeeReposiory.findById(id).orElse(null);
		EmployeeEntity objEmployeeEntity = employeeReposiory.findEmployeeById(id);
		return modelMapper.map(objEmployeeEntity, EmployeeDto.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("afterPropertiesSet from EmployeeService ");
	}

	@Override
	public void destroy() throws Exception {
		logger.info("destroy bean from EmployeeService ");
	}

	@PostConstruct
	private void PostConstruct() {
		logger.info("PostConstruct from EmployeeService :" + this.hashCode());
	}

}
