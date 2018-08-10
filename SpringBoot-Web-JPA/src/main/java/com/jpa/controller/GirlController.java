package com.jpa.controller;

import com.jpa.entity.Girl;
import com.jpa.properties.GirlProperties;
import com.jpa.service.GirlService;
import com.web.model.ResultModel;
import com.web.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Date: 2017-12-16 19:39
 */
@RestController
@RequestMapping("/girlController")
public class GirlController {

    @Autowired
    private GirlProperties girlProperties;

    @Autowired
    private GirlService girlService;

    /**
     * 获取项目初始化的配置文件信息
     *
     * @return
     * @see 可以使用@GetMapping("route")替代@RequestMapping(value="route",method = RequestMethod.GET)
     * 同理还有@PostMapping, @PutMapping, @DeleteMapping
     */
    @ApiOperation("获取项目初始化的配置文件信息")
    @GetMapping("/propertiesInfo")
    public String getPropertiesGrilInfo() {
        return "cupSize: " + girlProperties.getCupSize() + ", Age: " + girlProperties.getAge();
    }

    /**
     * 获取女生列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("获取女生列表")
    @GetMapping("/girls")
    public ResultModel<List<Girl>> getGirlList() throws Exception {
        return ResultUtil.success(girlService.getGirlList());
    }

    /**
     * 添加女生
     *
     * @param girl
     * @return
     * @throws Exception
     */
    @ApiOperation("添加女生")
    @PostMapping("/add")
    public ResultModel<Girl> girlAdd(Girl girl) throws Exception {
        return ResultUtil.success(girlService.addGirl(girl));
    }

    /**
     * 通过 id 查找女生
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation("通过 id 查找女生")
    @GetMapping("/{id}")
    public ResultModel<Girl> girlFindOne(@PathVariable("id") Integer id) throws Exception {
        return ResultUtil.success(girlService.findById(id));
    }

    /**
     * 通过 id 删除女生 Content-Type = application/x-www-form-urlencoded
     *
     * @param id
     */
    @ApiOperation("通过 id 删除女生")
    @DeleteMapping(value = "/{id}")
    public ResultModel girlDelete(@PathVariable("id") Integer id) throws Exception {
        girlService.deleteGirl(id);
        return ResultUtil.success();
    }

    /**
     * 更新女生 Content-Type = application/x-www-form-urlencoded
     *
     * @param id
     */
    @ApiOperation("更新女生")
    @PutMapping(value = "/update")
    public ResultModel<Girl> girlUpdate(Girl girl) throws Exception {
        return ResultUtil.success(girlService.updateGirl(girl));
    }

    /**
     * 根据id获取女生年纪
     *
     * @param id
     * @throws Exception
     */
    @ApiOperation("根据id获取女生年纪")
    @GetMapping(value = "getAge/{id}")
    public ResultModel<Integer> getAgeById(@PathVariable("id") Integer id) throws Exception {
        return ResultUtil.success(girlService.getAgeById(id));
    }
}
