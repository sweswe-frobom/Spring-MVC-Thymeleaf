/*package com.amh.pm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.User;
import com.amh.pm.service.OrganizationService;
import com.amh.pm.service.UserService;

@Controller
public class OrganizationMemberController {

	private UserService userService;
	private OrganizationService organizationService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	@Qualifier(value = "organizationService")
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@RequestMapping(value = "/organization/{id}/members", method = RequestMethod.GET)
	public String organMemlist(@PathVariable("id") int orgid, Model model, HttpServletRequest request) {

		// int organId = 1;
		List<User> userNameList = userService.findUserNameByOrgnId(orgid);

		model.addAttribute("orgMembers", userNameList);
		model.addAttribute("user", new User());

		HttpSession session = request.getSession(true);
		session.setAttribute("orgMemberName", userNameList);

		return "organizationMember";
	}

	@RequestMapping(value = "/organization/{id}/members/new", method = RequestMethod.POST)
	public String addOrganizationMember(@PathVariable("id") int orgid, @ModelAttribute User user, BindingResult result,
			Model model, HttpServletRequest request) {

		User u = userService.findUserIdByName(user.getName());

		Organization organization = organizationService.findById(orgid);

		HttpSession session = request.getSession(true);

		if (u == null || organization == null) {

			String noUserName = "User name does not exists.";

			session.setAttribute("noNameError", noUserName);

			return "organizationMember";

		} else {

			// organization.addUser(u);
			organization.getUserList().add(u);
			organizationService.save(organization);
			// redirect to

			// model.addAttribute("orgMembers",session.getAttribute("orgMemberName"));
			organMemlist(orgid, model, request);
			return "organizationMember";

		}

	}
}
*/