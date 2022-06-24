package com.ldmzw.demo.scg.controller;

import com.ldmzw.demo.scg.constant.Constants;
import com.ldmzw.demo.scg.controller.info.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController // TODO 接口增加鉴权机制
@Api(value = "ToolController", tags = "智慧运维")
public class ToolController {

    /**
     * 健康检查
     *
     * @return
     */
    @ApiOperation(value = "健康检查")
    @RequestMapping(value = Constants.PATH_HEALTH_CHECK, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseVO<String>> healthCheck() {
        return Mono.just(new ResponseVO<>());
    }

}
