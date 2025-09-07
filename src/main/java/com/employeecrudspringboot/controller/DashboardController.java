package com.employeecrudspringboot.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employeecrudspringboot.beancustomization.PostpreBean;
import com.employeecrudspringboot.dto.EmployeeDto;
import com.employeecrudspringboot.service.IEmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

//@Scope("prototype")
//@Scope("request")
//@Scope("session")
@Controller
public class DashboardController {
	private static final Logger logger = Logger.getLogger(DashboardController.class.getName());
	@Autowired
	@Qualifier("EmployeeService") // NoUniqueBeanDefinitionException
	IEmployeeService iEmployeeService;
	@Autowired
	PostpreBean postpreBean;

	public DashboardController() {
		logger.info("constructor from DashboardController :" + this.hashCode());
	}

	@GetMapping("/dashboard")
	public String dashboard(ModelMap objModelMap, HttpServletRequest request) {
		logger.info("dashboard : "+ iEmployeeService.findall().getEmployeeDto().size());
		objModelMap.addAttribute("objDashboardDto", iEmployeeService.findall());
		// request.getSession().invalidate();
		return "dashboard";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid EmployeeDto objEmployeeDto, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {
		logger.info("objEmployeeDto.toString()  :"+objEmployeeDto.toString());
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream().map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:/dashboard";
		}
		iEmployeeService.saveEmployee(objEmployeeDto);
		if (null != objEmployeeDto.getId()) {
			redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully!");
		} else {
			redirectAttributes.addFlashAttribute("successMessage", "Employee saved successfully!");
		}
		return "redirect:/dashboard";
	}

	@GetMapping("deleteEmployee")
	public String deleteEmployee(@RequestParam Integer id) {
		logger.info("deleteEmployee  :"+id);
		iEmployeeService.deleteEmployeeById(id);
		return "redirect:/dashboard";
	}

}
