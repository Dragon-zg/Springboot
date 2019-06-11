package com.jpa.controller;

import com.jpa.model.dto.TeacherDTO;
import com.jpa.model.entity.unidirectional.manttomany.Teacher;
import com.jpa.model.vo.TeacherVO;
import com.jpa.service.TeacherService;
import com.web.util.ServletUtils;
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
 * @author Dragon-zg
 * @date 2019/4/24 10:40
 **/
@Api(value = "teacher", tags = "teacher")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {"/page"})
    public Page<TeacherVO> page(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParam = ServletUtils.getParametersStartingWith(request, "search_");
        Page<Teacher> personPage = teacherService.pagingList(searchParam, pageable);
        return personPage.map(teacher -> new TeacherVO().convertFrom(teacher));
    }

    @ApiOperation(value = "列表")
    @GetMapping(value = {"/list"})
    public List<TeacherVO> list() {
        return convertTo(teacherService.listAll());
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public void init() {
        teacherService.init();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public TeacherVO detail(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.detail(id);
        return new TeacherVO().convertFrom(teacher);
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public void update(@PathVariable("id") Long id, @RequestBody TeacherDTO teacherDto) {
        teacherService.update(id, teacherDto);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public void delete(@PathVariable("id") Long id) {
        teacherService.delete(id);
    }

    private List<TeacherVO> convertTo(List<Teacher> personList) {
        if (CollectionUtils.isEmpty(personList)) {
            return Collections.emptyList();
        }

        return personList.stream().map(person -> (TeacherVO) new TeacherVO().convertFrom(person))
                .collect(Collectors.toList());
    }
}
