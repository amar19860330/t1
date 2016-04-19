/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t1.utils;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Administrator
 */
public class ServletUtil {

    @SuppressWarnings("rawtypes")
    public static < T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            T bean = beanClass.newInstance();
            Map map = request.getParameterMap();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
