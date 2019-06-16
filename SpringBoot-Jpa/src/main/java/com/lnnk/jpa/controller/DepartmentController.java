package com.lnnk.jpa.controller;

import com.lnnk.jpa.model.dto.DepartmentDTO;
import com.lnnk.jpa.model.entity.onetomany.Department;
import com.lnnk.jpa.model.vo.DepartmentVO;
import com.lnnk.jpa.service.DepartmentService;
import com.lnnk.web.util.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lnnk
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

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {"/page"})
    public Page<DepartmentVO> page(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParam = ServletUtils.getParametersStartingWith(request, "search_");
        Page<Department> departmentPage = departmentService.pagingList(searchParam, pageable);
        return departmentPage.map(department -> new DepartmentVO().convertFrom(department));
    }

    @ApiOperation(value = "列表")
    @GetMapping(value = {"/list"})
    public List<DepartmentVO> list() {
        return convertTo(departmentService.listAll());
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public void initDepartment() {
        departmentService.initDepartment();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public DepartmentVO detail(@PathVariable("id") Long id) {
        Department department = departmentService.detail(id);
        return new DepartmentVO().convertFrom(department);
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public void update(@PathVariable("id") Long id, @RequestBody DepartmentDTO departmentDto) {
        departmentService.update(id, departmentDto);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public void delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
    }

    private List<DepartmentVO> convertTo(List<Department> departments) {
        if (CollectionUtils.isEmpty(departments)) {
            return Collections.emptyList();
        }

        return departments.stream().map(department -> (DepartmentVO) new DepartmentVO().convertFrom(department))
                .collect(Collectors.toList());
    }
}
