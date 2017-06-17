package com.amh.pm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.Project;
import com.amh.pm.entity.Task;
import com.amh.pm.entity.User;
import com.amh.pm.service.OrganizationService;
import com.amh.pm.service.ProjectService;
import com.amh.pm.service.TaskService;
import com.amh.pm.service.UserService;

@Controller
public class TaskController {

    private UserService userService;

    private OrganizationService organizationService;

    private ProjectService projectService;

    private TaskService taskService;

    HttpSession session;

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

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired(required = true)
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/organizations/{organizationId}/projects/{projectId}/tasks", method = RequestMethod.GET)
    public String showTaskLists(@PathVariable("organizationId") int organizationid, @PathVariable("projectId") int projectid, Model model, HttpServletRequest request) {

        session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            int userId = (Integer) session.getAttribute("userId");
            Organization organizationOwner = organizationService.findById(organizationid);

            if (userId == organizationOwner.getOwner().getId()) {
                List<Task> tasks = taskService.findAll();
                List<Task> taskTitles = new ArrayList<Task>();

                for (Task task : tasks) {
                    if (task.getProject().getId() == projectid) {
                        taskTitles.add(task);
                    }
                }
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("taskTitles", taskTitles);
                return "taskLists";

            } else {
                List<Task> tasks = taskService.findAll();
                List<Task> taskTitles = new ArrayList<Task>();

                for (Task task : tasks) {
                    if (task.getProject().getId() == projectid) {
                        taskTitles.add(task);
                    }
                }
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("taskTitles", taskTitles);
                return "showTaskLists";

            }
        }
    }

    @RequestMapping(value = "/organizations/{organizationId}/projects/{projectId}/tasks/new", method = RequestMethod.GET)
    public String showAddTaskForm(@PathVariable("organizationId") int organizationid, @PathVariable("projectId") int projectid, Model model, HttpServletRequest request) {

        session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {

            List<User> userNames = userService.findUserNameByOrgnizationId(organizationid);

            model.addAttribute("userNames", userNames);
            model.addAttribute("organizationId", organizationid);
            model.addAttribute("projectId", projectid);
            model.addAttribute("task", new Task());
            return "addTask";
        }
    }

    @RequestMapping(value = "/organizations/{organizationId}/projects/{projectId}/tasks/new", method = RequestMethod.POST)
    public String addNewTask(@PathVariable("organizationId") int organizationid, @PathVariable("projectId") int projectid, @Validated @ModelAttribute Task task,
            BindingResult result, Model model, HttpServletRequest request) {

        List<User> userNames = userService.findUserNameByOrgnizationId(organizationid);

        /*
         * if (result.hasErrors()) { model.addAttribute("organizationId", organizationid); model.addAttribute("projectId",
         * projectid); return "addTask"; } else {
         */

        if (task.getScheduleStartDate() == null || task.getScheduleEndDate() == null || task.getActualStartDate() == null || task.getActualEndDate() == null) {
            String dateEmptyError = "Please Fill All Dates.";
            model.addAttribute("dateEmptyError", dateEmptyError);
            model.addAttribute("userNames", userNames);
            return "addTask";
        } else {

            Date scheduleStartDate = task.getScheduleStartDate();
            Date scheduleEndDate = task.getScheduleEndDate();
            Date actualStartDate = task.getActualStartDate();
            Date actualEndDate = task.getActualEndDate();

            if ((scheduleStartDate.compareTo(scheduleEndDate) > 0) && (actualStartDate.compareTo(actualEndDate) > 0) && (scheduleStartDate.compareTo(actualStartDate) > 0)) {

                String dateError1 = "Schedule End Date Must Be Greater Than Schedule Start Date. " + "Actual End Date Must Be Greater Than Actual Start Date. "
                        + "Actual Start Date Must Be Greater Than Schedule Start Date.";

                model.addAttribute("TaskdateError1", dateError1);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if ((scheduleStartDate.compareTo(scheduleEndDate) > 0) && (actualStartDate.compareTo(actualEndDate) > 0)
                    && !(scheduleStartDate.compareTo(actualStartDate) > 0)) {
                String dateError2 = "Schedule End Date Must Be Greater Than Schedule Start Date. " + "Actual End Date Must Be Greater Than Actual Start Date.";
                model.addAttribute("TaskdateError2", dateError2);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if ((scheduleStartDate.compareTo(scheduleEndDate) > 0) && !(actualStartDate.compareTo(actualEndDate) > 0)
                    && (scheduleStartDate.compareTo(actualStartDate) > 0)) {
                String dateError3 = "Schedule End Date Must Be Greater Than Schedule Start Date. " + "Actual Start Date Must Be Greater Than Schedule Start Date.";
                model.addAttribute("TaskdateError3", dateError3);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if (!(scheduleStartDate.compareTo(scheduleEndDate) > 0) && (actualStartDate.compareTo(actualEndDate) > 0)
                    && (scheduleStartDate.compareTo(actualStartDate) > 0)) {

                String dateError4 = "Actual End Date Must Be Greater Than Actual Start Date. " + "Actual Start Date Must Be Greater Than Schedule Start Date.";
                model.addAttribute("TaskdateError4", dateError4);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if ((scheduleStartDate.compareTo(scheduleEndDate) > 0) && !(actualStartDate.compareTo(actualEndDate) > 0)
                    && !(scheduleStartDate.compareTo(actualStartDate) > 0)) {
                String dateError5 = "Schedule End Date Must Be Greater Than Schedule Start Date.";
                model.addAttribute("TaskdateError5", dateError5);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if (!(scheduleStartDate.compareTo(scheduleEndDate) > 0) && (actualStartDate.compareTo(actualEndDate) > 0)
                    && !(scheduleStartDate.compareTo(actualStartDate) > 0)) {
                String dateError6 = "Actual End Date Must Be Greater Than Actual Start Date.";
                model.addAttribute("TaskdateError6", dateError6);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if (!(scheduleStartDate.compareTo(scheduleEndDate) > 0) && !(actualStartDate.compareTo(actualEndDate) > 0)
                    && (scheduleStartDate.compareTo(actualStartDate) > 0)) {
                String dateError7 = "Actual Start Date Must Be Greater Than Schedule Start Date.";
                model.addAttribute("TaskdateError7", dateError7);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else if (request.getParameter("assignee").equals("default")) {
                String assigneeEmptyError = "Please Select Assignee.";
                model.addAttribute("assigneeEmptyError", assigneeEmptyError);
                model.addAttribute("organizationId", organizationid);
                model.addAttribute("projectId", projectid);
                model.addAttribute("userNames", userNames);
                return "addTask";
            } else {

                int assigneeId = Integer.parseInt(request.getParameter("assignee"));

                User assigneeUser = userService.findById(assigneeId);
                Project project = projectService.findById(projectid);

                task.setAssignee(assigneeUser);
                task.setProject(project);
                task.setProgress(0);
                taskService.save(task);
                showTaskLists(organizationid, projectid, model, request);
                return "taskLists";
            }
        }
    }
    // }

    @RequestMapping(value = "/organizations/{organizationId}/projects/{projectId}/tasks/{taskId}", method = RequestMethod.GET)
    public String showTaskDetailByTaskId(@PathVariable("organizationId") int organizationid, @PathVariable("projectId") int projectid, @PathVariable("taskId") int taskid,
            @ModelAttribute Task task, BindingResult result, Model model, HttpServletRequest request) {
        session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            Task taskDetail = taskService.findById(taskid);

            model.addAttribute("taskDetail", taskDetail);
            return "showTaskDetail";

        }

    }

}
