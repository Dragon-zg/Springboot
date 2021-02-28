/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.lnnk.mybatis.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * <p>
 * Mysql代码生成器
 * </p>
 *
 * @author Caratacus
 */
public class MysqlGenerator extends SuperGenerator {

    public static void main(String[] args) {
        // 需要生产的表,为null时生成全部
        String[] tableNames = {"gen_table"};
        // 文件生成的位置,为null时默认取项目根路径
        String rootPath = "c:/";
        // 代码生成器
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        AutoGenerator mpg = mysqlGenerator.getAutoGenerator(tableNames, null);
        mpg.execute();
        System.out.println("Generator Success !");
    }
}
