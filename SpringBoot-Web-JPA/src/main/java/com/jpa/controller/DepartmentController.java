package com.jpa.controller;

import com.jpa.model.entity.unidirectional.onetomany.Department;
import com.jpa.service.DepartmentService;
import com.web.model.ResultModel;
import com.web.util.ResultUtils;
import com.web.util.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:40
 **/
@Api(value = "department", tags = "department")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public ResultModel initPerson() {
        departmentService.initDepartment();
        return ResultUtils.success();
    }

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {""})
    public Page<Department> page(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParam = ServletUtils.getParametersStartingWith(request, "search_");
        return departmentService.pagingList(searchParam, pageable);
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public ResultModel<Department> detail(@PathVariable("id") Long id) {
        return ResultUtils.success(departmentService.detail(id));
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public ResultModel update(@PathVariable("id") Long id, @RequestBody Department department) {
        departmentService.update(id, department);
        return ResultUtils.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public ResultModel delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
        return ResultUtils.success();
    }
}
