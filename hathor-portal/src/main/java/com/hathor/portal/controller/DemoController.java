package com.hathor.portal.controller;

import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.analyse.handler.DefaultAnalyseTreeBuildChain;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>DemoController</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 18:27
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@CrossOrigin
@RestController
@Api(value = "SQL解析", tags = {"SQL解析"})
@RequestMapping("/api/sql/parse/demo")
@Slf4j
public class DemoController {
    private final DefaultAnalyseTreeBuildChain defaultHandlerChain;

    @Autowired
    public DemoController(DefaultAnalyseTreeBuildChain defaultHandlerChain) {
        this.defaultHandlerChain = defaultHandlerChain;
    }

    @PostMapping("test")
    @ApiOperation(value = "test", notes = "demo1", httpMethod = "POST")
    public Object parse(String value) {
        SqlRequestContext sqlRequestContext = new SqlRequestContext();
        SqlResponseContext response = new SqlResponseContext();

        log.info("SQL ---->: {}", value);
        sqlRequestContext.setSql(value);
        sqlRequestContext.setDbType("hive");

        defaultHandlerChain.build(sqlRequestContext, response);

        TreeNode<TableNode> lineageTableTree = response.getLineageTableTree();
        System.out.println(lineageTableTree.getRoot());


        return lineageTableTree;
    }

}